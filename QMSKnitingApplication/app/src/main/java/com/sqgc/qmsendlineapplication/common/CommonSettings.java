/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/19/20 3:56 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/19/20 1:36 PM
 *
 */

package com.sqgc.qmsendlineapplication.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonSettings {

    public static final String PREFS_NAME = "INPUT_HISTORY_SHARED_DB";
    //db static
    public static String sharedPrefName = "QMSELCacheDB";
    public static String sharedCleanPrefName = "DataCleanCacheDB";
    public static String sharedUUIDPrefName = "QMSELCacheDeviceID";
    public static String sharedSelectionSettings = "QMSELSettingsDB";

    //time settings
    public static int MORNING_HR = 8;
    public static int MORNING_MIN = 30;
    public static int EVENING_HR = 20;
    public static int EVENING_MIN = 30;

//    public static List<QCDataModel> qcDataModelList = new ArrayList<>();


    //1 = celsius 1
    //2 = celsius 2
    //3 = birichina
    //4 = colBlonk
    //5 = huze
    //API Static
    public static int businessUnit = 2;

    public static String getDate() {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = dateFormat.format(new Date()); // Find todays date

            return currentDate;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public static String getCurrentTime() {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            String currentTime = dateFormat.format(new Date()); // Find todays date

            return currentTime;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
