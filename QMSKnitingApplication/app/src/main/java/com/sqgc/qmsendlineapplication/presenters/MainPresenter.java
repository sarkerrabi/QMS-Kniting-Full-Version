/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/24/20 2:58 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/24/20 2:03 PM
 *
 */

package com.sqgc.qmsendlineapplication.presenters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.sqgc.qmsendlineapplication.common.CommonSettings;
import com.sqgc.qmsendlineapplication.databases.DBHelper;
import com.sqgc.qmsendlineapplication.models.Defect;
import com.sqgc.qmsendlineapplication.models.api_models.BarcodeAPIResponseModel;
import com.sqgc.qmsendlineapplication.models.db_models.QMSGarmentDefectInfoDataModel;
import com.sqgc.qmsendlineapplication.models.db_models.QmsDetailsInformation;
import com.sqgc.qmsendlineapplication.models.db_models.SendDefectDataModel;
import com.sqgc.qmsendlineapplication.network.ApiClient;
import com.sqgc.qmsendlineapplication.services.ApiService;
import com.sqgc.qmsendlineapplication.sharedDB.LotSetShared;
import com.sqgc.qmsendlineapplication.sharedDB.UUIDSHared;
import com.sqgc.qmsendlineapplication.views.MainView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sqgc.qmsendlineapplication.common.CommonSettings.getDate;

public class MainPresenter {
    Activity activity;
    LotSetShared lotSetShared;
    UUIDSHared uuidsHared;
    private MainView mainView;
    private Context context;
    private DBHelper dbHelper;
    private ApiService apiService;


    public MainPresenter(MainView mainView, Context context, Activity activity) {
        this.mainView = mainView;
        this.activity = activity;
        this.context = context;
        dbHelper = new DBHelper(context);
        uuidsHared = new UUIDSHared(context);
        lotSetShared = new LotSetShared(context);
        if (apiService == null) {
            apiService = ApiClient.getRetrofit().create(ApiService.class);
        }
    }

    public void setStyleImages(int styleID, boolean isFront) {

        if (dbHelper.getImageByStyleID(styleID) != null && dbHelper.getImageByStyleID(styleID) != null) {
            mainView.onStyleImagesReady(dbHelper.getImageByStyleID(styleID), isFront);
        } else {
            mainView.styleImageFailedToLoad();
        }

    }


    public void setFullDesign(int styleID) {
        mainView.onFullDesignReady(styleID);
    }


    public void getDefectList(int gridNo, int styleID, int isBack) {

        mainView.onDefectListReady(dbHelper.getAllDefectByGrid(gridNo, styleID, isBack), styleID);

    }


    public void insertDefectIntoDataTable(Defect defect) {
        QMSGarmentDefectInfoDataModel qmsGarmentDefectInfoDataModel = new QMSGarmentDefectInfoDataModel();
        qmsGarmentDefectInfoDataModel.setDefectId(defect.getId());
        qmsGarmentDefectInfoDataModel.setDefectName(defect.getName());
        qmsGarmentDefectInfoDataModel.setDefectPositionId(defect.getDefectPosID());
        qmsGarmentDefectInfoDataModel.setDefectPositionName(defect.getDefectPosName());
        qmsGarmentDefectInfoDataModel.setDefectQuantity(defect.getDefectCount());
        qmsGarmentDefectInfoDataModel.setEntryDate(getDate());
        qmsGarmentDefectInfoDataModel.setEntryTime(CommonSettings.getCurrentTime());
        qmsGarmentDefectInfoDataModel.setQmsMasterKey(uuidsHared.getPKOfBarcode());
        qmsGarmentDefectInfoDataModel.setProductionUnitID(uuidsHared.getUserData().getProductionUnitId());
        qmsGarmentDefectInfoDataModel.setGarmentsNo(lotSetShared.getGarmentsCount() + 1);
        qmsGarmentDefectInfoDataModel.setLotNo(uuidsHared.getLotNo());

        dbHelper.insertQCGarmentsInfo(qmsGarmentDefectInfoDataModel);

    }

    public void deleteDefectInfoDataTable(Defect defect) {
        QMSGarmentDefectInfoDataModel qmsGarmentDefectInfoDataModel = new QMSGarmentDefectInfoDataModel();
        qmsGarmentDefectInfoDataModel.setDefectId(defect.getId());
        qmsGarmentDefectInfoDataModel.setDefectName(defect.getName());
        qmsGarmentDefectInfoDataModel.setDefectPositionId(defect.getDefectPosID());
        qmsGarmentDefectInfoDataModel.setDefectPositionName(defect.getDefectPosName());
        qmsGarmentDefectInfoDataModel.setDefectQuantity(defect.getDefectCount());
        qmsGarmentDefectInfoDataModel.setEntryDate(getDate());
        qmsGarmentDefectInfoDataModel.setEntryTime(CommonSettings.getCurrentTime());
        qmsGarmentDefectInfoDataModel.setQmsMasterKey(uuidsHared.getPKOfBarcode());
        qmsGarmentDefectInfoDataModel.setProductionUnitID(uuidsHared.getUserData().getProductionUnitId());
        qmsGarmentDefectInfoDataModel.setGarmentsNo(lotSetShared.getGarmentsCount() + 1);
        qmsGarmentDefectInfoDataModel.setLotNo(uuidsHared.getLotNo());

        dbHelper.deleteQCGarmentsInfo(qmsGarmentDefectInfoDataModel);
    }

    public void checkNoDefectEntry() {
        boolean isFound = dbHelper.isFoundGarmentNo(uuidsHared.getLotNo(), lotSetShared.getGarmentsCount(), getDate());

        if (!isFound) {
            QMSGarmentDefectInfoDataModel qmsGarmentDefectInfoDataModel = new QMSGarmentDefectInfoDataModel();
            qmsGarmentDefectInfoDataModel.setDefectId(0);
            qmsGarmentDefectInfoDataModel.setDefectName("na");
            qmsGarmentDefectInfoDataModel.setDefectPositionId(0);
            qmsGarmentDefectInfoDataModel.setDefectPositionName("na");
            qmsGarmentDefectInfoDataModel.setDefectQuantity(0);
            qmsGarmentDefectInfoDataModel.setEntryDate(getDate());
            qmsGarmentDefectInfoDataModel.setEntryTime(CommonSettings.getCurrentTime());
            qmsGarmentDefectInfoDataModel.setQmsMasterKey(uuidsHared.getPKOfBarcode());
            qmsGarmentDefectInfoDataModel.setProductionUnitID(uuidsHared.getUserData().getProductionUnitId());
            qmsGarmentDefectInfoDataModel.setGarmentsNo(lotSetShared.getGarmentsCount());
            qmsGarmentDefectInfoDataModel.setLotNo(uuidsHared.getLotNo());

            dbHelper.insertQCGarmentsInfo(qmsGarmentDefectInfoDataModel);

        }
    }


    public void setTotalGarmentsCount() {
        mainView.onTotalGarmentCountReady(dbHelper.totalGarmentsEntryInADay(getDate()));
    }


    public void sendDefectData() {
        //saveQCDataIntoCSV(dbHelper.getAllQCDataModels(uuidsHared.getTimeStamp(), getDate()));

        SendDefectDataModel sendDefectDataModel = new SendDefectDataModel();
        sendDefectDataModel.setProductionUnitId(uuidsHared.getUserData().getProductionUnitId());
        sendDefectDataModel.setQmsMasterKey(uuidsHared.getPKOfBarcode());

        List<QmsDetailsInformation> qcDataModelList = dbHelper.getAllDefectDataForSending(getDate());
        sendDefectDataModel.setQmsDetailsInformation(qcDataModelList);


        Gson gson = new Gson();
        String exportData = gson.toJson(sendDefectDataModel);
        //Log.e("TAG_CSV_JSON", "send_jsonData: " + exportData);

        Call<BarcodeAPIResponseModel> call = apiService.sendCSVData(exportData);
        call.enqueue(new Callback<BarcodeAPIResponseModel>() {
            @Override
            public void onResponse(Call<BarcodeAPIResponseModel> call, Response<BarcodeAPIResponseModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getIsSuccess()) {
                            Log.e("TAG_CSV_JSON", " sending done");
                            mainView.onSendCSVDataIntoServerSuccessfully(response.body(), qcDataModelList);
                        } else {
                            mainView.onSendCSVDataFailed("Failed to insert data");
                        }

                    } else {
                        mainView.onSendCSVDataFailed("ERROR 2001: Server Error!! Please contact with developers");
                    }

                } else {
                    //mainView.onSendCSVDataFailed("ERROR 2002: Server Error!! Please contact with developer");
                    mainView.onSendCSVDataFailed(response.message());
                }


            }

            @Override
            public void onFailure(Call<BarcodeAPIResponseModel> call, Throwable t) {
                mainView.onSendCSVDataFailed(t.getLocalizedMessage());
                //Toast.makeText(context, t.getCause()+"", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void updateSyncData(List<QmsDetailsInformation> qcDataModelList, BarcodeAPIResponseModel barcodeAPIResponseModel) {
        for (QmsDetailsInformation qcDataModel :
                qcDataModelList) {
            dbHelper.updateSyncData(qcDataModel);

        }
        mainView.onUpdateLocalDBSuccessful(barcodeAPIResponseModel.getMsg());


    }

    public void undoAGarments(int lotNo, int garmentNo) {

        List<QmsDetailsInformation> qcDataModelList = dbHelper.getAllQCDataModelsByGarmentNoForDeletingFromServer(garmentNo, lotNo, getDate());

        SendDefectDataModel sendDefectDataModel = new SendDefectDataModel();
        sendDefectDataModel.setProductionUnitId(uuidsHared.getUserData().getProductionUnitId());
        sendDefectDataModel.setQmsMasterKey(uuidsHared.getPKOfBarcode());

        sendDefectDataModel.setQmsDetailsInformation(qcDataModelList);
        Gson gson = new Gson();
        String undoGarmentsData = gson.toJson(sendDefectDataModel);


        Call<BarcodeAPIResponseModel> call = apiService.deleteDataFromServer(undoGarmentsData);
        call.enqueue(new Callback<BarcodeAPIResponseModel>() {
            @Override
            public void onResponse(Call<BarcodeAPIResponseModel> call, Response<BarcodeAPIResponseModel> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getIsSuccess()) {
                            mainView.onDeleteDataSuccessful(response.body(), lotNo, garmentNo);
                        } else {
                            mainView.onDeleteDataFailed("Failed to delete data");
                        }

                    } else {
                        mainView.onDeleteDataFailed("ERROR 2001: Server Error!! Please contact with developers");
                    }

                } else {
                    //mainView.onSendCSVDataFailed("ERROR 2002: Server Error!! Please contact with developer");
                    mainView.onDeleteDataFailed(response.message());
                }


            }

            @Override
            public void onFailure(Call<BarcodeAPIResponseModel> call, Throwable t) {
                mainView.onDeleteDataFailed(t.getMessage());

            }
        });

    }

    public void deleteDataFromLocalDB(int lotNo, int garmentNo) {
        String date = getDate();
        List<Integer> ids = dbHelper.getLastEntryID(lotNo, garmentNo, date);


        for (Integer id : ids) {
            dbHelper.deleteDefectsFromQCDefectInfo(id);

        }
        lotSetShared.decreaseGarmentsCount();
        mainView.onUpdateGarmentsCount();

    }

    public void addMultipleGarment(int myGarmentsNo, int noOfEntry, int maximum) {

        if (noOfEntry > maximum) {
            noOfEntry = maximum;

        }

        List<QMSGarmentDefectInfoDataModel> qmsGarmentDefectInfoDataModelList = dbHelper.getAllQCDataModelsByGarmentNo(myGarmentsNo, uuidsHared.getLotNo(), getDate());
        if (qmsGarmentDefectInfoDataModelList != null) {
            if (qmsGarmentDefectInfoDataModelList.size() > 0) {


                for (int i = 0; i < noOfEntry; i++) {
                    for (QMSGarmentDefectInfoDataModel qmsGarmentDefectInfoDataModel : qmsGarmentDefectInfoDataModelList) {
                        qmsGarmentDefectInfoDataModel.setGarmentsNo(myGarmentsNo + i);
                        lotSetShared.saveGarmentsCount();
                        dbHelper.insertQCGarmentsInfo(qmsGarmentDefectInfoDataModel);
                    }

                }
            } else {

                for (int i = 0; i < noOfEntry; i++) {
                    lotSetShared.saveGarmentsCount();
                    checkNoDefectEntry();
                }
                setTotalGarmentsCount();
            }
        }

    }


}
