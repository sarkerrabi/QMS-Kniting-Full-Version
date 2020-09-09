/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/19/20 3:56 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/19/20 1:46 PM
 *
 */

package com.sqgc.qmsendlineapplication.models.db_models;

public class QMSGarmentDefectInfoDataModel {

    private int id;
    private int defectId;
    private String defectName;

    private int defectPositionId;
    private String defectPositionName;

    private int defectQuantity;
    private int garmentsNo;

    private int lotNo;

    private String entryDate;
    private String entryTime;

    private int productionUnitID;
    private int qmsMasterKey;

    public int getGarmentsNo() {
        return garmentsNo;
    }

    public void setGarmentsNo(int garmentsNo) {
        this.garmentsNo = garmentsNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDefectId() {
        return defectId;
    }

    public void setDefectId(int defectId) {
        this.defectId = defectId;
    }

    public String getDefectName() {
        return defectName;
    }

    public void setDefectName(String defectName) {
        this.defectName = defectName;
    }

    public int getDefectPositionId() {
        return defectPositionId;
    }

    public void setDefectPositionId(int defectPositionId) {
        this.defectPositionId = defectPositionId;
    }

    public String getDefectPositionName() {
        return defectPositionName;
    }

    public void setDefectPositionName(String defectPositionName) {
        this.defectPositionName = defectPositionName;
    }

    public int getDefectQuantity() {
        return defectQuantity;
    }

    public void setDefectQuantity(int defectQuantity) {
        this.defectQuantity = defectQuantity;
    }


    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public int getProductionUnitID() {
        return productionUnitID;
    }

    public void setProductionUnitID(int productionUnitID) {
        this.productionUnitID = productionUnitID;
    }

    public int getQmsMasterKey() {
        return qmsMasterKey;
    }

    public void setQmsMasterKey(int qmsMasterKey) {
        this.qmsMasterKey = qmsMasterKey;
    }

    public int getLotNo() {
        return lotNo;
    }

    public void setLotNo(int lotNo) {
        this.lotNo = lotNo;
    }
}
