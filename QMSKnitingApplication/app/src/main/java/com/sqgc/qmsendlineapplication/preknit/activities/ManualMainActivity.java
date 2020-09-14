/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 9/12/20 10:37 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 9/12/20 10:37 AM
 *
 */

package com.sqgc.qmsendlineapplication.preknit.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.sqgc.qmsendlineapplication.FloorInfoActivity;
import com.sqgc.qmsendlineapplication.LoginActivity;
import com.sqgc.qmsendlineapplication.R;
import com.sqgc.qmsendlineapplication.decorators.GridSpacingItemDecoration;
import com.sqgc.qmsendlineapplication.dialogs.SimpleAlertDialog;
import com.sqgc.qmsendlineapplication.models.Defect;
import com.sqgc.qmsendlineapplication.models.GarmentsBundleSettings;
import com.sqgc.qmsendlineapplication.models.QCDataModel;
import com.sqgc.qmsendlineapplication.models.StyleMapDataModel;
import com.sqgc.qmsendlineapplication.models.api_models.BarcodeAPIResponseModel;
import com.sqgc.qmsendlineapplication.preknit.adapters.ManualDefectAdapter;
import com.sqgc.qmsendlineapplication.preknit.database.DBHelper;
import com.sqgc.qmsendlineapplication.preknit.presenters.ManualMainPresenter;
import com.sqgc.qmsendlineapplication.preknit.views.ManualMainView;
import com.sqgc.qmsendlineapplication.sharedDB.LotSetShared;
import com.sqgc.qmsendlineapplication.sharedDB.UUIDSHared;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sqgc.qmsendlineapplication.common.CommonSettings.getCurrentTime;
import static com.sqgc.qmsendlineapplication.common.CommonSettings.getDate;

public class ManualMainActivity extends AppCompatActivity implements ManualMainView {
    private static final String TAG = "MAIN";
    public List<QCDataModel> qcDataModelList = new ArrayList<>();

    List<TextView> textViewList = new ArrayList<>();

    //    CommonData commonData;
    GridLayout frontGridLayout, backGridLayout;
    //List<DesignPixel> setDesign = new ArrayList<>();
    @BindView(R.id.bt_front_side)
    Button btFrontSide;
    @BindView(R.id.bt_back_side)
    Button btBackSide;

    ManualMainPresenter mainPresenter;
    @BindView(R.id.rvDefects)
    RecyclerView rvDefects;
    @BindView(R.id.tvGarmentCount)
    TextView tvGarmentCount;
    @BindView(R.id.bt_nextCountGarment)
    EditText btNextCountGarment;
    @BindView(R.id.iv_style)
    ImageView ivStyle;
    @BindView(R.id.tvLotNo)
    TextView tvLotNo;
    @BindView(R.id.bt_undo)
    Button btUndo;
    @BindView(R.id.tvTGEntry)
    TextView tvTGEntry;
    @BindView(R.id.tv_last_sync)
    TextView tvLastSync;
    private GridLayoutManager gridLayoutManager;

    private LotSetShared lotSetShared;
    private UUIDSHared uuidsHared;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_main);
        ButterKnife.bind(this);
        lotSetShared = new LotSetShared(getApplicationContext());
        uuidsHared = new UUIDSHared(getApplicationContext());
        mainPresenter = new ManualMainPresenter(this, getApplicationContext(), ManualMainActivity.this);
//        commonData = new CommonData();
//        setDesign = commonData.getFrontDesign();
//        //btBackSide.setBackgroundColor(Color.BLACK);


        frontGridLayout = findViewById(R.id.grid_lay);
        backGridLayout = findViewById(R.id.grid_lay2);

        mainPresenter.setFullDesign(lotSetShared.getGarmentSettings().getStyleSubCategory(), lotSetShared.getGarmentSettings().getStyleCategory());
        this.onUpdateGarmentsCount();
        int spanCount = 3; // 3 columns
        int spacing = 10; // 50px
        boolean includeEdge = true;
        rvDefects.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        onUpdateGarmentsCount();
//        setDesignView();

        /*
        textViewList.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "clicked 4", Toast.LENGTH_SHORT).show();
                tvClicked.setText(4+" ");
            }
        });*/

        if (lotSetShared.getGarmentsCount() == 0) {
            btUndo.setVisibility(View.INVISIBLE);
        }
        mainPresenter.setTotalGarmentsCount();


        setLastSyncData();

    }

    private void setLastSyncData() {
        if (uuidsHared.getLastSyncTime() != null) {
            tvLastSync.setText(String.format("Last Syc with server at - %s", uuidsHared.getLastSyncTime()));
        }
    }

    @Override
    public void onFullDesignReady(List<StyleMapDataModel> allStyleMaps) {
        setFrontDesignView1(allStyleMaps);
        setBackDesignView1(allStyleMaps);


    }

    @Override
    public void setStyleBGImage(int subCatID, boolean isFront) {
        if (subCatID == 1) {
            if (isFront) {

                ivStyle.setImageResource(R.drawable.cardigan_button_front);
            } else {

                ivStyle.setImageResource(R.drawable.cardigan_button_back2);
            }
        } else if (subCatID >= 27 && subCatID <= 36) {
            if (isFront) {

                ivStyle.setImageResource(R.drawable.brief_front);
            } else {

                ivStyle.setImageResource(R.drawable.brief_back);
            }
        }
    }

    @Override
    public void setPosName(String name) {
        if (name != null) {
            tvLotNo.setText("SPName: " + name);
        } else {
            tvLotNo.setText("  ");
        }

    }

    @Override
    public void onTotalGarmentCountReady(int totalQC) {

        tvTGEntry.setText("T.G.Entry: " + totalQC);

    }

    @Override
    public void onSendCSVDataIntoServerSuccessfully(BarcodeAPIResponseModel barcodeAPIResponseModel, List<QCDataModel> qcDataModelList) {
        Toast.makeText(this, barcodeAPIResponseModel.getMsg(), Toast.LENGTH_SHORT).show();
        if (qcDataModelList != null) {
            if (!qcDataModelList.isEmpty()) {
                mainPresenter.updateSyncData(qcDataModelList, barcodeAPIResponseModel);
            }
        }

    }

    @Override
    public void onUpdateLocalDBSuccessful(String msg) {
        if (msg != null) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        uuidsHared.saveLastSyncTime(getDate() + " " + getCurrentTime());
        setLastSyncData();


    }


    @Override
    public void onDeleteDataSuccessful(BarcodeAPIResponseModel barcodeAPIResponseModel, int lotNo, int garmentNo) {
        Toast.makeText(this, barcodeAPIResponseModel.getMsg(), Toast.LENGTH_SHORT).show();
        mainPresenter.deleteDataFromLocalDB(lotNo, garmentNo);

    }

    @Override
    public void onDeleteDataFailed(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSendCSVDataFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    public void showAlert(String message) {
        new SimpleAlertDialog(ManualMainActivity.this, message).show();
    }

    private void setBackDesignView1(List<StyleMapDataModel> allStyleMaps) {
        backGridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        backGridLayout.setColumnCount(15);
        backGridLayout.setRowCount(13);
        textViewList.clear();
        for (int i = 0; i < allStyleMaps.size(); i++) {
            TextView titleText;
            titleText = new TextView(getApplicationContext());
            titleText.setGravity(View.TEXT_ALIGNMENT_CENTER);
            //titleText.setText(i + " ");
            titleText.setId(i);

            backGridLayout.addView(titleText, i);
            GridLayout.LayoutParams param = new GridLayout.LayoutParams();


            if (uuidsHared.getScreenWidth() == 1223 && uuidsHared.getScreenHeight() == 800) {
                param.height = 40;
                param.width = 42;
            } else if (uuidsHared.getScreenWidth() == 1160 && uuidsHared.getScreenHeight() == 600) {
                param.height = 25;
                param.width = 40;
            } else if (uuidsHared.getScreenWidth() == 979 && uuidsHared.getScreenHeight() == 600) {
                param.height = 28;
                param.width = 33;
            } else {
                param.height = 40;
                param.width = 42;
            }


            param.rightMargin = 2;
            param.topMargin = 2;
            titleText.setLayoutParams(param);


/*
            titleText.setText(i + " ");
            if (allStyleMaps.get(i).getDefectPosIDBack() != 0) {
                titleText.setBackgroundColor(Color.RED);
            }
*/


            textViewList.add(titleText);
            int finalI = i;
            textViewList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mainPresenter.getDefectList(allStyleMaps.get(finalI).getDefectPosIDBack());
                    // Log.d(TAG, "onClick: " + allStyleMaps.get(finalI).getDefectPosIDBack());
                    mainPresenter.setPosName(allStyleMaps.get(finalI).getDefectPosIDBack());


                }
            });
        }

    }

    private void setFrontDesignView1(List<StyleMapDataModel> allStyleMaps) {
        frontGridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        frontGridLayout.setColumnCount(15);
        frontGridLayout.setRowCount(13);
        textViewList.clear();
        for (int i = 0; i < allStyleMaps.size(); i++) {
            TextView titleText;
            titleText = new TextView(getApplicationContext());
            titleText.setGravity(View.TEXT_ALIGNMENT_CENTER);
            //titleText.setText(i + " ");
            titleText.setId(i);

            frontGridLayout.addView(titleText, i);
            GridLayout.LayoutParams param = new GridLayout.LayoutParams();
            if (uuidsHared.getScreenWidth() == 1223 && uuidsHared.getScreenHeight() == 800) {
                param.height = 40;
                param.width = 42;
            } else if (uuidsHared.getScreenWidth() == 1160 && uuidsHared.getScreenHeight() == 600) {
                param.height = 25;
                param.width = 40;
            } else if (uuidsHared.getScreenWidth() == 979 && uuidsHared.getScreenHeight() == 600) {
                param.height = 28;
                param.width = 33;
            } else {
                param.height = 40;
                param.width = 42;
            }
            param.rightMargin = 2;
            param.topMargin = 2;
            titleText.setLayoutParams(param);


/*
            titleText.setText(i + " ");
            if (allStyleMaps.get(i).getDefectPosIDFront() != 0) {
                titleText.setBackgroundColor(Color.RED);
            }
*/

            textViewList.add(titleText);

            int finalI = i;
            textViewList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*if (allStyleMaps.get(finalI).getDefectPosIDFront() != 0) {*/
                    mainPresenter.getDefectList(allStyleMaps.get(finalI).getDefectPosIDFront());
                    //Log.d(TAG, "onClick: " + allStyleMaps.get(finalI).getDefectPosIDFront());
                    mainPresenter.setPosName(allStyleMaps.get(finalI).getDefectPosIDFront());

//                    }
                }
            });
        }


    }

    @OnClick({R.id.bt_front_side, R.id.bt_back_side})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_front_side:
                mainPresenter.setStyleImage(lotSetShared.getGarmentSettings().getStyleSubCategory(), lotSetShared.getGarmentSettings().getStyleCategory(), true);

                mainPresenter.getDefectList(0);
                lotSetShared.saveGarmentPosition(true);
                btFrontSide.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                btBackSide.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                frontGridLayout.setVisibility(View.VISIBLE);
                backGridLayout.setVisibility(View.GONE);
                break;
            case R.id.bt_back_side:
                mainPresenter.setStyleImage(lotSetShared.getGarmentSettings().getStyleSubCategory(), lotSetShared.getGarmentSettings().getStyleCategory(), false);


                mainPresenter.getDefectList(0);
                lotSetShared.saveGarmentPosition(false);
                btFrontSide.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                btBackSide.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

                backGridLayout.setVisibility(View.VISIBLE);
                frontGridLayout.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onDefectListReady(List<Defect> defectList, int posID) {


        gridLayoutManager = new GridLayoutManager(this, 3);
        rvDefects.setLayoutManager(gridLayoutManager);

        ManualDefectAdapter recycleViewAdapter = new ManualDefectAdapter(posID, defectList, this);


        rvDefects.setAdapter(recycleViewAdapter);


    }


    @Override
    public void onUpdateGarmentsCount() {
        tvGarmentCount.setText("Garments Count.: " + lotSetShared.getGarmentsCount());
        if (lotSetShared.getGarmentsCount() == 0) {
            btUndo.setVisibility(View.INVISIBLE);
        }
        mainPresenter.setTotalGarmentsCount();
    }


    private void removeDefectWhereZero() {
        for (int i = 0; i < qcDataModelList.size(); i++) {
            if (qcDataModelList.get(i).getDefect().getDefectCount() == 0) {
                qcDataModelList.remove(qcDataModelList.get(i));
            }
        }
    }


    @OnClick(R.id.bt_next_garment)
    public void onViewClicked() {
        removeDefectWhereZero();


        if (btNextCountGarment.getText().toString().isEmpty()) {

            lotSetShared.saveGarmentsCount();

            onUpdateGarmentsCount();
            mainPresenter.checkNoDefectEntry();

        } else {
            int value = Integer.parseInt(btNextCountGarment.getText().toString());
            int myMax = Integer.parseInt(lotSetShared.getGarmentSettings().getGarmentsTobeChecked()) - lotSetShared.getGarmentsCount();

            if (value > myMax) {
                showAlert("Your entry: " + value + " is greater than \nthe remaining PCs to be checked: " + myMax);

            } else {

                mainPresenter.addMultipleGarment(lotSetShared.getGarmentsCount() + 1, value, Integer.parseInt(lotSetShared.getGarmentSettings().getGarmentsTobeChecked()));

                onUpdateGarmentsCount();
                btNextCountGarment.setText("");
            }
        }


/*
        if (!qcDataModelList.isEmpty()) {

            mainPresenter.insertListIntoDB(qcDataModelList);
            mainPresenter.saveQCDataIntoCSV(qcDataModelList);
        }
*/

        Gson gson = new Gson();

        String log = gson.toJson(qcDataModelList);

        //Log.d(TAG, "onViewClicked: " + log);

        qcDataModelList.clear();
        mainPresenter.getDefectList(0);
        if (lotSetShared.getGarmentsCount() > 0) {
            btUndo.setVisibility(View.VISIBLE);
        }
        if (lotSetShared.getGarmentsCount() >= Integer.parseInt(lotSetShared.getGarmentSettings().getGarmentsTobeChecked())) {
            goNewLot();
        }
        mainPresenter.setTotalGarmentsCount();


        mainPresenter.sendCSVData();
//        mainPresenter.saveQCDataIntoCSV(new DBHelper(getApplicationContext()).getAllQCDataModels(uuidsHared.getTimeStamp(), getDate()));

    }

    private void goNewLot() {
        startActivity(new Intent(ManualMainActivity.this, FloorInfoActivity.class));
        qcDataModelList.clear();
        mainPresenter.getDefectList(0);
        //lotSetShared.clearSharedDB();
        uuidsHared.increaseLotNo();
        finish();
    }

    @OnClick({R.id.bt_new_lot, R.id.br_done})
    public void onViewitemClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_new_lot:
                startActivity(new Intent(ManualMainActivity.this, FloorInfoActivity.class));
                qcDataModelList.clear();
                mainPresenter.getDefectList(0);
                //lotSetShared.clearSharedDB();
                uuidsHared.increaseLotNo();
                finish();
                break;
            case R.id.br_done:
                startActivity(new Intent(ManualMainActivity.this, LoginActivity.class));
                qcDataModelList.clear();
                mainPresenter.getDefectList(0);
                lotSetShared.clearSharedDB();
                finish();
                break;
        }
    }

    @Override
    public void onAddQCdata(int posID, Defect defect) {
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        String name = dbHelper.getPosNameByID(posID);
        if (name != null) {
            if (lotSetShared.getGarmentSettings() != null) {
                GarmentsBundleSettings garmentsBundleSettings = lotSetShared.getGarmentSettings();
                QCDataModel qcDataModel = new QCDataModel(
                        lotSetShared.getGarmentsCount() + 1, lotSetShared.getGarmentPosition(), defect, garmentsBundleSettings.getColor());
                qcDataModel.setSize(lotSetShared.getGarmentSettings().getSize());
                qcDataModel.setDefectPos(name);
                qcDataModel.setBatchQty(lotSetShared.getGarmentSettings().getBatchQty());

                qcDataModel.setStyleCat(lotSetShared.getGarmentSettings().getStyleCategory()
                );
                qcDataModel.setLotNo(new UUIDSHared(getApplicationContext()).getLotNo());
                qcDataModel.setStyleSubCat(lotSetShared.getGarmentSettings().getStyleSubCategory());
                qcDataModel.setDate(getDate());
                qcDataModel.setTime(getCurrentTime());
                qcDataModel.setUserID(uuidsHared.getUserData().getUserName());
                qcDataModel.setLine(lotSetShared.getFloorSetting().getLine().getName());
                qcDataModel.setUnit(lotSetShared.getFloorSetting().getProductionUnit().getName());
                qcDataModel.setBuyerName(lotSetShared.getGarmentSettings().getBuyer().getName());
                qcDataModel.setPo(lotSetShared.getGarmentSettings().getPo().getName());
                qcDataModel.setSmv(lotSetShared.getGarmentSettings().getSmv());
                qcDataModel.setOperatorID(lotSetShared.getGarmentSettings().getOperatorID());
                qcDataModel.setMachineID(lotSetShared.getGarmentSettings().getMachineID());

                // Log.d(TAG, "onAddQCdata: " + qcDataModel.toString());

                qcDataModelList.add(qcDataModel);
                dbHelper.insertGarmentsDefectEntry(qcDataModel);


            }
        }


    }

    @Override
    public void onRemoveQCData(int posID, Defect defect) {
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        String name = dbHelper.getPosNameByID(posID);
        if (name != null) {
            if (lotSetShared.getGarmentSettings() != null) {
                GarmentsBundleSettings garmentsBundleSettings = lotSetShared.getGarmentSettings();
                QCDataModel qcDataModel = new QCDataModel(
                        lotSetShared.getGarmentsCount() + 1, lotSetShared.getGarmentPosition(), defect, garmentsBundleSettings.getColor());
                qcDataModel.setSize(lotSetShared.getGarmentSettings().getSize());
                qcDataModel.setDefectPos(name);
                qcDataModel.setBatchQty(lotSetShared.getGarmentSettings().getBatchQty());
                qcDataModel.setLotNo(new UUIDSHared(getApplicationContext()).getLotNo());
                qcDataModel.setStyleCat(lotSetShared.getGarmentSettings().getStyleCategory()
                );
                qcDataModel.setStyleSubCat(lotSetShared.getGarmentSettings().getStyleSubCategory());
                qcDataModel.setDate(getDate());
                qcDataModel.setTime(getCurrentTime());
                qcDataModel.setLine(lotSetShared.getFloorSetting().getLine().getName());
                qcDataModel.setUnit(lotSetShared.getFloorSetting().getProductionUnit().getName());
                qcDataModel.setBuyerName(lotSetShared.getGarmentSettings().getBuyer().getName());
                qcDataModel.setPo(lotSetShared.getGarmentSettings().getPo().getName());
                qcDataModel.setSmv(lotSetShared.getGarmentSettings().getSmv());
                qcDataModel.setOperatorID(lotSetShared.getGarmentSettings().getOperatorID());
                qcDataModel.setMachineID(lotSetShared.getGarmentSettings().getMachineID());
                qcDataModel.setUserID(uuidsHared.getUserData().getUserName());

                qcDataModelList.remove(qcDataModel);
                removeDefectWhereZero();
                dbHelper.deleteGarmentDefectEntry(qcDataModel);


            }
        }
    }


    @OnClick(R.id.bt_undo)
    public void onViewUndoClicked() {

        mainPresenter.undoAGarments(uuidsHared.getLotNo(), lotSetShared.getGarmentsCount());

        //mainPresenter.setTotalGarmentsCount();


    }

}