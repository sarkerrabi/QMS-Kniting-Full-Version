/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/8/20 5:51 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/8/20 5:51 PM
 *
 */

package com.sqgc.qmsendlineapplication.models.api_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PositionGridListAPIResponse {

    @SerializedName("StyleId")
    @Expose
    private Integer styleId;
    @SerializedName("StyleName")
    @Expose
    private String styleName;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;
    @SerializedName("SilhouetteId")
    @Expose
    private Integer silhouetteId;
    @SerializedName("SilhouetteName")
    @Expose
    private String silhouetteName;
    @SerializedName("BaseServerPath")
    @Expose
    private String baseServerPath;
    @SerializedName("ImageList")
    @Expose
    private List<ImageList> imageList = null;
    @SerializedName("SilhouetteGridList")
    @Expose
    private List<SilhouetteGridList> silhouetteGridList = null;

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getSilhouetteId() {
        return silhouetteId;
    }

    public void setSilhouetteId(Integer silhouetteId) {
        this.silhouetteId = silhouetteId;
    }

    public String getSilhouetteName() {
        return silhouetteName;
    }

    public void setSilhouetteName(String silhouetteName) {
        this.silhouetteName = silhouetteName;
    }

    public String getBaseServerPath() {
        return baseServerPath;
    }

    public void setBaseServerPath(String baseServerPath) {
        this.baseServerPath = baseServerPath;
    }

    public List<ImageList> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageList> imageList) {
        this.imageList = imageList;
    }

    public List<SilhouetteGridList> getSilhouetteGridList() {
        return silhouetteGridList;
    }

    public void setSilhouetteGridList(List<SilhouetteGridList> silhouetteGridList) {
        this.silhouetteGridList = silhouetteGridList;
    }

}