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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
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

    //cached obj
    GarmentsBundleSettings garmentsBundleSettings;
    LotSetShared lotSetShared;
    SelectionSetting selectionSetting;
    BarcodeAPIDataModel barcodeAPIDataModel;

    AlertDialog internetAlertDialog;
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
                break;
        }
    }

    private void setProductionFloorData() {

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


}