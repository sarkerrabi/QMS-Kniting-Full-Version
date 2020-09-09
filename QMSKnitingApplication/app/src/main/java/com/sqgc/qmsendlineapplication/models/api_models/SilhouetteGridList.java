/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/8/20 5:51 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/8/20 4:32 PM
 *
 */

package com.sqgc.qmsendlineapplication.models.api_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SilhouetteGridList {

    @SerializedName("GridNo")
    @Expose
    private Integer gridNo;
    @SerializedName("DefectPositionId")
    @Expose
    private Integer defectPositionId;
    @SerializedName("FrontorBack")
    @Expose
    private Integer frontorBack;
    @SerializedName("DefectPositionName")
    @Expose
    private String defectPositionName;
    @SerializedName("DefectList")
    @Expose
    private Object defectList;

    public Integer getGridNo() {
        return gridNo;
    }

    public void setGridNo(Integer gridNo) {
        this.gridNo = gridNo;
    }

    public Integer getDefectPositionId() {
        return defectPositionId;
    }

    public void setDefectPositionId(Integer defectPositionId) {
        this.defectPositionId = defectPositionId;
    }

    public Integer getFrontorBack() {
        return frontorBack;
    }

    public void setFrontorBack(Integer frontorBack) {
        this.frontorBack = frontorBack;
    }

    public String getDefectPositionName() {
        return defectPositionName;
    }

    public void setDefectPositionName(String defectPositionName) {
        this.defectPositionName = defectPositionName;
    }

    public Object getDefectList() {
        return defectList;
    }

    public void setDefectList(Object defectList) {
        this.defectList = defectList;
    }

}