/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/17/20 5:11 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/17/20 3:12 PM
 *
 */

package com.sqgc.qmsendlineapplication.models.api_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SavedPrimaryKeyDataResponse {
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("pk")
    @Expose
    private Integer pk;


    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }
}
