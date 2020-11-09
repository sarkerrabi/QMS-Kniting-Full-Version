/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 11/7/20 10:21 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 11/7/20 10:21 AM
 *
 */

package com.sqgc.qmsendlineapplication.scheduled_job;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sqgc.qmsendlineapplication.LoginActivity;
import com.sqgc.qmsendlineapplication.models.QCDataModel;
import com.sqgc.qmsendlineapplication.models.api_models.BarcodeAPIResponseModel;
import com.sqgc.qmsendlineapplication.network.ApiClient;
import com.sqgc.qmsendlineapplication.preknit.database.DBHelper;
import com.sqgc.qmsendlineapplication.services.ApiServiceUpdated;
import com.sqgc.qmsendlineapplication.sharedDB.CleanTotalSetShared;
import com.sqgc.qmsendlineapplication.sharedDB.UUIDSHared;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CleanerJobService extends BroadcastReceiver {

    CleanTotalSetShared cleanTotalSetShared;
    private ApiServiceUpdated apiService;

    @Override
    public void onReceive(Context context, Intent intent) {
        cleanTotalSetShared = new CleanTotalSetShared(context);
        int clean_time = intent.getIntExtra("CLEAN_TIME", -1);
        if (clean_time == 0) {

            if (getDate().equals(cleanTotalSetShared.getDate())) {
                if (!cleanTotalSetShared.isMorningShiftCleanedSuccessfully()) {
/*                    Toast.makeText(context, "Do my cleaner job: !!!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                    Log.e("TAGJOB", "Do my cleaner job: !!!!!!!!!!!!!!!!!!");
                    MediaPlayer mediaPlayer = MediaPlayer.create(context,
                            Settings.System.DEFAULT_RINGTONE_URI);
                    mediaPlayer.start();*/
                    clearDBData(context);

                    cleanTotalSetShared.saveMorningShiftCleanedSuccessfully();
                }

            }
        } else {

            if (getDate().equals(cleanTotalSetShared.getDate())) {
                if (!cleanTotalSetShared.isEveningShiftCleanedSuccessfully()) {
/*                    Toast.makeText(context, "Do my cleaner job: !!!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                    Log.e("TAGJOB", "Do my cleaner job: !!!!!!!!!!!!!!!!!!");
                    MediaPlayer mediaPlayer = MediaPlayer.create(context,
                            Settings.System.DEFAULT_RINGTONE_URI);
                    mediaPlayer.start();*/
                    clearDBData(context);

                    cleanTotalSetShared.saveEveningShiftCleanedSuccessfully();
                }

            }

        }
    }

    private String getDate() {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = dateFormat.format(new Date()); // Find todays date

            return currentDate;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }


    private void clearDBData(Context context) {
        sendCSVData(context);

        com.sqgc.qmsendlineapplication.preknit.database.DBHelper dbHelper = new com.sqgc.qmsendlineapplication.preknit.database.DBHelper(context);
        dbHelper.clearEntryData();

        com.sqgc.qmsendlineapplication.databases.DBHelper myDB = new com.sqgc.qmsendlineapplication.databases.DBHelper(context);
        myDB.clearDBEntryData();

        Toast.makeText(context, "Data cleared successfully", Toast.LENGTH_SHORT).show();

        Log.e("TAGJOB", "clearDBData: " + context.getClass().getSimpleName());


        Intent i = new Intent();
        i.setClassName(context.getPackageName(), LoginActivity.class.getName());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }


    public void sendCSVData(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        UUIDSHared uuidsHared = new UUIDSHared(context);

        if (apiService == null) {
            apiService = ApiClient.getRetrofit().create(ApiServiceUpdated.class);
        }
        List<QCDataModel> qcDataModelList = dbHelper.getAllQCDataModelsForSendingToDB(uuidsHared.getTimeStamp(), getDate());

        Gson gson = new Gson();
        String exportData = gson.toJson(qcDataModelList);
//        Log.e("TAG_CSV_JSON", "sendjsonData: " + exportData);

        Call<BarcodeAPIResponseModel> call = apiService.sendCSVDataUpdated(exportData);
        call.enqueue(new Callback<BarcodeAPIResponseModel>() {
            @Override
            public void onResponse(Call<BarcodeAPIResponseModel> call, Response<BarcodeAPIResponseModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getIsSuccess()) {
                            Log.e("TAGJOB", "Sending Data successful");
                        } else {
                            Log.e("TAGJOB", "Failed to insert data");
                        }

                    } else {
                        Log.e("TAGJOB", "ERROR 2001: Server Error!! Please contact with developers");
                    }

                } else {
                    //mainView.onSendCSVDataFailed("ERROR 2002: Server Error!! Please contact with developer");
                    Log.e("TAGJOB", response.message());
                }


            }

            @Override
            public void onFailure(Call<BarcodeAPIResponseModel> call, Throwable t) {
                Log.e("TAGJOB", t.getLocalizedMessage());
                //Toast.makeText(context, t.getCause()+"", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
