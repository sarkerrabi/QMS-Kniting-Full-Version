/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/4/20 4:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/4/20 12:54 PM
 *
 */

package com.sqgc.qmsendlineapplication.models;

public class PO {
    private int id;
    private String name;
    private StyleSubCategory styleSubCategory;


    public PO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PO(int id, String name, int subCatID, String subCatName, int catID) {
        this.id = id;
        this.name = name;
        this.styleSubCategory = new StyleSubCategory(subCatID, subCatName, catID);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StyleSubCategory getStyleSubCategory() {
        return styleSubCategory;
    }

    public void setStyleSubCategory(StyleSubCategory styleSubCategory) {
        this.styleSubCategory = styleSubCategory;
    }
}
