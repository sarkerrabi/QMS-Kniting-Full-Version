/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/4/20 4:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/4/20 12:51 PM
 *
 */

package com.sqgc.qmsendlineapplication.sharedDB;

import android.content.Context;
import android.content.SharedPreferences;

import com.sqgc.qmsendlineapplication.common.CommonSettings;

import static android.content.Context.MODE_PRIVATE;

public class SelectionSetting {
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private Context context;

    public SelectionSetting(Context context) {
        this.context = context;
        editor = context.getSharedPreferences(CommonSettings.sharedSelectionSettings, MODE_PRIVATE).edit();
        sharedPreferences = context.getSharedPreferences(CommonSettings.sharedSelectionSettings, MODE_PRIVATE);
    }

    public void saveLineSelection(int position) {
        editor.putInt("FLOOR_POS", position);
        editor.apply();
    }

    public void saveProductionUnitSelection(int position) {
        editor.putInt("Production_Unit_POS", position);
        editor.apply();
    }

    public void saveStyleSelection(int position) {
        editor.putInt("STYLE_POS", position);
        editor.apply();
    }

    public void savePOSelection(int position) {
        editor.putInt("PO_POS", position);
        editor.apply();
    }

    public void saveColorSelection(int position) {
        editor.putInt("COLOR_POS", position);
        editor.apply();
    }

    public int getProductionUnitPos() {
        return sharedPreferences.getInt("Production_Unit_POS", 0);
    }

    public int getStylePos() {
        return sharedPreferences.getInt("STYLE_POS", 0);
    }

    public int getPOPos() {
        return sharedPreferences.getInt("PO_POS", 0);
    }

    public int getColorPos() {
        return sharedPreferences.getInt("COLOR_POS", 0);
    }

    public int getLinePos() {
        return sharedPreferences.getInt("FLOOR_POS", 0);
    }


}
