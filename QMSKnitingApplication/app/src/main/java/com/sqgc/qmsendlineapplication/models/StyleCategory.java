/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 7/20/20 5:47 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/20/20 5:45 PM
 *
 */

package com.sqgc.qmsendlineapplication.models;

import java.util.ArrayList;
import java.util.List;

public class StyleCategory {
    int id;
    String name;

    List<StyleSubCategory> subCategoryList = new ArrayList<>();

    public StyleCategory(int id, String name) {
        this.id = id;
        this.name = name;
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

    public List<StyleSubCategory> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<StyleSubCategory> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }
}
