/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/12/20 8:33 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/11/20 2:14 PM
 *
 */

package com.sqgc.qmsendlineapplication.models.db_models;

public class StyleImageDataModel {
    private int id;
    private int styleID;
    private String frontImagePath;
    private String backImagePath;

    public StyleImageDataModel(int id, int styleID, String frontImagePath, String backImagePath) {
        this.id = id;
        this.styleID = styleID;
        this.frontImagePath = frontImagePath;
        this.backImagePath = backImagePath;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStyleID() {
        return styleID;
    }

    public void setStyleID(int styleID) {
        this.styleID = styleID;
    }

    public String getFrontImagePath() {
        return frontImagePath;
    }

    public void setFrontImagePath(String frontImagePath) {
        this.frontImagePath = frontImagePath;
    }

    public String getBackImagePath() {
        return backImagePath;
    }

    public void setBackImagePath(String backImagePath) {
        this.backImagePath = backImagePath;
    }
}
