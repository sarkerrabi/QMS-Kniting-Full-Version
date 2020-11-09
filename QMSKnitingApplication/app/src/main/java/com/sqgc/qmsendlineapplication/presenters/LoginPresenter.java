/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/12/20 8:33 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/11/20 3:46 PM
 *
 */

package com.sqgc.qmsendlineapplication.presenters;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.sqgc.qmsendlineapplication.databases.DBHelper;
import com.sqgc.qmsendlineapplication.models.api_models.LoginResponse;
import com.sqgc.qmsendlineapplication.network.ApiClient;
import com.sqgc.qmsendlineapplication.scheduled_job.CleanerJobService;
import com.sqgc.qmsendlineapplication.services.ApiService;
import com.sqgc.qmsendlineapplication.sharedDB.CleanTotalSetShared;
import com.sqgc.qmsendlineapplication.views.LoginView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private final LoginView loginView;
    private final Context context;
    private final Activity activity;
    private ApiService apiService;
    private final DBHelper dbHelper;
    private final com.sqgc.qmsendlineapplication.preknit.database.DBHelper myDBHelper;
    private final CleanTotalSetShared cleanTotalSetShared;

    public LoginPresenter(LoginView loginView, Context context, Activity activity) {
        this.loginView = loginView;
        dbHelper = new DBHelper(context);
        myDBHelper = new com.sqgc.qmsendlineapplication.preknit.database.DBHelper(context);
        dbHelper.getWritableDatabase();
        myDBHelper.getWritableDatabase();
        cleanTotalSetShared = new CleanTotalSetShared(context);
        dbHelper.close();
        this.context = context;
        this.activity = activity;
        if (apiService == null) {
            apiService = ApiClient.getRetrofit().create(ApiService.class);
        }

    }

    public void loginNow(String username, String password) {

        apiService.loginRequest(username, password)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                if (response.body().isIsSuccessful()) {
                                    loginView.onLoginSuccess(response.body());
                                } else {
                                    loginView.onLoginFailed(response.body().getMessage());
                                }
                            } else {
                                loginView.onLoginFailed(response.message());
                            }

                        } else {
                            loginView.onLoginFailed(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        loginView.onLoginFailed(t.getMessage());

                    }
                });
    }

    public void setCleanTimer() {

/*        Calendar calendar = Calendar.getInstance();
        Log.e("TAGJOB", "setCleanTimer: "+ calendar.get(Calendar.HOUR_OF_DAY) );
        Log.e("TAGJOB", "setCleanTimer: "+ calendar.get(Calendar.MINUTE) );
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                13, 36, 0);*/


        if (cleanTotalSetShared.getDate() == null) {
            cleanTotalSetShared.saveDate(getDate());
            cleanTotalSetShared.cleanEveningShiftAlerm();
            cleanTotalSetShared.cleanMorningShiftAlerm();
            setMorningAlarm();
            setEveningAlarm();
        } else if (!cleanTotalSetShared.getDate().equals(getDate())) {
            cleanTotalSetShared.saveDate(getDate());
            cleanTotalSetShared.cleanEveningShiftAlerm();
            cleanTotalSetShared.cleanMorningShiftAlerm();
            setMorningAlarm();
            setEveningAlarm();

        }


    }

    private void setMorningAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        long time = calendar.getTimeInMillis();
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, CleanerJobService.class);
        intent.putExtra("CLEAN_TIME", 0); // 0 = morning 1= evening
        final int intent_id = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, intent_id, intent, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent);
        Log.e("TAGJOB", "Alarm is set morning ");


    }

    private void setEveningAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        long time = calendar.getTimeInMillis();
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, CleanerJobService.class);
        intent.putExtra("CLEAN_TIME", 1); // 0 = morning 1= evening
        final int intent_id = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, intent_id, intent, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent);
        Log.e("TAGJOB", "Alarm is set evening");


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


}
