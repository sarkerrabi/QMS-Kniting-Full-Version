/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 10/13/20 1:33 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/13/20 1:33 PM
 *
 */

package com.sqgc.qmsendlineapplication.models.api_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonData {

    @SerializedName("CommonId")
    @Expose
    private Integer commonId;
    @SerializedName("CommonName")
    @Expose
    private String commonName;

    public Integer getCommonId() {
        return commonId;
    }

    public void setCommonId(Integer commonId) {
        this.commonId = commonId;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }


}
