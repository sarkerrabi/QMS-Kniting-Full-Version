/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/4/20 10:24 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/4/20 7:10 PM
 *
 */

package com.sqgc.qmsendlineapplication.views;


import com.sqgc.qmsendlineapplication.models.Line;
import com.sqgc.qmsendlineapplication.models.api_models.BarcodeAPIDataModel;
import com.sqgc.qmsendlineapplication.models.api_models.CommonData;

import java.util.List;

public interface FloorInfoView {
/*    void onLineListReady(List<Line> lines);

    void onModuleListReady(List<Module> lines);

    void onProductionUnitsReady(List<ProductionUnit> lines);*/

    void onBarcodeDetailsReady(BarcodeAPIDataModel body);

    void onBarcodeFailed(String error_message);

    void onLineListReady(List<Line> allLines);

    void onProductionUnitListReady(List<CommonData> body);

    void onProductionUnitListFailed(String error_message);
}
