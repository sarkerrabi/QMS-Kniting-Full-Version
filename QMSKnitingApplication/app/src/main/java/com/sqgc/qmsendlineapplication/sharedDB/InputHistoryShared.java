/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 9/12/20 9:50 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 9/12/20 9:50 AM
 *
 */

package com.sqgc.qmsendlineapplication.sharedDB;

import android.content.Context;
import android.content.SharedPreferences;

import com.sqgc.qmsendlineapplication.common.CommonSettings;

import java.util.HashSet;
import java.util.Set;

public class InputHistoryShared {
    private static final String PREFS_BUYER_SEARCH_HISTORY = "BuyerSearchHistory";
    private static final String PREFS_STYLE_SEARCH_HISTORY = "StyleSearchHistory";
    private static final String PREFS_PO_SEARCH_HISTORY = "PoSearchHistory";
    private static final String PREFS_COLOR_SEARCH_HISTORY = "ColorSearchHistory";
    private static final String PREFS_SIZE_SEARCH_HISTORY = "SizeSearchHistory";
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private Context context;


    public InputHistoryShared(Context context) {
        this.context = context;
        settings = context.getSharedPreferences(CommonSettings.PREFS_NAME, 0);
        editor = settings.edit();
    }


    //buyer
    public void saveBuyerPrefs(Set<String> buyerHistory) {
        editor.putStringSet(PREFS_BUYER_SEARCH_HISTORY, buyerHistory);
        editor.commit();
    }

    public Set<String> getBuyersName() {

        return new HashSet<String>(settings.getStringSet(PREFS_BUYER_SEARCH_HISTORY, new HashSet<String>()));
    }

    //style
    public void saveStylePrefs(Set<String> styleHistory) {
        editor.putStringSet(PREFS_STYLE_SEARCH_HISTORY, styleHistory);
        editor.commit();
    }

    public Set<String> getStylesName() {
        return new HashSet<String>(settings.getStringSet(PREFS_STYLE_SEARCH_HISTORY, new HashSet<String>()));
    }


    //PO
    public void savePOPrefs(Set<String> poHistory) {
        editor.putStringSet(PREFS_PO_SEARCH_HISTORY, poHistory);
        editor.commit();
    }

    public Set<String> getPOName() {

        return new HashSet<String>(settings.getStringSet(PREFS_PO_SEARCH_HISTORY, new HashSet<String>()));
    }

    //color
    public void saveColorPrefs(Set<String> colorHistory) {
        editor.putStringSet(PREFS_COLOR_SEARCH_HISTORY, colorHistory);
        editor.commit();
    }

    public Set<String> getColorName() {
        return new HashSet<String>(settings.getStringSet(PREFS_COLOR_SEARCH_HISTORY, new HashSet<String>()));
    }

    //size
    public void saveSizePrefs(Set<String> sizeHistory) {
        editor.putStringSet(PREFS_SIZE_SEARCH_HISTORY, sizeHistory);
        editor.commit();
    }

    public Set<String> getSizeName() {
        return new HashSet<String>(settings.getStringSet(PREFS_SIZE_SEARCH_HISTORY, new HashSet<String>()));
    }


}