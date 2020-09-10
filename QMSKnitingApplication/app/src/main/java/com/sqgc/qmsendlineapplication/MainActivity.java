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
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.novoda.merlin.Bindable;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;
import com.novoda.merlin.NetworkStatus;
import com.sqgc.qmsendlineapplication.adapters.DefectAdapter;
import com.sqgc.qmsendlineapplication.dialogs.SimpleAlertDialog;
import com.sqgc.qmsendlineapplication.models.Defect;
import com.sqgc.qmsendlineapplication.models.QCDataModel;
import com.sqgc.qmsendlineapplication.models.api_models.BarcodeAPIResponseModel;
import com.sqgc.qmsendlineapplication.models.db_models.QmsDetailsInformation;
import com.sqgc.qmsendlineapplication.models.db_models.StyleImageDataModel;
import com.sqgc.qmsendlineapplication.presenters.MainPresenter;
import com.sqgc.qmsendlineapplication.presenters.MainPresenterUpdated;
import com.sqgc.qmsendlineapplication.sharedDB.LotSetShared;
import com.sqgc.qmsendlineapplication.sharedDB.UUIDSHared;
import com.sqgc.qmsendlineapplication.views.MainView;
import com.sqgc.qmsendlineapplication.views.MainViewUpdated;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sqgc.qmsendlineapplication.common.CommonSettings.getCurrentTime;
import static com.sqgc.qmsendlineapplication.common.CommonSettings.getDate;

public class MainActivity extends AppCompatActivity implements MainView, Connectable, Bindable, Disconnectable, MainViewUpdated {

    List<TextView> textViewList = new ArrayList<>();

    GridLayout frontGridLayout, backGridLayout;
    @BindView(R.id.bt_front_side)
    Button btFrontSide;
    @BindView(R.id.bt_back_side)
    Button btBackSide;

    MainPresenter mainPresenter;
    MainPresenterUpdated mainPresenterUpdated;
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

    AlertDialog internetAlertDialog;
    private Merlin merlin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        lotSetShared = new LotSetShared(getApplicationContext());
        uuidsHared = new UUIDSHared(getApplicationContext());
        mainPresenter = new MainPresenter(this, getApplicationContext(), MainActivity.this);
        mainPresenterUpdated = new MainPresenterUpdated(this, getApplicationContext(), MainActivity.this);
        frontGridLayout = findViewById(R.id.grid_lay);
        backGridLayout = findViewById(R.id.grid_lay2);

        merlin = new Merlin.Builder()
                .withConnectableCallbacks()
                .withDisconnectableCallbacks()
                .withBindableCallbacks()
                .build(MainActivity.this);


        mainPresenter.setFullDesign(lotSetShared.getGarmentSettings().getStyleID());
        mainPresenter.setStyleImages(lotSetShared.getGarmentSettings().getStyleID(), true);

        if (lotSetShared.getGarmentsCount() == 0) {
            btUndo.setVisibility(View.INVISIBLE);
        }

        onUpdateGarmentsCount();

        mainPresenter.setTotalGarmentsCount();

        merlin.registerConnectable(this);
        merlin.registerDisconnectable(this);
        merlin.registerBindable(this);


    }

    @Override
    public void onStyleImagesReady(StyleImageDataModel styleImageDataModel, boolean isFront) {
        if (isFront) {
            Glide.with(this)
                    .load(styleImageDataModel.getFrontImagePath())
                    .placeholder(R.drawable.loader)
                    .thumbnail(Glide.with(this).load(R.drawable.loader))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivStyle);
        } else {
            Glide.with(this)
                    .load(styleImageDataModel.getBackImagePath())
                    .placeholder(R.drawable.loader)
                    .thumbnail(Glide.with(this).load(R.drawable.loader))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivStyle);

        }


    }

    @OnClick({R.id.bt_new_lot, R.id.bt_logout})
    public void onViewitemClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_new_lot:
                startActivity(new Intent(MainActivity.this, FloorInfoActivity.class));
                uuidsHared.increaseLotNo();
                finish();
                break;
            case R.id.bt_logout:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }


    @OnClick({R.id.bt_front_side, R.id.bt_back_side})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_front_side:

                mainPresenter.setStyleImages(lotSetShared.getGarmentSettings().getStyleID(), true);
                lotSetShared.saveGarmentPosition(true);
                btFrontSide.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                btBackSide.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                frontGridLayout.setVisibility(View.VISIBLE);
                backGridLayout.setVisibility(View.GONE);
                break;
            case R.id.bt_back_side:

                mainPresenter.setStyleImages(lotSetShared.getGarmentSettings().getStyleID(), false);
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

        DefectAdapter recycleViewAdapter = new DefectAdapter(defectList, this);

        rvDefects.setAdapter(recycleViewAdapter);

    }

    @Override
    public void onAddQCdata(Defect defect) {
        mainPresenter.insertDefectIntoDataTable(defect);

        //preknit
        mainPresenterUpdated.insertDefectIntoPreDataTable(defect);


    }

    @Override
    public void onRemoveQCData(Defect defect) {
        mainPresenter.deleteDefectInfoDataTable(defect);

        //preknit
        mainPresenterUpdated.deleteDefectInfoDataTable(defect);

    }

    @Override
    public void onUpdateGarmentsCount() {
        tvGarmentCount.setText("Garments Count.: " + lotSetShared.getGarmentsCount());
        if (lotSetShared.getGarmentsCount() == 0) {
            btUndo.setVisibility(View.INVISIBLE);
        }
        mainPresenter.setTotalGarmentsCount();
    }

    @Override
    public void onFullDesignReady(int styleID) {
        setBackDesignView();
        setFrontDesignView();
    }


    @Override
    public void setPosName(String name) {

    }

    @Override
    public void onTotalGarmentCountReady(int totalQC) {
        tvTGEntry.setText("T.G.Entry: " + totalQC);

    }

    @Override
    public void onSendCSVDataIntoServerSuccessfully(BarcodeAPIResponseModel barcodeAPIResponseModel, List<QmsDetailsInformation> qcDataModelList) {
        if (qcDataModelList != null) {
            if (!qcDataModelList.isEmpty()) {
                mainPresenter.updateSyncData(qcDataModelList, barcodeAPIResponseModel);
            }
        }
    }

    //preknit
    @Override
    public void onSendCSVDataIntoServerSuccessfullyUpdated(BarcodeAPIResponseModel barcodeAPIResponseModel, List<QCDataModel> qcDataModelList) {
        Toast.makeText(this, barcodeAPIResponseModel.getMsg(), Toast.LENGTH_SHORT).show();
        if (qcDataModelList != null) {
            if (!qcDataModelList.isEmpty()) {
                mainPresenterUpdated.updateSyncData(qcDataModelList, barcodeAPIResponseModel);
            }
        }
    }

    @Override
    public void onSendCSVDataFailedUpdated(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteDataSuccessfulUpdated(BarcodeAPIResponseModel barcodeAPIResponseModel, int lotNo, int garmentNo) {
        Toast.makeText(this, barcodeAPIResponseModel.getMsg(), Toast.LENGTH_SHORT).show();
        mainPresenterUpdated.deleteDataFromLocalDB(lotNo, garmentNo);
    }

    @Override
    public void onDeleteDataFailedUpdated(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSendCSVDataFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

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
        //preknit
        mainPresenterUpdated.deleteDataFromLocalDBUpdated(lotNo, garmentNo);
    }

    @Override
    public void onDeleteDataFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    //preknit
    @Override
    public void onUpdateLocalDBSuccessfulUpdated(String msg) {
        if (msg != null) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        uuidsHared.saveLastSyncTime(getDate() + " " + getCurrentTime());
        setLastSyncData();
    }

    //preknit
    @Override
    public void onUpdateGarmentsCountUpdated() {
        tvGarmentCount.setText("Garments Count.: " + lotSetShared.getGarmentsCount());
        if (lotSetShared.getGarmentsCount() == 0) {
            btUndo.setVisibility(View.INVISIBLE);
        }
        //preknit
        mainPresenterUpdated.setTotalGarmentsCount();
    }

    //preknit
    @Override
    public void onTotalGarmentCountReadyUpdated(int totalGarmentsEntryInADay) {
        tvTGEntry.setText("T.G.Entry: " + totalGarmentsEntryInADay);
    }

    @Override
    public void styleImageFailedToLoad() {
        Toast.makeText(this, "Style Image is not ready to load", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, FloorInfoActivity.class));
        uuidsHared.decreaseLotNo();
        finish();
    }

    private void setBackDesignView() {
        backGridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        backGridLayout.setColumnCount(15);
        backGridLayout.setRowCount(13);
        textViewList.clear();
        for (int i = 0; i < 195; i++) {
            TextView titleText;
            titleText = new TextView(getApplicationContext());
            titleText.setGravity(View.TEXT_ALIGNMENT_CENTER);
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

            textViewList.add(titleText);
            int finalI = i + 1;
            titleText.setText(finalI + " ");
            textViewList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mainPresenter.getDefectList(finalI, lotSetShared.getGarmentSettings().getStyleID(), 1);
                    // Log.d(TAG, "onClick: " + allStyleMaps.get(finalI).getDefectPosIDBack());
//                    mainPresenter.setPosName(allStyleMaps.get(finalI).getDefectPosIDBack());


                }
            });
        }

    }

    private void setFrontDesignView() {
        frontGridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        frontGridLayout.setColumnCount(15);
        frontGridLayout.setRowCount(13);
        textViewList.clear();
        for (int i = 0; i < 195; i++) {
            TextView titleText;
            titleText = new TextView(getApplicationContext());
            titleText.setGravity(View.TEXT_ALIGNMENT_CENTER);

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

            textViewList.add(titleText);

            int finalI = i + 1;
            titleText.setText(finalI + " ");
            textViewList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mainPresenter.getDefectList(finalI, lotSetShared.getGarmentSettings().getStyleID(), 0);
                    //Log.d(TAG, "onClick: " + allStyleMaps.get(finalI).getDefectPosIDFront());
//                    mainPresenter.setPosName(allStyleMaps.get(finalI).getDefectPosIDFront());

//                    }
                }
            });
        }


    }

    @OnClick(R.id.bt_undo)
    public void onViewUndoClicked() {

        //mainPresenter.undoAGarments(uuidsHared.getLotNo(), lotSetShared.getGarmentsCount());
        //preknit
        mainPresenterUpdated.undoAGarments(uuidsHared.getLotNo(), lotSetShared.getGarmentsCount());


    }

    @OnClick(R.id.bt_next_garment)
    public void onViewClicked() {


        if (btNextCountGarment.getText().toString().isEmpty()) {

            lotSetShared.saveGarmentsCount();

            onUpdateGarmentsCount();
            mainPresenter.checkNoDefectEntry();

            //preknit
            mainPresenterUpdated.checkNoDefectEntry();

        } else {
            int value = Integer.parseInt(btNextCountGarment.getText().toString());
            int myMax = Integer.parseInt(lotSetShared.getGarmentSettings().getGarmentsTobeChecked()) - lotSetShared.getGarmentsCount();

            if (value > myMax) {
                showAlert("Your entry: " + value + " is greater than \nthe remaining PCs to be checked: " + myMax);

            } else {

                mainPresenter.addMultipleGarment(lotSetShared.getGarmentsCount() + 1, value, Integer.parseInt(lotSetShared.getGarmentSettings().getGarmentsTobeChecked()));

                //preknit
                mainPresenterUpdated.addMultipleGarment(lotSetShared.getGarmentsCount() + 1, value, Integer.parseInt(lotSetShared.getGarmentSettings().getGarmentsTobeChecked()));


                onUpdateGarmentsCount();
                btNextCountGarment.setText("");
            }
        }

        mainPresenter.getDefectList(0, lotSetShared.getGarmentSettings().getStyleID(), 0);
        if (lotSetShared.getGarmentsCount() > 0) {
            btUndo.setVisibility(View.VISIBLE);
        }
        if (lotSetShared.getGarmentsCount() >= Integer.parseInt(lotSetShared.getGarmentSettings().getGarmentsTobeChecked())) {
            goNewLot();
        }
        mainPresenter.setTotalGarmentsCount();

        mainPresenterUpdated.sendCSVData();
//        mainPresenter.sendDefectData();

    }

    public void showAlert(String message) {
        new SimpleAlertDialog(MainActivity.this, message).show();
    }

    private void goNewLot() {
        startActivity(new Intent(MainActivity.this, FloorInfoActivity.class));

        mainPresenter.getDefectList(0, lotSetShared.getGarmentSettings().getStyleID(), 0);
        uuidsHared.increaseLotNo();
        finish();
    }

    private void setLastSyncData() {
        if (uuidsHared.getLastSyncTime() != null) {
            tvLastSync.setText(String.format("Last Syc with server at - %s", uuidsHared.getLastSyncTime()));
        }
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
//        mainPresenter.sendDefectData();

    }

    @Override
    public void onBind(NetworkStatus networkStatus) {
        if (!networkStatus.isAvailable()) {
            onDisconnect();
        }

    }

    @Override
    public void onDisconnect() {
        Toast.makeText(this, "NO INTERNET CONNECTION !!! PLEASE CHECK YOUR INTERNET CONNECTION", Toast.LENGTH_SHORT).show();
    }

}