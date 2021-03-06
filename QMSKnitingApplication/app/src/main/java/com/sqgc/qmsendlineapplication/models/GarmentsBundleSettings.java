/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/12/20 1:58 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/12/20 10:07 AM
 *
 */

package com.sqgc.qmsendlineapplication.models;

public class GarmentsBundleSettings {
    private String styleCategory;
    private int styleID;
    private String styleSubCategory;
    private String color;
    private String size;
    private String batchQty;
    private String garmentsTobeChecked;
    private Buyer buyer;
    private PO po;
    private String smv;
    private String operatorID = "na";
    private String machineID = "na";
    private String serverLotNo = "na";


    public GarmentsBundleSettings() {
    }

    public String getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID;
    }

    public String getMachineID() {
        return machineID;
    }

    public void setMachineID(String machineID) {
        this.machineID = machineID;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public PO getPo() {
        return po;
    }

    public void setPo(PO po) {
        this.po = po;
    }

    public int getStyleID() {
        return styleID;
    }

    public void setStyleID(int styleID) {
        this.styleID = styleID;
    }

    public String getStyleSubCategory() {
        return styleSubCategory;
    }

    public void setStyleSubCategory(String styleSubCategory) {
        this.styleSubCategory = styleSubCategory;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBatchQty() {
        return batchQty;
    }

    public void setBatchQty(String batchQty) {
        this.batchQty = batchQty;
    }

    public String getGarmentsTobeChecked() {
        return garmentsTobeChecked;
    }

    public void setGarmentsTobeChecked(String garmentsTobeChecked) {
        this.garmentsTobeChecked = garmentsTobeChecked;
    }

    public String getSmv() {
        return smv;
    }

    public void setSmv(String smv) {
        this.smv = smv;
    }

    public String getStyleCategory() {
        return styleCategory;
    }

    public void setStyleCategory(String styleCategory) {
        this.styleCategory = styleCategory;
    }

    public String getServerLotNo() {
        return serverLotNo;
    }

    public void setServerLotNo(String serverLotNo) {
        this.serverLotNo = serverLotNo;
    }
}
