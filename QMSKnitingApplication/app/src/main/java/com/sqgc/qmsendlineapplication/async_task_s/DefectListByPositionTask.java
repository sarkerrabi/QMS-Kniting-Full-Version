/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/17/20 5:11 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/17/20 4:56 PM
 *
 */

package com.sqgc.qmsendlineapplication.async_task_s;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.sqgc.qmsendlineapplication.databases.DBHelper;
import com.sqgc.qmsendlineapplication.dialogs.Loader;
import com.sqgc.qmsendlineapplication.models.api_models.BarcodeAPIDataModel;
import com.sqgc.qmsendlineapplication.models.api_models.DefectListByPositionAPIResponse;
import com.sqgc.qmsendlineapplication.models.api_models.DefectPosition;
import com.sqgc.qmsendlineapplication.models.api_models.SavedPrimaryKeyDataResponse;
import com.sqgc.qmsendlineapplication.network.ApiClient;
import com.sqgc.qmsendlineapplication.services.ApiService;
import com.sqgc.qmsendlineapplication.sharedDB.LotSetShared;
import com.sqgc.qmsendlineapplication.sharedDB.UUIDSHared;
import com.sqgc.qmsendlineapplication.views.ProductionEntryView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DefectListByPositionTask extends AsyncTask<String, String, String> {

    ProductionEntryView productionEntryView;
    Loader loader;
    int styleID = 0;
    DBHelper dbHelper;
    private ApiService apiService;
    private Activity activity;
    private Context context;
    private LotSetShared lotSetShared;
    private UUIDSHared uuidsHared;
    private BarcodeAPIDataModel barcodeAPIDataModel;

    public DefectListByPositionTask(Activity activity, Context context, ProductionEntryView productionEntryView, int styleID, BarcodeAPIDataModel barcodeAPIDataModel) {
        this.activity = activity;
        this.context = context;
        this.productionEntryView = productionEntryView;
        this.styleID = styleID;
        this.barcodeAPIDataModel = barcodeAPIDataModel;
        this.dbHelper = new DBHelper(context);
        this.lotSetShared = new LotSetShared(context);
        this.uuidsHared = new UUIDSHared(context);
        if (apiService == null) {
            apiService = ApiClient.getRetrofit().create(ApiService.class);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loader = new Loader(activity);
        loader.showDialog();
    }


    @Override
    protected String doInBackground(String... strings) {
        apiService.getDefectListData(styleID, 2)
                .enqueue(new Callback<DefectListByPositionAPIResponse>() {
                    @Override
                    public void onResponse(Call<DefectListByPositionAPIResponse> call, Response<DefectListByPositionAPIResponse> response) {
                        if (response.isSuccessful()) {
                            DefectListByPositionAPIResponse defectListByPositionAPIResponse = response.body();
                            if (defectListByPositionAPIResponse != null) {
                                if (defectListByPositionAPIResponse.getIsSuccess()) {

                                    List<DefectPosition> defectPositionList = defectListByPositionAPIResponse.getDefectPositions();

                                    for (DefectPosition defectPosition : defectPositionList) {
                                        dbHelper.deleteDefectList(defectPosition.getDefectPositionId());
                                        for (int i = 0; i < defectPosition.getDefectList().size(); i++) {
                                            dbHelper.insertDefectList(defectPosition, defectPosition.getDefectList().get(i));
                                        }
                                    }


                                } else {
                                    productionEntryView.onDefectListAPIFailed("Style is not found");

                                }
                            } else {
                                productionEntryView.onDefectListAPIFailed(response.message());
                            }
                        } else {
                            productionEntryView.onDefectListAPIFailed(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<DefectListByPositionAPIResponse> call, Throwable t) {
                        productionEntryView.onDefectListAPIFailed(t.getMessage());


                    }
                });

        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        apiService.getSavedPKdata(uuidsHared.getUserData().getQmsUserId(), barcodeAPIDataModel.getMasterBarcodeId(),
                uuidsHared.getUniqueID(), barcodeAPIDataModel.getOperationSMV()).enqueue(new Callback<SavedPrimaryKeyDataResponse>() {
            @Override
            public void onResponse(Call<SavedPrimaryKeyDataResponse> call, Response<SavedPrimaryKeyDataResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess()) {
                        uuidsHared.savePKOfBarcode(response.body().getPk());
                        productionEntryView.onDefectListAPISavedDone();
                    } else {
                        productionEntryView.onDefectListAPIFailed(response.message());
                    }
                } else {
                    productionEntryView.onDefectListAPIFailed(response.message());
                }

            }

            @Override
            public void onFailure(Call<SavedPrimaryKeyDataResponse> call, Throwable t) {
                productionEntryView.onDefectListAPIFailed(t.getMessage());

            }
        });


        loader.hideDialog();

    }

}
