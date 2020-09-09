/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/19/20 3:56 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/17/20 5:53 PM
 *
 */

package com.sqgc.qmsendlineapplication.models.db_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SendDefectDataModel {

    @SerializedName("ProductionUnitId")
    @Expose
    private Integer productionUnitId;
    @SerializedName("QmsMasterKey")
    @Expose
    private Integer qmsMasterKey;
    @SerializedName("QmsDetailsInformation")
    @Expose
    private List<QmsDetailsInformation> qmsDetailsInformation = null;

    public Integer getProductionUnitId() {
        return productionUnitId;
    }

    public void setProductionUnitId(Integer productionUnitId) {
        this.productionUnitId = productionUnitId;
    }

    public Integer getQmsMasterKey() {
        return qmsMasterKey;
    }

    public void setQmsMasterKey(Integer qmsMasterKey) {
        this.qmsMasterKey = qmsMasterKey;
    }

    public List<QmsDetailsInformation> getQmsDetailsInformation() {
        return qmsDetailsInformation;
    }

    public void setQmsDetailsInformation(List<QmsDetailsInformation> qmsDetailsInformation) {
        this.qmsDetailsInformation = qmsDetailsInformation;
    }
}
