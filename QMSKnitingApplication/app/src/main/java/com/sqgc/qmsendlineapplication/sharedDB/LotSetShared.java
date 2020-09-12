/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/4/20 4:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/4/20 12:54 PM
 *
 */

package com.sqgc.qmsendlineapplication.sharedDB;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.sqgc.qmsendlineapplication.common.CommonSettings;
import com.sqgc.qmsendlineapplication.models.FloorSetting;
import com.sqgc.qmsendlineapplication.models.GarmentsBundleSettings;

import static android.content.Context.MODE_PRIVATE;

public class LotSetShared {
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private Context context;

    public LotSetShared(Context context) {
        this.context = context;
        editor = context.getSharedPreferences(CommonSettings.sharedPrefName, MODE_PRIVATE).edit();
        sharedPreferences = context.getSharedPreferences(CommonSettings.sharedPrefName, MODE_PRIVATE);
    }


    public void saveFloorSetting(FloorSetting floorSetting) {
        Gson gson = new Gson();
        String json = gson.toJson(floorSetting);
        editor.putString("floor_settings", json);
        editor.apply();
    }

    public FloorSetting getFloorSetting() {
        Gson gson = new Gson();
        String floor = sharedPreferences.getString("floor_settings", null);//"No name defined" is the default value.

        if (floor != null) {
            FloorSetting floorSetting = gson.fromJson(floor, FloorSetting.class);

            return floorSetting;
        }


        return null;
    }

    public void saveSettings(GarmentsBundleSettings garmentsBundleSettings) {
        Gson gson = new Gson();
        String json = gson.toJson(garmentsBundleSettings);
        editor.putString("bundle", json);
        editor.putInt("garmentCount", 0);
        editor.apply();

    }

    public GarmentsBundleSettings getGarmentSettings() {
        Gson gson = new Gson();
        String bundle = sharedPreferences.getString("bundle", null);//"No name defined" is the default value.

        if (bundle != null) {
            GarmentsBundleSettings garmentsBundleSettings = gson.fromJson(bundle, GarmentsBundleSettings.class);

            return garmentsBundleSettings;
        }


        return null;
    }

    public void saveGarmentsCount() {
        int garmentCount = sharedPreferences.getInt("garmentCount", 0);
        garmentCount = garmentCount + 1;
        editor.putInt("garmentCount", garmentCount);
        editor.apply();
    }

    public void saveGarmentsCount(int no) {
        int garmentCount = sharedPreferences.getInt("garmentCount", 0);
        garmentCount = garmentCount + no;
        editor.putInt("garmentCount", garmentCount);
        editor.apply();
    }

    public void decreaseGarmentsCount() {
        int garmentCount = sharedPreferences.getInt("garmentCount", 0);
        if (garmentCount != 0) {
            garmentCount = garmentCount - 1;
            editor.putInt("garmentCount", garmentCount);
            editor.apply();
        }
    }

    public int getGarmentsCount() {
        return sharedPreferences.getInt("garmentCount", 0);
    }

    public void saveGarmentPosition(boolean isFront) {
        if (isFront) {
            editor.putString("GARMENT_POS", "FRONT");
        } else {
            editor.putString("GARMENT_POS", "BACK");
        }

        editor.apply();
    }

    public String getGarmentPosition() {
        return sharedPreferences.getString("GARMENT_POS", "FRONT");
    }

    public void clearSharedDB() {
        sharedPreferences.edit().clear().apply();
    }

    public void saveManualOrScan(boolean isManual) {
        editor.putBoolean("IS_MANUAL", isManual);
        editor.apply();
    }

    public boolean getManualOrScan() {
        return sharedPreferences.getBoolean("IS_MANUAL", false);
    }


}
