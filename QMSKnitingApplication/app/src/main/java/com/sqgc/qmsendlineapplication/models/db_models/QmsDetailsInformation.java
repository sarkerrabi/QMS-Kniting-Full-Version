/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/22/20 2:05 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/22/20 12:35 PM
 *
 */

package com.sqgc.qmsendlineapplication.models.db_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QmsDetailsInformation {
    @SerializedName("DefectId")
    @Expose
    private Integer defectId = 0;
    @SerializedName("DefectPositionId")
    @Expose
    private Integer defectPositionId = 0;
    @SerializedName("DefectQuantity")
    @Expose
    private Integer defectQuantity = 0;
    @SerializedName("GarmentsNo")
    @Expose
    private String garmentsNo;
    @SerializedName("EntryDate")
    @Expose
    private String entryDate;
    @SerializedName("EntryTime")
    @Expose
    private String entryTime;

    public Integer getDefectId() {
        return defectId;
    }

    public void setDefectId(Integer defectId) {
        this.defectId = defectId;
    }

    public Integer getDefectPositionId() {
        return defectPositionId;
    }

    public void setDefectPositionId(Integer defectPositionId) {
        this.defectPositionId = defectPositionId;
    }

    public Integer getDefectQuantity() {
        return defectQuantity;
    }

    public void setDefectQuantity(Integer defectQuantity) {
        this.defectQuantity = defectQuantity;
    }

    public String getGarmentsNo() {
        return garmentsNo;
    }

    public void setGarmentsNo(String garmentsNo) {
        this.garmentsNo = garmentsNo;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }
}
