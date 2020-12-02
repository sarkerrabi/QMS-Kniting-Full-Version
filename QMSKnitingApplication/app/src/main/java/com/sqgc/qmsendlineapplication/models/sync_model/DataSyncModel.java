/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/24/20 4:32 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/24/20 2:03 PM
 *
 */

package com.sqgc.qmsendlineapplication.models.sync_model;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.sqgc.qmsendlineapplication.databases.DBHelper;
import com.sqgc.qmsendlineapplication.models.api_models.BarcodeAPIResponseModel;
import com.sqgc.qmsendlineapplication.models.db_models.QmsDetailsInformation;
import com.sqgc.qmsendlineapplication.models.db_models.SendDefectDataModel;
import com.sqgc.qmsendlineapplication.network.ApiClient;
import com.sqgc.qmsendlineapplication.services.ApiService;
import com.sqgc.qmsendlineapplication.sharedDB.LotSetShared;
import com.sqgc.qmsendlineapplication.sharedDB.UUIDSHared;
import com.sqgc.qmsendlineapplication.views.SyncView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sqgc.qmsendlineapplication.common.CommonSettings.getDate;


public class DataSyncModel {
    LotSetShared lotSetShared;
    UUIDSHared uuidsHared;
    private final Context context;
    private final Activity activity;
    private ApiService apiService;
    private final SyncView syncView;
    private final DBHelper dbHelper;
    private final com.sqgc.qmsendlineapplication.preknit.database.DBHelper preknitDBHelper;

    public DataSyncModel(Context context, Activity activity, SyncView syncView) {
        this.context = context;
        this.activity = activity;
        this.syncView = syncView;
        dbHelper = new DBHelper(context);
        preknitDBHelper = new com.sqgc.qmsendlineapplication.preknit.database.DBHelper(context);

        uuidsHared = new UUIDSHared(context);
        lotSetShared = new LotSetShared(context);
        if (apiService == null) {
            apiService = ApiClient.getRetrofit().create(ApiService.class);
        }
    }

    public void sendDefectData() {

        List<SyncKeys> syncKeys = dbHelper.getAllQMSMasterKeys();
        for (SyncKeys syncKey : syncKeys) {
//send data api starts

            if (syncKey != null) {


                SendDefectDataModel sendDefectDataModel = new SendDefectDataModel();
                sendDefectDataModel.setProductionUnitId(syncKey.getProductionUnitID());
                sendDefectDataModel.setQmsMasterKey(syncKey.getQmsMasterKey());


                List<QmsDetailsInformation> qcDataModelList = dbHelper.getAllDefectDataForSending(getDate());
                sendDefectDataModel.setQmsDetailsInformation(qcDataModelList);


                Gson gson = new Gson();
                String exportData = gson.toJson(sendDefectDataModel);
                Call<BarcodeAPIResponseModel> call = apiService.sendCSVData(exportData);
                call.enqueue(new Callback<BarcodeAPIResponseModel>() {
                    @Override
                    public void onResponse(Call<BarcodeAPIResponseModel> call, Response<BarcodeAPIResponseModel> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                if (response.body().getIsSuccess()) {
                                    Log.e("SYNC_DATA", " sending done");
                                    if (qcDataModelList != null) {
                                        if (!qcDataModelList.isEmpty()) {
                                            updateSyncData(qcDataModelList, response.body());
                                        }
                                    }
                                } else {
                                    syncView.onSendDataFailed("Failed to insert data");
                                }

                            } else {
                                syncView.onSendDataFailed("ERROR 2001: Server Error!! Please contact with developers");
                            }

                        } else {
                            //mainView.onSendCSVDataFailed("ERROR 2002: Server Error!! Please contact with developer");
                            syncView.onSendDataFailed(response.message());
                        }


                    }

                    @Override
                    public void onFailure(Call<BarcodeAPIResponseModel> call, Throwable t) {
                        syncView.onSendDataFailed(t.getLocalizedMessage());
                        //Toast.makeText(context, t.getCause()+"", Toast.LENGTH_SHORT).show();

                    }
                });

            }

//send data api ends


        }


    }


    public void updateSyncData(List<QmsDetailsInformation> qcDataModelList, BarcodeAPIResponseModel barcodeAPIResponseModel) {
        for (QmsDetailsInformation qcDataModel :
                qcDataModelList) {
            dbHelper.updateSyncData(qcDataModel);

        }
        syncView.onUpdateLocalDBDefectSuccessful(barcodeAPIResponseModel.getMsg());


    }

}
