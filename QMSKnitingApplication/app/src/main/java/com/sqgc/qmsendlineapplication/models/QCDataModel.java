/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/4/20 4:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/4/20 12:37 PM
 *
 */

package com.sqgc.qmsendlineapplication.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QCDataModel {
    private String uid_code;
    private int lotNo;
    private String unit;
    private String line;
    private String time;
    private String date;
    private String batchQty;
    private String buyerName;
    private String styleCat;
    private String styleSubCat;
    private long garmentNo;
    private String garmentPos; //front/Back
    private Defect defect;
    private String color;
    private String defectPos;
    private String size;
    private String po;
    private String smv;
    private int id;

    public QCDataModel() {
    }

    public QCDataModel(int garmentNo, String garmentPos, Defect defect, String color) {
        this.garmentNo = garmentNo;
        this.garmentPos = garmentPos;
        this.defect = defect;
        this.color = color;

    }

    public QCDataModel(String uid_code, int lotNo, String unit, String line, String time, String date, String batchQty, String buyerName, String styleCat, String styleSubCat, int garmentNo, String garmentPos, Defect defect, String color, String defectPos, String size, String po, String smv) {
        this.uid_code = uid_code;
        this.lotNo = lotNo;
        this.unit = unit;
        this.line = line;
        this.time = time;
        this.date = date;
        this.batchQty = batchQty;
        this.buyerName = buyerName;
        this.styleCat = styleCat;
        this.styleSubCat = styleSubCat;
        this.garmentNo = garmentNo;
        this.garmentPos = garmentPos;
        this.defect = defect;
        this.color = color;
        this.defectPos = defectPos;
        this.size = size;
        this.po = po;
        this.smv = smv;
    }

    /*
    public QCDataModel(String uid_code, int lotNo, String unit, String line, String time, String date, String batchQty, String buyerName, String styleCat, String styleSubCat, int garmentNo, String garmentPos, Defect defect, String color, String defectPos, String size, String po) {
        this.uid_code = uid_code;
        this.lotNo = lotNo;
        this.unit = unit;
        this.line = line;
        this.time = time;
        this.date = date;
        this.batchQty = batchQty;
        this.buyerName = buyerName;
        this.styleCat = styleCat;
        this.styleSubCat = styleSubCat;
        this.garmentNo = garmentNo;
        this.garmentPos = garmentPos;
        this.defect = defect;
        this.color = color;
        this.defectPos = defectPos;
        this.size = size;
        this.po = po;
    }*/

    private static String getDateForUniqueGarment() {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
            String currentDate = dateFormat.format(new Date()); // Find todays date

            return currentDate;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSmv() {
        return smv;
    }

    public void setSmv(String smv) {
        this.smv = smv;
    }

    public String getUid_code() {
        return uid_code;
    }

    public void setUid_code(String uid_code) {
        this.uid_code = uid_code;
    }

    public int getLotNo() {
        return lotNo;
    }

    public void setLotNo(int lotNo) {
        this.lotNo = lotNo;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDefectPos() {
        return defectPos;
    }

    public void setDefectPos(String defectPos) {
        this.defectPos = defectPos;
    }

    public String getBatchQty() {
        return batchQty;
    }

    public void setBatchQty(String batchQty) {
        this.batchQty = batchQty;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getStyleCat() {
        return styleCat;
    }

    public void setStyleCat(String styleCat) {
        this.styleCat = styleCat;
    }

    public String getStyleSubCat() {
        return styleSubCat;
    }

    public void setStyleSubCat(String styleSubCat) {
        this.styleSubCat = styleSubCat;
    }

    public long getGarmentNo() {
        return garmentNo;
    }

    public void setGarmentNo(int garmentNo) {
        this.garmentNo = garmentNo;
    }

    public String getGarmentPos() {
        return garmentPos;
    }

    public void setGarmentPos(String garmentPos) {
        this.garmentPos = garmentPos;
    }

    public Defect getDefect() {
        return defect;
    }

    public void setDefect(Defect defect) {
        this.defect = defect;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "QCDataModel{" +
                "unit='" + unit + '\'' +
                ", line='" + line + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", batchQty='" + batchQty + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", styleCat='" + styleCat + '\'' +
                ", styleSubCat='" + styleSubCat + '\'' +
                ", garmentNo=" + garmentNo +
                ", garmentPos='" + garmentPos + '\'' +
                ", defect=" + defect +
                ", color='" + color + '\'' +
                ", defectPos='" + defectPos + '\'' +
                ", size='" + size + '\'' +
                ", po='" + po + '\'' +
                '}';
    }

    public void setGarmentsID() {
        long temp_garmentNo = garmentNo;
        String tempGarmentID = getDateForUniqueGarment() + String.format("%02d", lotNo) + String.format("%03d", temp_garmentNo);
        this.garmentNo = Long.parseLong(tempGarmentID);

    }


    public String csvBody() {
        return unit + ".><." +
                line + ".><." +
                time + ".><." +
                date + ".><." +
                batchQty + ".><." +
                buyerName + ".><." +
                styleCat + ".><." +
                styleSubCat + ".><." +
                po + ".><." +
                getDateForUniqueGarment() + String.format("%02d", lotNo) + String.format("%03d", garmentNo) + ".><." +
                garmentPos + ".><." +
                defect.getName() + ".><." +
                defect.getDefectCount() + ".><." +
                color + ".><." +
                defectPos + ".><." +
                smv + ".><." +
                size + "--";
    }
}
