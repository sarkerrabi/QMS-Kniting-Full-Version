/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 7/20/20 5:47 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/20/20 5:45 PM
 *
 */

package com.sqgc.qmsendlineapplication.models;

public class Size {
    private int id;
    private String name;

    public Size(int id, String name) {
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
}
