/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 9/11/20 1:01 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 9/11/20 1:01 AM
 *
 */

package com.sqgc.qmsendlineapplication.views;

import com.sqgc.qmsendlineapplication.models.QCDataModel;
import com.sqgc.qmsendlineapplication.models.api_models.BarcodeAPIResponseModel;

import java.util.List;

public interface MainViewUpdated {

    //api
    void onSendCSVDataIntoServerSuccessfullyUpdated(BarcodeAPIResponseModel barcodeAPIResponseModel, List<QCDataModel> qcDataModelList);

    void onSendCSVDataFailedUpdated(String message);


    void onDeleteDataSuccessfulUpdated(BarcodeAPIResponseModel barcodeAPIResponseModel, int lotNo, int garmentNo);

    void onDeleteDataFailedUpdated(String message);

    void onUpdateLocalDBSuccessfulUpdated(String msg);

    void onUpdateGarmentsCountUpdated();

    void onTotalGarmentCountReadyUpdated(int totalGarmentsEntryInADay);
}
