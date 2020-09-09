/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/10/20 4:13 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/10/20 12:09 PM
 *
 */

package com.sqgc.qmsendlineapplication.models.api_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DefectPosition {

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
    private List<DefectList> defectList = null;

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

    public List<DefectList> getDefectList() {
        return defectList;
    }

    public void setDefectList(List<DefectList> defectList) {
        this.defectList = defectList;
    }

}
