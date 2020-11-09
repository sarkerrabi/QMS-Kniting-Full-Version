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

import com.sqgc.qmsendlineapplication.common.CommonSettings;

import static android.content.Context.MODE_PRIVATE;

public class CleanTotalSetShared {
    private final SharedPreferences.Editor editor;
    private final SharedPreferences sharedPreferences;
    private final Context context;

    public CleanTotalSetShared(Context context) {
        this.context = context;
        editor = context.getSharedPreferences(CommonSettings.sharedCleanPrefName, MODE_PRIVATE).edit();
        sharedPreferences = context.getSharedPreferences(CommonSettings.sharedCleanPrefName, MODE_PRIVATE);
    }

    public void saveDate(String date) {
        editor.putString("LAST_CLEAN_DATE", date);
        editor.apply();
    }

    public String getDate() {
        return sharedPreferences.getString("LAST_CLEAN_DATE", null);
    }

    public void saveMorningShiftCleanedSuccessfully() {
        editor.putBoolean("IS_CLEANED_MORNING", true);
        editor.apply();
    }

    public boolean isMorningShiftCleanedSuccessfully() {

        return sharedPreferences.getBoolean("IS_CLEANED_MORNING", false);
    }

    public void cleanMorningShiftAlerm() {
        editor.putBoolean("IS_CLEANED_MORNING", false);
        editor.apply();

    }

    public void saveEveningShiftCleanedSuccessfully() {
        editor.putBoolean("IS_CLEANED_EVENING", true);
        editor.apply();
    }

    public boolean isEveningShiftCleanedSuccessfully() {

        return sharedPreferences.getBoolean("IS_CLEANED_EVENING", false);
    }

    public void cleanEveningShiftAlerm() {
        editor.putBoolean("IS_CLEANED_EVENING", false);
        editor.apply();

    }


}
