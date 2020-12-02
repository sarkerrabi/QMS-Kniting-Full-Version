/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/17/20 5:11 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/17/20 5:11 PM
 *
 */

package com.sqgc.qmsendlineapplication.presenters;

import android.app.Activity;
import android.content.Context;

import com.sqgc.qmsendlineapplication.models.api_models.BarcodeAPIDataModel;
import com.sqgc.qmsendlineapplication.models.api_models.CommonData;
import com.sqgc.qmsendlineapplication.network.ApiClient;
import com.sqgc.qmsendlineapplication.preknit.database.DBHelper;
import com.sqgc.qmsendlineapplication.services.ApiService;
import com.sqgc.qmsendlineapplication.views.FloorInfoView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FloorInfoPresenter {
    private final FloorInfoView floorInfoView;
    private final Context context;
    private final Activity activity;
    private ApiService apiService;
    private final DBHelper myDBHelper;


    public FloorInfoPresenter(FloorInfoView floorInfoView, Context context, Activity activity) {
        this.floorInfoView = floorInfoView;
        this.context = context;
        this.activity = activity;
        myDBHelper = new DBHelper(context);
        if (apiService == null) {
            apiService = ApiClient.getRetrofit().create(ApiService.class);
        }
    }

    public void getLinesFromDB() {
        floorInfoView.onLineListReady(myDBHelper.getAllLines());
    }


    public void getModulesFromDB() {
        // floorInfoView.onModuleListReady(dbHelper.getAllModules());
    }


    public void getProductionUnitFromDB() {
        //floorInfoView.onProductionUnitsReady(dbHelper.getAllProductionUnit());
        Call<List<CommonData>> call = apiService.getProductionUnitListData(1);

        call.enqueue(new Callback<List<CommonData>>() {
            @Override
            public void onResponse(Call<List<CommonData>> call, Response<List<CommonData>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        floorInfoView.onProductionUnitListReady(response.body());
                    } else {
                        floorInfoView.onProductionUnitListFailed("Error QMS-101: Contact with developers");
                    }


                } else {
                    floorInfoView.onProductionUnitListFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<CommonData>> call, Throwable t) {
                floorInfoView.onProductionUnitListFailed(t.getMessage());

            }
        });

    }


    public void getBarcodeDataFromServer(String barcodeNo, Integer businessUnitId) {


        Call<BarcodeAPIDataModel> call = apiService.getBarcodeData(businessUnitId, getBarcodeNoFromString(barcodeNo));

        call.enqueue(new Callback<BarcodeAPIDataModel>() {
            @Override
            public void onResponse(Call<BarcodeAPIDataModel> call, Response<BarcodeAPIDataModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        floorInfoView.onBarcodeDetailsReady(response.body());
                    } else {
                        floorInfoView.onBarcodeFailed("Error QMS-101: Contact with developers");
                    }


                } else {
                    floorInfoView.onBarcodeFailed(response.message());
                }

            }


            @Override
            public void onFailure(Call<BarcodeAPIDataModel> call, Throwable t) {
                floorInfoView.onBarcodeFailed(t.getMessage());

            }
        });


    }

    private int getBarcodeNoFromString(String barcode) {
        String[] bcodes = barcode.split(",");
        if (barcode != null) {
            if (barcode.contains(",")) {
                return Integer.parseInt(bcodes[0]);
            } else {
                floorInfoView.onBarcodeFailed("Barcode no error. Please check barcode no. and try again.");
                return 0;

            }

        } else {
            floorInfoView.onBarcodeFailed("Barcode is blank. Please check barcode no. and try again.");
            return 0;
        }

    }


}
