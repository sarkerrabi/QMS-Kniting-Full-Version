/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/17/20 5:11 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/17/20 5:11 PM
 *
 */

package com.sqgc.qmsendlineapplication.async_task_s;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.sqgc.qmsendlineapplication.databases.DBHelper;
import com.sqgc.qmsendlineapplication.dialogs.Loader;
import com.sqgc.qmsendlineapplication.models.api_models.BarcodeAPIDataModel;
import com.sqgc.qmsendlineapplication.models.api_models.ImageList;
import com.sqgc.qmsendlineapplication.models.api_models.PositionGridListAPIResponse;
import com.sqgc.qmsendlineapplication.models.api_models.SilhouetteGridList;
import com.sqgc.qmsendlineapplication.network.ApiClient;
import com.sqgc.qmsendlineapplication.services.ApiService;
import com.sqgc.qmsendlineapplication.views.ProductionEntryView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PositionGridTask extends AsyncTask<String, String, String> {

    ProductionEntryView productionEntryView;
    Loader loader;
    int styleID = 0;
    DBHelper dbHelper;
    Activity activity;
    Context context;
    private ApiService apiService;
    private BarcodeAPIDataModel barcodeAPIDataModel;

    public PositionGridTask(Activity activity, Context context, ProductionEntryView productionEntryView, int styleID, BarcodeAPIDataModel barcodeAPIDataModel) {
        this.activity = activity;
        this.context = context;
        this.productionEntryView = productionEntryView;
        this.styleID = styleID;
        this.barcodeAPIDataModel = barcodeAPIDataModel;
        dbHelper = new DBHelper(context);

        if (apiService == null) {
            apiService = ApiClient.getRetrofit().create(ApiService.class);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loader = new Loader(activity);
        loader.showDialog();
        //dialog = ProgressDialog.show(activity, null, "Initializing...");
    }


    @Override
    protected String doInBackground(String... strings) {

        apiService.getPosWithGridData(styleID, 2)
                .enqueue(new Callback<PositionGridListAPIResponse>() {
                    @Override
                    public void onResponse(Call<PositionGridListAPIResponse> call, Response<PositionGridListAPIResponse> response) {
                        if (response.isSuccessful()) {
                            PositionGridListAPIResponse positionGridListAPIResponse = response.body();
                            if (positionGridListAPIResponse != null) {
                                if (positionGridListAPIResponse.getStyleId() != 0) {
                                    dbHelper.deleteScheoutteeData(styleID);

                                    for (SilhouetteGridList silhouetteGrid :
                                            positionGridListAPIResponse.getSilhouetteGridList()) {
                                        dbHelper.insertScheoutteeData(
                                                silhouetteGrid,
                                                positionGridListAPIResponse.getStyleId(),
                                                positionGridListAPIResponse.getStyleName(),
                                                positionGridListAPIResponse.getSilhouetteName(),
                                                positionGridListAPIResponse.getSilhouetteId()
                                        );


                                    }
                                    String frontImage = "na";
                                    String backImage = "na";
                                    for (ImageList image :
                                            positionGridListAPIResponse.getImageList()) {
                                        if (image.getFrontBack() == 1) {
                                            frontImage = image.getSilhouetteImageDirectory();

                                        }
                                        if (image.getFrontBack() == 2) {
                                            backImage = image.getSilhouetteImageDirectory();
                                        }
                                    }
                                    dbHelper.insertImageData(positionGridListAPIResponse.getStyleId(), frontImage, backImage);


                                    productionEntryView.onPositionGridAPISavedDone();


                                } else {
                                    productionEntryView.onPositionGridAPIFailed("Style is not found");

                                }
                            } else {
                                productionEntryView.onPositionGridAPIFailed(response.message());
                            }
                        } else {
                            productionEntryView.onPositionGridAPIFailed(response.message());
                        }


                    }

                    @Override
                    public void onFailure(Call<PositionGridListAPIResponse> call, Throwable t) {
                        productionEntryView.onPositionGridAPIFailed(t.getMessage());
                        Log.e("TASK", "onFailure: " + t.getMessage());

                    }
                });


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        loader.hideDialog();
        DefectListByPositionTask defectListByPositionTask = new DefectListByPositionTask(activity, context, productionEntryView, styleID, barcodeAPIDataModel);
        defectListByPositionTask.execute();


    }
}
