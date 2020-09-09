/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/17/20 5:11 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/17/20 2:25 PM
 *
 */

package com.sqgc.qmsendlineapplication.models.api_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("QmsUserId")
    @Expose
    private Integer qmsUserId;
    @SerializedName("IsSuccessful")
    @Expose
    private Boolean isSuccessful;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("BusinessUnit")
    @Expose
    private String businessUnit;
    @SerializedName("BusinessUnitId")
    @Expose
    private Integer businessUnitId;
    @SerializedName("ProductionUnit")
    @Expose
    private String productionUnit;
    @SerializedName("ProductionUnitId")
    @Expose
    private Integer productionUnitId;
    @SerializedName("ModuleName")
    @Expose
    private String moduleName;
    @SerializedName("LineNumber")
    @Expose
    private String lineNumber;
    @SerializedName("UserName")
    @Expose
    private String userName;

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public String getModuleName() {
        return moduleName;
    }

    public int getQmsUserId() {
        return qmsUserId;
    }

    public String getProductionUnit() {
        return productionUnit;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public boolean isIsSuccessful() {
        return isSuccessful;
    }


    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public Integer getBusinessUnitId() {
        return businessUnitId;
    }

    public Integer getProductionUnitId() {
        return productionUnitId;
    }
}