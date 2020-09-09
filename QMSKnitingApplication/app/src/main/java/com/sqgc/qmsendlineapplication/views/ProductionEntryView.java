/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/12/20 8:33 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/11/20 3:03 PM
 *
 */

package com.sqgc.qmsendlineapplication.views;

public interface ProductionEntryView {
    //new api
    void onPositionGridAPISavedDone();

    void onPositionGridAPIFailed(String message);

    void onDefectListAPISavedDone();

    void onDefectListAPIFailed(String message);
}
