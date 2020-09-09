/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/25/20 4:42 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/25/20 4:42 PM
 *
 */

package com.sqgc.qmsendlineapplication.models.sync_model;

public class SyncKeys {
    private int productionUnitID = 0;
    private int qmsMasterKey = 0;

    public SyncKeys(int productionUnitID, int qmsMasterKey) {
        this.productionUnitID = productionUnitID;
        this.qmsMasterKey = qmsMasterKey;
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
}
