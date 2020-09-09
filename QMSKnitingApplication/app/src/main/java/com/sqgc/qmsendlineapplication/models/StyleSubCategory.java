/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/4/20 4:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/4/20 12:54 PM
 *
 */

package com.sqgc.qmsendlineapplication.models;

public class StyleSubCategory {
    int id;
    String name;
    int subCatID;

    public StyleSubCategory(int id, String name, int subCatID) {
        this.id = id;
        this.name = name;
        this.subCatID = subCatID;
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

    public int getSubCatID() {
        return subCatID;
    }

    public void setSubCatID(int subCatID) {
        this.subCatID = subCatID;
    }
}
