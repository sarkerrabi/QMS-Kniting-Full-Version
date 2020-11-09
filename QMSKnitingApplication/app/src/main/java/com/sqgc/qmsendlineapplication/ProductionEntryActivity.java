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
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.novoda.merlin.Bindable;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;
import com.novoda.merlin.NetworkStatus;
import com.sqgc.qmsendlineapplication.async_task_s.PositionGridTask;
import com.sqgc.qmsendlineapplication.models.Buyer;
import com.sqgc.qmsendlineapplication.models.GarmentsBundleSettings;
import com.sqgc.qmsendlineapplication.models.PO;
import com.sqgc.qmsendlineapplication.models.api_models.BarcodeAPIDataModel;
import com.sqgc.qmsendlineapplication.sharedDB.LotSetShared;
import com.sqgc.qmsendlineapplication.sharedDB.SelectionSetting;
import com.sqgc.qmsendlineapplication.views.ProductionEntryView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductionEntryActivity extends AppCompatActivity implements ProductionEntryView, Connectable, Bindable, Disconnectable {

    private static int BARCODE_SCAN_CODE = 404;
    @BindView(R.id.tv_buyer)
    TextView tvBuyer;
    @BindView(R.id.tv_style)
    TextView tvStyle;
    @BindView(R.id.tv_po)
    TextView tvPo;
    @BindView(R.id.tv_size)
    TextView tvSize;
    @BindView(R.id.tv_color)
    TextView tvColor;
    @BindView(R.id.pcTobeChecked)
    EditText pcTobeChecked;
    @BindView(R.id.tv_batch_qty)
    TextView tvBatchQty;
    @BindView(R.id.tv_lot_no)
    TextView tvLotNo;

    //cached obj
    GarmentsBundleSettings garmentsBundleSettings;
    LotSetShared lotSetShared;
    SelectionSetting selectionSetting;
    BarcodeAPIDataModel barcodeAPIDataModel;

    AlertDialog internetAlertDialog;
    @BindView(R.id.operator_id)
    EditText etOperatorId;
    @BindView(R.id.machine_id)
    EditText etMachineId;

    private Merlin merlin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_entry);
        ButterKnife.bind(this);

        lotSetShared = new LotSetShared(getApplicationContext());
        selectionSetting = new SelectionSetting(getApplicationContext());
        garmentsBundleSettings = new GarmentsBundleSettings();

        merlin = new Merlin.Builder()
                .withConnectableCallbacks()
                .withDisconnectableCallbacks()
                .withBindableCallbacks()
                .build(ProductionEntryActivity.this);


        //get data from previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Gson gson = new Gson();
            String value = extras.getString("BARCODE_JSON");
            if (value != null) {
                barcodeAPIDataModel = gson.fromJson(value, BarcodeAPIDataModel.class);
                if (barcodeAPIDataModel != null) {
                    setValuesIntoView();
                }

            }
        }

        merlin.registerConnectable(this);
        merlin.registerDisconnectable(this);
        merlin.registerBindable(this);


    }

    private void setValuesIntoView() {

        garmentsBundleSettings.setSmv(barcodeAPIDataModel.getOperationSMV());
        //Log.e("TAG_SMV", "setValuesIntoView: "+ barcodeAPIDataModel.getOperationSMV());

        //buyer
        tvBuyer.setText(barcodeAPIDataModel.getBuyerName());
        Buyer buyer = new Buyer(0, barcodeAPIDataModel.getBuyerName());
        garmentsBundleSettings.setBuyer(buyer);

        //style
        tvStyle.setText(barcodeAPIDataModel.getStyleName());

        garmentsBundleSettings.setStyleID(barcodeAPIDataModel.getStyleID());
        garmentsBundleSettings.setStyleSubCategory(barcodeAPIDataModel.getStyleName());


        //po
        tvPo.setText(barcodeAPIDataModel.getpONumber());
        PO po = new PO(0, barcodeAPIDataModel.getpONumber());
        garmentsBundleSettings.setPo(po);


        //size
        tvSize.setText(barcodeAPIDataModel.getBundleSize());
        garmentsBundleSettings.setSize(barcodeAPIDataModel.getBundleSize());


        //color
        tvColor.setText(barcodeAPIDataModel.getColor());
        garmentsBundleSettings.setColor(barcodeAPIDataModel.getColor());

        //batch qty
        tvBatchQty.setText(barcodeAPIDataModel.getBatchQuantity().toString());
        garmentsBundleSettings.setBatchQty(String.valueOf(barcodeAPIDataModel.getBatchQuantity()));

        //machine id
        etMachineId.setText(String.valueOf(barcodeAPIDataModel.getMachineId()));
        garmentsBundleSettings.setMachineID(String.valueOf(barcodeAPIDataModel.getMachineId()));

        //server lot no
        tvLotNo.setText(String.valueOf(barcodeAPIDataModel.getLotNo()));
        garmentsBundleSettings.setServerLotNo(String.valueOf(barcodeAPIDataModel.getLotNo()));


    }

    @OnClick({R.id.bt_back_to_floor, R.id.bt_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back_to_floor:
                startActivity(new Intent(ProductionEntryActivity.this, FloorInfoActivity.class));
                finish();
                break;
            case R.id.bt_set:
                setProductionFloorData();
                lotSetShared.saveManualOrScan(false);
                break;
        }
    }

    private void setProductionFloorData() {
        //operatorID
        if (etOperatorId.getText().toString().isEmpty()) {
            etOperatorId.setError("Operator ID is required*");
            return;
        }
        garmentsBundleSettings.setOperatorID(etOperatorId.getText().toString());


        //machine ID
        if (etMachineId.getText().toString().isEmpty()) {
            etMachineId.setError("Machine ID is required*");
            return;
        }
        garmentsBundleSettings.setMachineID(etMachineId.getText().toString());

        if (pcTobeChecked.getText().toString().isEmpty()) {
            pcTobeChecked.setError("PCs to be checked is required*");
            return;
        }
        garmentsBundleSettings.setGarmentsTobeChecked(pcTobeChecked.getText().toString());
        lotSetShared.saveSettings(garmentsBundleSettings);
        PositionGridTask positionGridTask = new PositionGridTask(ProductionEntryActivity.this, getApplicationContext(), this, barcodeAPIDataModel.getStyleID(), barcodeAPIDataModel);
        positionGridTask.execute();


    }

    @Override
    public void onPositionGridAPISavedDone() {


    }

    @Override
    public void onPositionGridAPIFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDefectListAPISavedDone() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

    @Override
    public void onDefectListAPIFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

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
        internetAlertDialog = new AlertDialog.Builder(ProductionEntryActivity.this).create();
        internetAlertDialog.setTitle("Alert");
        internetAlertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        internetAlertDialog.setCancelable(false);
        internetAlertDialog.setCanceledOnTouchOutside(false);
        internetAlertDialog.setMessage("NO INTERNET CONNECTION !!! \nPLEASE CHECK YOUR INTERNET CONNECTION");
        internetAlertDialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                if (BARCODE_SCAN_CODE == 1001) {
                    if (result.getContents() != null) {
                        etOperatorId.setText(result.getContents());

                    } else {
                        Toast.makeText(this, "No Barcode Found, Try Again!", Toast.LENGTH_SHORT).show();
                    }
                } else if (BARCODE_SCAN_CODE == 2002) {
                    if (result.getContents() != null) {
                        etMachineId.setText(result.getContents());

                    } else {
                        Toast.makeText(this, "No Barcode Found, Try Again!", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @OnClick({R.id.bt_operator_scan, R.id.bt_machine_id_scan})
    public void onScanViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_operator_scan:
                BARCODE_SCAN_CODE = 1001;
                IntentIntegrator integrator = new IntentIntegrator(this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.PDF_417);
                integrator.setPrompt("Scan a Operator ID barcode");
                integrator.setCameraId(0);  // Use a specific camera of the device
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
                break;
            case R.id.bt_machine_id_scan:
                BARCODE_SCAN_CODE = 2002;
                IntentIntegrator integrator2 = new IntentIntegrator(this);
                integrator2.setDesiredBarcodeFormats(IntentIntegrator.PDF_417);
                integrator2.setPrompt("Scan a Machine ID barcode");
                integrator2.setCameraId(0);  // Use a specific camera of the device
                integrator2.setBeepEnabled(false);
                integrator2.setBarcodeImageEnabled(true);
                integrator2.initiateScan();
                break;
        }
    }
}