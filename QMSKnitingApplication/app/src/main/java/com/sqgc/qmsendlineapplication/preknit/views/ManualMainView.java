/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 9/12/20 10:44 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 9/12/20 10:44 AM
 *
 */

package com.sqgc.qmsendlineapplication.preknit.views;

import com.sqgc.qmsendlineapplication.models.Defect;
import com.sqgc.qmsendlineapplication.models.QCDataModel;
import com.sqgc.qmsendlineapplication.models.StyleMapDataModel;
import com.sqgc.qmsendlineapplication.models.api_models.BarcodeAPIResponseModel;

import java.util.List;

public interface ManualMainView {
    void onDefectListReady(List<Defect> defectList, int posID);

    void onAddQCdata(int posID, Defect defect);

    void onRemoveQCData(int posID, Defect defect);

    void onUpdateGarmentsCount();

    void onFullDesignReady(List<StyleMapDataModel> allStyleMapByID);

    void setStyleBGImage(int subCatID, boolean isFront);

    void setPosName(String name);

    void onTotalGarmentCountReady(int totalQC);

    //api
    void onSendCSVDataIntoServerSuccessfully(BarcodeAPIResponseModel barcodeAPIResponseModel, List<QCDataModel> qcDataModelList);

    void onSendCSVDataFailed(String message);

    void onUpdateLocalDBSuccessful(String msg);

    void onDeleteDataSuccessful(BarcodeAPIResponseModel barcodeAPIResponseModel, int lotNo, int garmentNo);

    void onDeleteDataFailed(String message);
}
