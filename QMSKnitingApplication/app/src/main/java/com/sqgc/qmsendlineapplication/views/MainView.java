/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/22/20 12:29 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/22/20 11:00 AM
 *
 */

package com.sqgc.qmsendlineapplication.views;


import com.sqgc.qmsendlineapplication.models.Defect;
import com.sqgc.qmsendlineapplication.models.api_models.BarcodeAPIResponseModel;
import com.sqgc.qmsendlineapplication.models.db_models.QmsDetailsInformation;
import com.sqgc.qmsendlineapplication.models.db_models.StyleImageDataModel;

import java.util.List;

public interface MainView {

    void onStyleImagesReady(StyleImageDataModel imageByStyleID, boolean isFront);

    void onDefectListReady(List<Defect> defectList, int posID);

    void onAddQCdata(Defect defect);

    void onRemoveQCData(Defect defect);

    void onUpdateGarmentsCount();

    void onFullDesignReady(int styleID);


    void setPosName(String name);

    void onTotalGarmentCountReady(int totalQC);

    //api
    void onSendCSVDataIntoServerSuccessfully(BarcodeAPIResponseModel barcodeAPIResponseModel, List<QmsDetailsInformation> qcDataModelList);

    void onSendCSVDataFailed(String message);

    void onUpdateLocalDBSuccessful(String msg);

    void onDeleteDataSuccessful(BarcodeAPIResponseModel barcodeAPIResponseModel, int lotNo, int garmentNo);

    void onDeleteDataFailed(String message);

    void styleImageFailedToLoad();


}
