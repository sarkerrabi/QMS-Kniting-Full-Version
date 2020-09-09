/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/17/20 5:11 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/17/20 4:52 PM
 *
 */

package com.sqgc.qmsendlineapplication.sharedDB;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.sqgc.qmsendlineapplication.common.CommonSettings;
import com.sqgc.qmsendlineapplication.models.api_models.LoginResponse;

import java.util.UUID;

import static android.content.Context.MODE_PRIVATE;

public class UUIDSHared {
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private Context context;

    public UUIDSHared(Context context) {
        this.context = context;
        editor = context.getSharedPreferences(CommonSettings.sharedUUIDPrefName, MODE_PRIVATE).edit();
        sharedPreferences = context.getSharedPreferences(CommonSettings.sharedUUIDPrefName, MODE_PRIVATE);
    }

    public void saveUniqueID() throws Exception {
//        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String timeStamp = System.currentTimeMillis() + "";
        editor.putString("DEVICE_ID", createUID());
        editor.putInt("LotNO", 1);
        editor.putString("TIMESTAMP", timeStamp);
        editor.apply();
    }

    public String getUniqueID() {
        return sharedPreferences.getString("DEVICE_ID", null);
    }

    public int getLotNo() {
        return sharedPreferences.getInt("LotNO", 1);
    }


    public String getTimeStamp() {

        return sharedPreferences.getString("DEVICE_ID", null);
    }

    public void setLotNo() {
        editor.putInt("LotNO", 1);
        editor.apply();
    }


    public void increaseLotNo() {
        int lotNo = getLotNo();
        lotNo = lotNo + 1;
        editor.putInt("LotNO", lotNo);
        editor.apply();

    }

    public void decreaseLotNo() {
        int lotNo = getLotNo();
        if (lotNo > 0) {
            lotNo = lotNo - 1;
        } else {
            lotNo = 0;
        }
        editor.putInt("LotNO", lotNo);
        editor.apply();

    }

    private String createUID() throws Exception {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public void saveLastSyncTime(String date_time) {
        String timeStamp = System.currentTimeMillis() + "";
        editor.putString("LAST_SYNC_TIME", date_time);
        editor.apply();
    }

    public String getLastSyncTime() {

        return sharedPreferences.getString("LAST_SYNC_TIME", null);
    }

    public void saveUserData(LoginResponse loginResponse) {
        Gson gson = new Gson();
        String json = gson.toJson(loginResponse);
        editor.putString("user_data", json);
        editor.apply();

    }

    public LoginResponse getUserData() {
        Gson gson = new Gson();
        String userData = sharedPreferences.getString("user_data", null);//"No name defined" is the default value.

        if (userData != null) {
            LoginResponse loginResponse = gson.fromJson(userData, LoginResponse.class);

            return loginResponse;
        }


        return null;
    }

    public void savePKOfBarcode(int pk) {
        editor.putInt("BARCODE_PK", pk);
        editor.apply();
    }

    public int getPKOfBarcode() {
        return sharedPreferences.getInt("BARCODE_PK", 0);

    }


}
