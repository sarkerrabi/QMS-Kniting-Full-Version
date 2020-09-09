/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/19/20 3:56 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/17/20 6:15 PM
 *
 */

package com.sqgc.qmsendlineapplication.models;

public class Defect {

    private int id = 0;
    private String name;
    private int defectPosID = 0;
    private String defectPosName;
    private int defectCount = 0;

    public Defect(int id, String name, int defectPosID, String defectPosName) {
        this.id = id;
        this.name = name;
        this.defectPosID = defectPosID;
        this.defectPosName = defectPosName;
    }

    public Defect(String name, int defectCount) {
        this.name = name;
        this.defectCount = defectCount;
    }

    public int getDefectPosID() {
        return defectPosID;
    }

    public void setDefectPosID(int defectPosID) {
        this.defectPosID = defectPosID;
    }

    public String getDefectPosName() {
        return defectPosName;
    }

    public void setDefectPosName(String defectPosName) {
        this.defectPosName = defectPosName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefectCount() {
        return defectCount;
    }

    public void setDefectCount(int defectCount) {
        this.defectCount = defectCount;
    }

    public void incrementDefectCount() {
        this.defectCount++;
    }
}
