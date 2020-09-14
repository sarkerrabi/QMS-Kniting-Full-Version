/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 9/11/20 12:39 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 9/11/20 12:39 AM
 *
 */

package com.sqgc.qmsendlineapplication.presenters;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sqgc.qmsendlineapplication.models.Defect;
import com.sqgc.qmsendlineapplication.models.GarmentsBundleSettings;
import com.sqgc.qmsendlineapplication.models.QCDataModel;
import com.sqgc.qmsendlineapplication.models.api_models.BarcodeAPIResponseModel;
import com.sqgc.qmsendlineapplication.network.ApiClient;
import com.sqgc.qmsendlineapplication.preknit.database.DBHelper;
import com.sqgc.qmsendlineapplication.services.ApiServiceUpdated;
import com.sqgc.qmsendlineapplication.sharedDB.LotSetShared;
import com.sqgc.qmsendlineapplication.sharedDB.UUIDSHared;
import com.sqgc.qmsendlineapplication.views.MainViewUpdated;

import net.ozaydin.serkan.easy_csv.EasyCsv;
import net.ozaydin.serkan.easy_csv.FileCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sqgc.qmsendlineapplication.common.CommonSettings.getCurrentTime;
import static com.sqgc.qmsendlineapplication.common.CommonSettings.getDate;

public class MainPresenterUpdated {
    public final int WRITE_PERMISSON_REQUEST_CODE = 1;
    EasyCsv easyCsv;
    Activity activity;
    LotSetShared lotSetShared;
    UUIDSHared uuidsHared;
    List<String> headerList;
    List<String> dataList;
    private MainViewUpdated mainView;
    private Context context;
    private DBHelper dbHelper;
    private ApiServiceUpdated apiService;


    public MainPresenterUpdated(MainViewUpdated mainView, Context context, Activity activity) {
        this.mainView = mainView;
        this.activity = activity;
        this.context = context;
        dbHelper = new DBHelper(context);
        easyCsv = new EasyCsv(activity);
        uuidsHared = new UUIDSHared(context);
        lotSetShared = new LotSetShared(context);
        if (apiService == null) {
            apiService = ApiClient.getRetrofit().create(ApiServiceUpdated.class);
        }
    }


    public void insertDefectIntoPreDataTable(Defect defect) {
        DBHelper dbHelper = new DBHelper(context);
        if (lotSetShared.getGarmentSettings() != null) {
            GarmentsBundleSettings garmentsBundleSettings = lotSetShared.getGarmentSettings();
            QCDataModel qcDataModel = new QCDataModel(
                    lotSetShared.getGarmentsCount() + 1, lotSetShared.getGarmentPosition(), defect, garmentsBundleSettings.getColor());
            qcDataModel.setSize(lotSetShared.getGarmentSettings().getSize());
            qcDataModel.setDefectPos(defect.getDefectPosName());
            qcDataModel.setBatchQty(lotSetShared.getGarmentSettings().getBatchQty());

            qcDataModel.setStyleCat("Cardigan");
            qcDataModel.setLotNo(new UUIDSHared(context).getLotNo());
            qcDataModel.setStyleSubCat(lotSetShared.getGarmentSettings().getStyleSubCategory());
            qcDataModel.setDate(getDate());
            qcDataModel.setTime(getCurrentTime());
            qcDataModel.setUserID(uuidsHared.getUserData().getUserName());

            qcDataModel.setLine(lotSetShared.getFloorSetting().getLine().getName());
            qcDataModel.setUnit(lotSetShared.getFloorSetting().getProductionUnit().getName());
            qcDataModel.setBuyerName(lotSetShared.getGarmentSettings().getBuyer().getName());
            qcDataModel.setPo(lotSetShared.getGarmentSettings().getPo().getName());
            qcDataModel.setSmv(lotSetShared.getGarmentSettings().getSmv());
            qcDataModel.setOperatorID(lotSetShared.getGarmentSettings().getOperatorID());
            qcDataModel.setMachineID(lotSetShared.getGarmentSettings().getMachineID());

            // Log.d(TAG, "onAddQCdata: " + qcDataModel.toString());

            dbHelper.insertGarmentsDefectEntry(qcDataModel);


        }


    }

    public void deleteDefectInfoDataTable(Defect defect) {
        DBHelper dbHelper = new DBHelper(context);

        if (lotSetShared.getGarmentSettings() != null) {
            GarmentsBundleSettings garmentsBundleSettings = lotSetShared.getGarmentSettings();
            QCDataModel qcDataModel = new QCDataModel(
                    lotSetShared.getGarmentsCount() + 1, lotSetShared.getGarmentPosition(), defect, garmentsBundleSettings.getColor());
            qcDataModel.setSize(lotSetShared.getGarmentSettings().getSize());
            qcDataModel.setDefectPos(defect.getDefectPosName());
            qcDataModel.setBatchQty(lotSetShared.getGarmentSettings().getBatchQty());
            qcDataModel.setLotNo(new UUIDSHared(context).getLotNo());
            qcDataModel.setStyleCat("Cardigan");
            qcDataModel.setStyleSubCat(lotSetShared.getGarmentSettings().getStyleSubCategory());
            qcDataModel.setDate(getDate());
            qcDataModel.setTime(getCurrentTime());
            qcDataModel.setUserID(uuidsHared.getUserData().getUserName());
            qcDataModel.setLine(lotSetShared.getFloorSetting().getLine().getName());
            qcDataModel.setUnit(lotSetShared.getFloorSetting().getProductionUnit().getName());
            qcDataModel.setBuyerName(lotSetShared.getGarmentSettings().getBuyer().getName());
            qcDataModel.setPo(lotSetShared.getGarmentSettings().getPo().getName());
            qcDataModel.setSmv(lotSetShared.getGarmentSettings().getSmv());
            qcDataModel.setOperatorID(lotSetShared.getGarmentSettings().getOperatorID());
            qcDataModel.setMachineID(lotSetShared.getGarmentSettings().getMachineID());

            dbHelper.deleteGarmentDefectEntry(qcDataModel);


        }

    }

    public void undoAGarments(int lotNo, int garmentNo) {

        updoEntryFromServerDB(lotNo, garmentNo);
    }

    private void updoEntryFromServerDB(int lotNo, int garmentNo) {
        List<QCDataModel> qcDataModelList = dbHelper.getAllQCDataModelsByGarmentNoForDeletingFromServer(uuidsHared.getTimeStamp(), garmentNo, lotNo, getDate());
        Gson gson = new Gson();
        String undoGarmentsData = gson.toJson(qcDataModelList);
        Call<BarcodeAPIResponseModel> call = apiService.deleteDataFromServerUpdated(undoGarmentsData);
        call.enqueue(new Callback<BarcodeAPIResponseModel>() {
            @Override
            public void onResponse(Call<BarcodeAPIResponseModel> call, Response<BarcodeAPIResponseModel> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getIsSuccess()) {
                            mainView.onDeleteDataSuccessfulUpdated(response.body(), lotNo, garmentNo);
                        } else {
                            mainView.onDeleteDataFailedUpdated("Failed to delete data");
                        }

                    } else {
                        mainView.onDeleteDataFailedUpdated("ERROR 2001: Server Error!! Please contact with developers");
                    }

                } else {
                    //mainView.onSendCSVDataFailed("ERROR 2002: Server Error!! Please contact with developer");
                    mainView.onDeleteDataFailedUpdated(response.message());
                }


            }

            @Override
            public void onFailure(Call<BarcodeAPIResponseModel> call, Throwable t) {

            }
        });


    }


    public void sendCSVData() {
        saveQCDataIntoCSV(dbHelper.getAllQCDataModels(uuidsHared.getTimeStamp(), getDate()));

        List<QCDataModel> qcDataModelList = dbHelper.getAllQCDataModelsForSendingToDB(uuidsHared.getTimeStamp(), getDate());

        Gson gson = new Gson();
        String exportData = gson.toJson(qcDataModelList);
        //Log.e("TAG_CSV_JSON", "sendjsonData: " + exportData);

        Call<BarcodeAPIResponseModel> call = apiService.sendCSVDataUpdated(exportData);
        call.enqueue(new Callback<BarcodeAPIResponseModel>() {
            @Override
            public void onResponse(Call<BarcodeAPIResponseModel> call, Response<BarcodeAPIResponseModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getIsSuccess()) {
                            mainView.onSendCSVDataIntoServerSuccessfullyUpdated(response.body(), qcDataModelList);
                        } else {
                            mainView.onSendCSVDataFailedUpdated("Failed to insert data");
                        }

                    } else {
                        mainView.onSendCSVDataFailedUpdated("ERROR 2001: Server Error!! Please contact with developers");
                    }

                } else {
                    //mainView.onSendCSVDataFailed("ERROR 2002: Server Error!! Please contact with developer");
                    mainView.onSendCSVDataFailedUpdated(response.message());
                }


            }

            @Override
            public void onFailure(Call<BarcodeAPIResponseModel> call, Throwable t) {
                mainView.onSendCSVDataFailedUpdated(t.getLocalizedMessage());
                //Toast.makeText(context, t.getCause()+"", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void saveQCDataIntoCSV(List<QCDataModel> qcDataModels) {


        String header = "Unit.><." +
                "Line.><." +
                "Time.><." +
                "Date.><." +
                "Batch Qty.><." +
                "Buyer Name.><." +
                "Product type.><." +
                "Style.><." +
                "PO.><." +
                "Garment No.><." +
                "Garment Pos.><." +
                "Defect Name.><." +
                "Defect Count.><." +
                "Color.><." +
                "Defect Pos.><." +
                "SMV.><." +
                "Size.><." +
                "OperatorID.><." +
                "MachineID.><." +
                "UserID--";
        headerList = new ArrayList<>();
        headerList.add(header);

        dataList = new ArrayList<>();
        for (QCDataModel qcDataModel : qcDataModels) {
            dataList.add(qcDataModel.csvBody());
        }

        easyCsv.setSeparatorColumn(".><.");
        easyCsv.setSeperatorLine("--");

        if (uuidsHared.getUniqueID() == null) {
            try {
                uuidsHared.saveUniqueID();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String fileName = "QMSKnittingData" + "_" + getDate() + "_" + uuidsHared.getUniqueID();

        easyCsv.createCsvFile(fileName, headerList, dataList, WRITE_PERMISSON_REQUEST_CODE, new FileCallback() {
            @Override
            public void onSuccess(File file) {
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(String err) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();

            }
        });


    }


    public void updateSyncData(List<QCDataModel> qcDataModelList, BarcodeAPIResponseModel barcodeAPIResponseModel) {
        for (QCDataModel qcDataModel :
                qcDataModelList) {
            dbHelper.updateSyncData(qcDataModel.getId());

        }
        mainView.onUpdateLocalDBSuccessfulUpdated(barcodeAPIResponseModel.getMsg());


    }

    public void deleteDataFromLocalDBUpdated(int lotNo, int garmentNo) {
        String date = getDate();
        List<Integer> ids = dbHelper.getLastEntryID(lotNo, garmentNo, date);


        for (Integer id : ids) {
            dbHelper.deleteDefectsFromQCDefectCount(id);

            dbHelper.deleteDefectsFromQCDefects(id);

        }
        lotSetShared.decreaseGarmentsCount();
        mainView.onUpdateGarmentsCountUpdated();
        saveQCDataIntoCSV(dbHelper.getAllQCDataModels(uuidsHared.getTimeStamp(), getDate()));

    }

    public void setTotalGarmentsCount() {
        mainView.onTotalGarmentCountReadyUpdated(dbHelper.totalGarmentsEntryInADay(getDate()));
    }

    public void checkNoDefectEntry() {
        boolean isFound = dbHelper.isFoundGarmentNo(uuidsHared.getLotNo(), lotSetShared.getGarmentsCount(), getDate());

        if (!isFound) {
            QCDataModel qcDataModel = new QCDataModel();
            qcDataModel.setLotNo(uuidsHared.getLotNo());
            qcDataModel.setGarmentNo(lotSetShared.getGarmentsCount());
            qcDataModel.setBatchQty(lotSetShared.getGarmentSettings().getBatchQty());
            qcDataModel.setBuyerName(lotSetShared.getGarmentSettings().getBuyer().getName());
            qcDataModel.setColor(lotSetShared.getGarmentSettings().getColor());
            qcDataModel.setDate(getDate());
            qcDataModel.setGarmentPos(lotSetShared.getGarmentPosition());
            qcDataModel.setLine(lotSetShared.getFloorSetting().getLine().getName());
            qcDataModel.setSize(lotSetShared.getGarmentSettings().getSize());
            qcDataModel.setStyleCat("Cardigan");
            qcDataModel.setUserID(uuidsHared.getUserData().getUserName());
            qcDataModel.setStyleSubCat(lotSetShared.getGarmentSettings().getStyleSubCategory());
            qcDataModel.setPo(lotSetShared.getGarmentSettings().getPo().getName());
            qcDataModel.setTime(getCurrentTime());
            qcDataModel.setUnit(lotSetShared.getFloorSetting().getProductionUnit().getName());
            qcDataModel.setSmv(lotSetShared.getGarmentSettings().getSmv());
            qcDataModel.setOperatorID(lotSetShared.getGarmentSettings().getOperatorID());
            qcDataModel.setMachineID(lotSetShared.getGarmentSettings().getMachineID());
            dbHelper.insertNoDefect(qcDataModel);
            saveQCDataIntoCSV(dbHelper.getAllQCDataModels(uuidsHared.getTimeStamp(), getDate()));


        }


    }

    public void addMultipleGarment(int myGarmentsNo, int noOfEntry, int maximum) {

        if (noOfEntry > maximum) {
            noOfEntry = maximum;

        }


        List<QCDataModel> qcDataModelList = dbHelper.getAllQCDataModelsByGarmentNo(uuidsHared.getTimeStamp(), myGarmentsNo, uuidsHared.getLotNo(), getDate());
        if (qcDataModelList != null) {
            if (qcDataModelList.size() > 0) {


                for (int i = 0; i < noOfEntry; i++) {
                    for (QCDataModel qcDataModel : qcDataModelList) {
                        qcDataModel.setGarmentNo(myGarmentsNo + i);
                        lotSetShared.saveGarmentsCount();
                        dbHelper.insertGarmentsDefectEntry(qcDataModel);
                    }

                }
                saveQCDataIntoCSV(dbHelper.getAllQCDataModels(uuidsHared.getTimeStamp(), getDate()));
//                lotSetShared.saveGarmentsCount(noOfEntry);
            } else {

                for (int i = 0; i < noOfEntry; i++) {
                    lotSetShared.saveGarmentsCount();
                    checkNoDefectEntry();

                }
                saveQCDataIntoCSV(dbHelper.getAllQCDataModels(uuidsHared.getTimeStamp(), getDate()));
                setTotalGarmentsCount();
            }
        }

    }

    public void deleteDataFromLocalDB(int lotNo, int garmentNo) {
        String date = getDate();
        List<Integer> ids = dbHelper.getLastEntryID(lotNo, garmentNo, date);


        for (Integer id : ids) {
            dbHelper.deleteDefectsFromQCDefectCount(id);

            dbHelper.deleteDefectsFromQCDefects(id);

        }
        lotSetShared.decreaseGarmentsCount();
        mainView.onUpdateGarmentsCountUpdated();
        saveQCDataIntoCSV(dbHelper.getAllQCDataModels(uuidsHared.getTimeStamp(), getDate()));
    }
}
