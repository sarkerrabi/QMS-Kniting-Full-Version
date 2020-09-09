/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/10/20 4:13 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/10/20 10:17 AM
 *
 */

package com.sqgc.qmsendlineapplication.models.api_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageList {

    @SerializedName("SilhouetteImageId")
    @Expose
    private Integer silhouetteImageId;
    @SerializedName("SilhouetteImageName")
    @Expose
    private Object silhouetteImageName;
    @SerializedName("SilhouetteImageDirectory")
    @Expose
    private String silhouetteImageDirectory;
    @SerializedName("ServerFileName")
    @Expose
    private String serverFileName;
    @SerializedName("FrontBack")
    @Expose
    private Integer frontBack;

    public Integer getSilhouetteImageId() {
        return silhouetteImageId;
    }

    public void setSilhouetteImageId(Integer silhouetteImageId) {
        this.silhouetteImageId = silhouetteImageId;
    }

    public Object getSilhouetteImageName() {
        return silhouetteImageName;
    }

    public void setSilhouetteImageName(Object silhouetteImageName) {
        this.silhouetteImageName = silhouetteImageName;
    }

    public String getSilhouetteImageDirectory() {
        return silhouetteImageDirectory;
    }

    public void setSilhouetteImageDirectory(String silhouetteImageDirectory) {
        this.silhouetteImageDirectory = silhouetteImageDirectory;
    }

    public String getServerFileName() {
        return serverFileName;
    }

    public void setServerFileName(String serverFileName) {
        this.serverFileName = serverFileName;
    }

    public Integer getFrontBack() {
        return frontBack;
    }

    public void setFrontBack(Integer frontBack) {
        this.frontBack = frontBack;
    }

}