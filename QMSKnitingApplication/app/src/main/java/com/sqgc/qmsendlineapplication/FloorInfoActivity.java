/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/24/20 2:58 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/24/20 2:58 PM
 *
 */

package com.sqgc.qmsendlineapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.amitshekhar.DebugDB;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.novoda.merlin.Bindable;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;
import com.novoda.merlin.NetworkStatus;
import com.sqgc.qmsendlineapplication.models.FloorSetting;
import com.sqgc.qmsendlineapplication.models.Line;
import com.sqgc.qmsendlineapplication.models.Module;
import com.sqgc.qmsendlineapplication.models.ProductionUnit;
import com.sqgc.qmsendlineapplication.models.api_models.BarcodeAPIDataModel;
import com.sqgc.qmsendlineapplication.models.api_models.LoginResponse;
import com.sqgc.qmsendlineapplication.presenters.FloorInfoPresenter;
import com.sqgc.qmsendlineapplication.sharedDB.LotSetShared;
import com.sqgc.qmsendlineapplication.sharedDB.SelectionSetting;
import com.sqgc.qmsendlineapplication.sharedDB.UUIDSHared;
import com.sqgc.qmsendlineapplication.views.FloorInfoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FloorInfoActivity extends AppCompatActivity implements FloorInfoView, Connectable, Bindable, Disconnectable {
    public final int WRITE_PERMISSON_REQUEST_CODE = 1;
    FloorInfoPresenter floorInfoPresenter;
    FloorSetting floorSetting;
    LotSetShared lotSetShared;
    UUIDSHared uuidsHared;
    SelectionSetting selectionSetting;
    @BindView(R.id.tv_last_sync)
    TextView tvLastSync;
    @BindView(R.id.tv_production_unit)
    TextView tvProductionUnit;
    @BindView(R.id.tv_module)
    TextView tvModule;
    @BindView(R.id.tv_line)
    TextView tvLine;

    AlertDialog internetAlertDialog;
    private Merlin merlin;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                floorInfoPresenter.getBarcodeDataFromServer(result.getContents(), uuidsHared.getUserData().getBusinessUnitId());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_info);
        ButterKnife.bind(this);
        Log.e("DB_DEBUG", "onCreate: " + DebugDB.getAddressLog());
        lotSetShared = new LotSetShared(getApplicationContext());
        selectionSetting = new SelectionSetting(getApplicationContext());
        uuidsHared = new UUIDSHared(getApplicationContext());

        floorSetting = new FloorSetting();
        floorInfoPresenter = new FloorInfoPresenter(this, getApplicationContext(), this);

        merlin = new Merlin.Builder()
                .withConnectableCallbacks()
                .withDisconnectableCallbacks()
                .withBindableCallbacks()
                .build(FloorInfoActivity.this);

        if (uuidsHared.getUserData() != null) {
            LoginResponse loginResponse = uuidsHared.getUserData();
            if (loginResponse.getLineNumber() != null) {
                tvLine.setText(loginResponse.getLineNumber());
                floorSetting.setLine(new Line(0, loginResponse.getLineNumber()));
            }

            if (loginResponse.getProductionUnit() != null) {
                tvProductionUnit.setText(loginResponse.getProductionUnit());
                floorSetting.setProductionUnit(new ProductionUnit(0, loginResponse.getProductionUnit()));
            }

            if (loginResponse.getModuleName() != null) {
                tvModule.setText(loginResponse.getModuleName());
                floorSetting.setModule(new Module(0, loginResponse.getModuleName()));
            }


        }


        if (uuidsHared.getLastSyncTime() != null) {
            tvLastSync.setText(String.format("Last Syc with server at - %s", uuidsHared.getLastSyncTime()));
        }

        merlin.registerConnectable(this);
        merlin.registerDisconnectable(this);
        merlin.registerBindable(this);

    }

    @OnClick(R.id.bt_scan)
    public void onViewScanBarcodeClicked() {
        if (floorSetting.getLine() != null || floorSetting.getModule() != null || floorSetting.getProductionUnit() != null) {
            lotSetShared.saveFloorSetting(floorSetting);
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.PDF_417);
            integrator.setPrompt("Scan a barcode");
            integrator.setCameraId(0);  // Use a specific camera of the device
            integrator.setBeepEnabled(false);
            integrator.setBarcodeImageEnabled(true);
            integrator.initiateScan();
        }
    }


    @Override
    public void onBarcodeDetailsReady(BarcodeAPIDataModel barcodeAPIDataModel) {
        if (barcodeAPIDataModel != null) {
            if (!barcodeAPIDataModel.getStatus()) {
                this.onBarcodeFailed("Barcode not found");
            } else {
                Gson gson = new Gson();
                String productionEntryJson = gson.toJson(barcodeAPIDataModel);
                Intent intent = new Intent(FloorInfoActivity.this, ProductionEntryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("BARCODE_JSON", productionEntryJson);
                startActivity(intent);

            }
        }


    }


    @Override
    public void onBarcodeFailed(String error_message) {
        Toast.makeText(this, error_message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.bt_logout)
    public void onViewClicked() {
        startActivity(new Intent(FloorInfoActivity.this, LoginActivity.class));
        finish();
    }


    @Override
    protected void onResume() {
        merlin.bind();
        super.onResume();
    }

    @Override
    protected void onPause() {
        merlin.unbind();
        super.onPause();
    }

    @Override
    public void onConnect() {
        if (internetAlertDialog != null) {
            if (internetAlertDialog.isShowing()) {
                try {
                    internetAlertDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }


    }

    @Override
    public void onBind(NetworkStatus networkStatus) {
        if (!networkStatus.isAvailable()) {
            onDisconnect();
        }

    }

    @Override
    public void onDisconnect() {
        internetAlertDialog = new AlertDialog.Builder(FloorInfoActivity.this).create();
        internetAlertDialog.setTitle("Alert");
        internetAlertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        internetAlertDialog.setCancelable(false);
        internetAlertDialog.setCanceledOnTouchOutside(false);
        internetAlertDialog.setMessage("NO INTERNET CONNECTION !!! \nPLEASE CHECK YOUR INTERNET CONNECTION");
        internetAlertDialog.show();
    }
}