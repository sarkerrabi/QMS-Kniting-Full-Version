/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/4/20 4:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/4/20 12:47 PM
 *
 */

package com.sqgc.qmsendlineapplication.models;

public class StyleMapDataModel {
    private int id;
    private int pixNo = 0;
    private int defectPosIDFront = 0;
    private int defectPosIDBack = 0;
    private int styleSubCatID = 0;

    public StyleMapDataModel(int id, int pixNo, int defectPosIDFront, int defectPosIDBack, int styleSubCatID) {
        this.id = id;
        this.pixNo = pixNo;
        this.defectPosIDFront = defectPosIDFront;
        this.defectPosIDBack = defectPosIDBack;
        this.styleSubCatID = styleSubCatID;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPixNo() {
        return pixNo;
    }

    public void setPixNo(int pixNo) {
        this.pixNo = pixNo;
    }

    public int getDefectPosIDFront() {
        return defectPosIDFront;
    }

    public void setDefectPosIDFront(int defectPosIDFront) {
        this.defectPosIDFront = defectPosIDFront;
    }

    public int getDefectPosIDBack() {
        return defectPosIDBack;
    }

    public void setDefectPosIDBack(int defectPosIDBack) {
        this.defectPosIDBack = defectPosIDBack;
    }

    public int getStyleSubCatID() {
        return styleSubCatID;
    }

    public void setStyleSubCatID(int styleSubCatID) {
        this.styleSubCatID = styleSubCatID;
    }
}
