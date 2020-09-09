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

public class DefectListByPositionAPIResponse {

    @SerializedName("IsSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("Messgae")
    @Expose
    private String messgae;
    @SerializedName("DefectPositions")
    @Expose
    private List<DefectPosition> defectPositions = null;

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }

    public List<DefectPosition> getDefectPositions() {
        return defectPositions;
    }

    public void setDefectPositions(List<DefectPosition> defectPositions) {
        this.defectPositions = defectPositions;
    }

}
