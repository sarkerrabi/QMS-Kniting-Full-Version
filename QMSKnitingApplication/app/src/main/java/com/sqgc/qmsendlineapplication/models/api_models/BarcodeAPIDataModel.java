/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/8/20 10:43 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/8/20 10:16 AM
 *
 */

package com.sqgc.qmsendlineapplication.models.api_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BarcodeAPIDataModel {

    @SerializedName("StyleID")
    @Expose
    public Integer styleID;
    @SerializedName("MasterBarcodeId")
    @Expose
    public Integer masterBarcodeId;
    @SerializedName("BundleNo")
    @Expose
    public Integer bundleNo;
    @SerializedName("Status")
    @Expose
    public Boolean status;
    @SerializedName("StyleName")
    @Expose
    public String styleName;
    @SerializedName("MachineId")
    @Expose
    public String machineId;
    @SerializedName("LotNo")
    @Expose
    public String lotNo;
    @SerializedName("BusinessUnitName")
    @Expose
    public String businessUnitName;
    @SerializedName("BarcodeNumber")
    @Expose
    public Object barcodeNumber;
    @SerializedName("BuyerID")
    @Expose
    public Integer buyerID;
    @SerializedName("BuyerName")
    @Expose
    public String buyerName;
    @SerializedName("BusinessUnitId")
    @Expose
    public Integer businessUnitId;
    @SerializedName("SelectDate")
    @Expose
    public Object selectDate;
    @SerializedName("PONumber")
    @Expose
    public String pONumber;
    @SerializedName("BundleSize")
    @Expose
    public String bundleSize;
    @SerializedName("Color")
    @Expose
    public String color;
    @SerializedName("BatchQuantity")
    @Expose
    public Integer batchQuantity;
    @SerializedName("CutNo")
    @Expose
    public Integer cutNo;
    @SerializedName("ShadeNO")
    @Expose
    public Integer shadeNO;
    @SerializedName("ProductName")
    @Expose
    public Object productName;
    @SerializedName("NoOfBundle")
    @Expose
    public Integer noOfBundle;
    @SerializedName("OperationSMV")
    @Expose
    public String operationSMV;

    public Integer getStyleID() {
        return styleID;
    }

    public void setStyleID(Integer styleID) {
        this.styleID = styleID;
    }

    public Integer getMasterBarcodeId() {
        return masterBarcodeId;
    }

    public void setMasterBarcodeId(Integer masterBarcodeId) {
        this.masterBarcodeId = masterBarcodeId;
    }

    public Integer getBundleNo() {
        return bundleNo;
    }

    public void setBundleNo(Integer bundleNo) {
        this.bundleNo = bundleNo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getBusinessUnitName() {
        return businessUnitName;
    }

    public void setBusinessUnitName(String businessUnitName) {
        this.businessUnitName = businessUnitName;
    }

    public Object getBarcodeNumber() {
        return barcodeNumber;
    }

    public void setBarcodeNumber(Object barcodeNumber) {
        this.barcodeNumber = barcodeNumber;
    }

    public Integer getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(Integer buyerID) {
        this.buyerID = buyerID;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Integer getBusinessUnitId() {
        return businessUnitId;
    }

    public void setBusinessUnitId(Integer businessUnitId) {
        this.businessUnitId = businessUnitId;
    }

    public Object getSelectDate() {
        return selectDate;
    }

    public void setSelectDate(Object selectDate) {
        this.selectDate = selectDate;
    }

    public String getpONumber() {
        return pONumber;
    }

    public void setpONumber(String pONumber) {
        this.pONumber = pONumber;
    }

    public String getBundleSize() {
        return bundleSize;
    }

    public void setBundleSize(String bundleSize) {
        this.bundleSize = bundleSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getBatchQuantity() {
        return batchQuantity;
    }

    public void setBatchQuantity(Integer batchQuantity) {
        this.batchQuantity = batchQuantity;
    }

    public Integer getCutNo() {
        return cutNo;
    }

    public void setCutNo(Integer cutNo) {
        this.cutNo = cutNo;
    }

    public Integer getShadeNO() {
        return shadeNO;
    }

    public void setShadeNO(Integer shadeNO) {
        this.shadeNO = shadeNO;
    }

    public Object getProductName() {
        return productName;
    }

    public void setProductName(Object productName) {
        this.productName = productName;
    }

    public Integer getNoOfBundle() {
        return noOfBundle;
    }

    public void setNoOfBundle(Integer noOfBundle) {
        this.noOfBundle = noOfBundle;
    }

    public String getOperationSMV() {
        return operationSMV;
    }

    public void setOperationSMV(String operationSMV) {
        this.operationSMV = operationSMV;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }
}
