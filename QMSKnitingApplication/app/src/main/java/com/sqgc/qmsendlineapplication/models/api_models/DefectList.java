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

public class DefectList {

    @SerializedName("DefectId")
    @Expose
    private Integer defectId;
    @SerializedName("DefectName")
    @Expose
    private String defectName;

    public Integer getDefectId() {
        return defectId;
    }

    public void setDefectId(Integer defectId) {
        this.defectId = defectId;
    }

    public String getDefectName() {
        return defectName;
    }

    public void setDefectName(String defectName) {
        this.defectName = defectName;
    }

}
