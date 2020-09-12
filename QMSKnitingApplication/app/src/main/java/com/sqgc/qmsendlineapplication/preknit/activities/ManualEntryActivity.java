/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 9/12/20 9:46 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 9/12/20 9:46 AM
 *
 */

package com.sqgc.qmsendlineapplication.preknit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sqgc.qmsendlineapplication.FloorInfoActivity;
import com.sqgc.qmsendlineapplication.R;
import com.sqgc.qmsendlineapplication.models.Buyer;
import com.sqgc.qmsendlineapplication.models.GarmentsBundleSettings;
import com.sqgc.qmsendlineapplication.models.PO;
import com.sqgc.qmsendlineapplication.sharedDB.InputHistoryShared;
import com.sqgc.qmsendlineapplication.sharedDB.LotSetShared;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManualEntryActivity extends AppCompatActivity {
    @BindView(R.id.actv_buyer)
    AutoCompleteTextView actvBuyer;
    @BindView(R.id.actv_style)
    AutoCompleteTextView actvStyle;
    @BindView(R.id.actv_po)
    AutoCompleteTextView actvPo;
    @BindView(R.id.actv_size)
    AutoCompleteTextView actvSize;
    @BindView(R.id.actv_color)
    AutoCompleteTextView actvColor;
    @BindView(R.id.actv_batch_qty)
    AutoCompleteTextView actvBatchQty;
    @BindView(R.id.actv_pc_to_be_checked)
    AutoCompleteTextView actvPcToBeChecked;

    GarmentsBundleSettings garmentsBundleSettings;
    LotSetShared lotSetShared;
    InputHistoryShared inputHistoryShared;
    @BindView(R.id.operator_id)
    AutoCompleteTextView actvOperatorId;
    @BindView(R.id.actv_machine_id)
    AutoCompleteTextView actvMachineId;
    @BindView(R.id.bt_back_to_floor)
    Button btBackToFloor;
    @BindView(R.id.bt_set)
    Button btSet;

    // private SharedPreferences settings;
    private Set<String> buyerHistory, styleHistory, poHistory, colorHistory, sizeHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_entry);
        ButterKnife.bind(this);
        inputHistoryShared = new InputHistoryShared(getApplicationContext());
        lotSetShared = new LotSetShared(getApplicationContext());
        garmentsBundleSettings = new GarmentsBundleSettings();

        //settings = getSharedPreferences(PREFS_NAME, 0);
        buyerHistory = inputHistoryShared.getBuyersName();
        styleHistory = inputHistoryShared.getStylesName();
        poHistory = inputHistoryShared.getPOName();
        sizeHistory = inputHistoryShared.getSizeName();
        colorHistory = inputHistoryShared.getColorName();

        setAutoCompleteSource();


        //buyer
        actvBuyer.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    addBuyerSearchInput(actvBuyer.getText().toString());
                    return true;
                }
                return false;
            }
        });


        //style
        actvStyle.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    addStyleSearchInput(actvStyle.getText().toString());
                    return true;
                }
                return false;
            }
        });

        //po
        actvPo.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    addPOSearchInput(actvPo.getText().toString());
                    return true;
                }
                return false;
            }
        });


        //size
        actvSize.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    addSizeSearchInput(actvSize.getText().toString());
                    return true;
                }
                return false;
            }
        });


        //color
        actvColor.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    addColorSearchInput(actvColor.getText().toString());
                    return true;
                }
                return false;
            }
        });


    }

    private void setAutoCompleteSource() {
        //buyer
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, buyerHistory.toArray(new String[buyerHistory.size()]));
        actvBuyer.setAdapter(adapter);

        //style
        ArrayAdapter<String> styleAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, styleHistory.toArray(new String[styleHistory.size()]));
        actvStyle.setAdapter(styleAdapter);

        //po
        ArrayAdapter<String> poAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, poHistory.toArray(new String[poHistory.size()]));
        actvPo.setAdapter(poAdapter);

        //color
        ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, colorHistory.toArray(new String[colorHistory.size()]));
        actvColor.setAdapter(colorAdapter);

        //size
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, sizeHistory.toArray(new String[sizeHistory.size()]));
        actvSize.setAdapter(sizeAdapter);


    }


    //buyer
    private void addBuyerSearchInput(String input) {
        if (buyerHistory == null) {
            buyerHistory = inputHistoryShared.getBuyersName();
        }

        if (!buyerHistory.contains(input)) {
            buyerHistory.add(input);
            setAutoCompleteSource();
        }
    }

    //style
    private void addStyleSearchInput(String input) {
        if (styleHistory == null) {
            styleHistory = inputHistoryShared.getStylesName();
        }

        if (!styleHistory.contains(input)) {
            styleHistory.add(input);
            setAutoCompleteSource();
        }
    }

    //po
    private void addPOSearchInput(String input) {
        if (poHistory == null) {
            poHistory = inputHistoryShared.getPOName();
        }

        if (!poHistory.contains(input)) {
            poHistory.add(input);
            setAutoCompleteSource();
        }
    }

    //size
    private void addSizeSearchInput(String input) {
        if (sizeHistory == null) {
            sizeHistory = inputHistoryShared.getSizeName();
        }

        if (!sizeHistory.contains(input)) {
            sizeHistory.add(input);
            setAutoCompleteSource();
        }
    }


    //color
    private void addColorSearchInput(String input) {
        if (colorHistory == null) {
            colorHistory = inputHistoryShared.getColorName();
        }

        if (!colorHistory.contains(input)) {
            colorHistory.add(input);
            setAutoCompleteSource();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        savePrefs();
    }

    private void savePrefs() {

        inputHistoryShared.saveBuyerPrefs(buyerHistory);
        inputHistoryShared.saveStylePrefs(styleHistory);
        inputHistoryShared.savePOPrefs(poHistory);
        inputHistoryShared.saveSizePrefs(sizeHistory);
        inputHistoryShared.saveColorPrefs(colorHistory);


    }

    @OnClick({R.id.bt_back_to_floor, R.id.bt_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_back_to_floor:
                startActivity(new Intent(ManualEntryActivity.this, FloorInfoActivity.class));
                finish();
                break;
            case R.id.bt_set:
                savePrefs();
                setProductionEntryData();
                break;
        }
    }

    private void setProductionEntryData() {

        //operatorID
        if (actvOperatorId.getText().toString().isEmpty()) {
            actvOperatorId.setError("Operator ID is required*");
            return;
        }
        garmentsBundleSettings.setOperatorID(actvOperatorId.getText().toString());


        //machine ID
        if (actvMachineId.getText().toString().isEmpty()) {
            actvMachineId.setError("Machine ID is required*");
            return;
        }
        garmentsBundleSettings.setMachineID(actvMachineId.getText().toString());


        //PCs to be checked
        if (actvPcToBeChecked.getText().toString().isEmpty()) {
            actvPcToBeChecked.setError("PCs to be checked required is required*");
            return;
        }
        garmentsBundleSettings.setGarmentsTobeChecked(actvPcToBeChecked.getText().toString());


        //buyer
        if (actvBuyer.getText().toString().isEmpty()) {
            actvBuyer.setError("Buyer is required*");
            return;
        }
        Buyer buyer = new Buyer(0, actvBuyer.getText().toString());
        garmentsBundleSettings.setBuyer(buyer);

        //style
        //tvStyle.setText(barcodeAPIDataModel.getStyleName());

        garmentsBundleSettings.setStyleCategory("cardigan ");
        garmentsBundleSettings.setSmv("0.0");

        if (actvStyle.getText().toString().isEmpty()) {
            actvStyle.setError("Style is required*");
            return;
        }

        garmentsBundleSettings.setStyleSubCategory(actvStyle.getText().toString());


        //po
        if (actvPo.getText().toString().isEmpty()) {
            actvPo.setError("PO is required*");
            return;
        }
        PO po = new PO(0, actvPo.getText().toString());
        garmentsBundleSettings.setPo(po);


        //size
        if (actvSize.getText().toString().isEmpty()) {
            actvSize.setError("Size is required*");
            return;
        }
        garmentsBundleSettings.setSize(actvSize.getText().toString());


        //color
        if (actvColor.getText().toString().isEmpty()) {
            actvColor.setError("Color is required*");
            return;
        }
        garmentsBundleSettings.setColor(actvColor.getText().toString());


        //Batch Qty
        if (actvBatchQty.getText().toString().isEmpty()) {
            actvBatchQty.setError("Batch Quantity is required*");
            return;
        }
        garmentsBundleSettings.setBatchQty(actvBatchQty.getText().toString());


        if (garmentsBundleSettings.getStyleCategory().toLowerCase().equals("cardigan ")) {

            lotSetShared.saveSettings(garmentsBundleSettings);

/*            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();*/

            Intent intent = new Intent(getApplicationContext(), ManualMainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

            //Toast.makeText(this, "SET", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Style is not ready to set", Toast.LENGTH_SHORT).show();
        }
    }

}