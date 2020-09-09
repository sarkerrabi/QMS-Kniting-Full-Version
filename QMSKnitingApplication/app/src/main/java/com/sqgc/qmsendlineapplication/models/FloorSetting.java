/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/4/20 4:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/4/20 12:54 PM
 *
 */

package com.sqgc.qmsendlineapplication.models;

public class FloorSetting {
    private Line line;
    private Module module;
    private ProductionUnit productionUnit;

    public FloorSetting() {
    }

    public FloorSetting(Line line, Module module, ProductionUnit productionUnit) {
        this.line = line;
        this.module = module;
        this.productionUnit = productionUnit;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public ProductionUnit getProductionUnit() {
        return productionUnit;
    }

    public void setProductionUnit(ProductionUnit productionUnit) {
        this.productionUnit = productionUnit;
    }
}
