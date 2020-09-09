/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/22/20 12:29 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/22/20 11:33 AM
 *
 */

package com.sqgc.qmsendlineapplication.services;

import com.sqgc.qmsendlineapplication.models.api_models.BarcodeAPIDataModel;
import com.sqgc.qmsendlineapplication.models.api_models.BarcodeAPIResponseModel;
import com.sqgc.qmsendlineapplication.models.api_models.DefectListByPositionAPIResponse;
import com.sqgc.qmsendlineapplication.models.api_models.LoginResponse;
import com.sqgc.qmsendlineapplication.models.api_models.PositionGridListAPIResponse;
import com.sqgc.qmsendlineapplication.models.api_models.SavedPrimaryKeyDataResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    //get garment data api
    @GET("GetBarcodeInformation")
    Call<BarcodeAPIDataModel> getBarcodeData(@Query("BusinessUnit") Integer businessUnit, @Query("BarcodeNumber") Integer barcodeNumber);

    //defect entry data
    @Headers("Content-Type: application/json")
    @POST("QmsDefectEntryDetails")
    Call<BarcodeAPIResponseModel> sendCSVData(@Body String body);

    //delete Garments data
    @Headers("Content-Type: application/json")
    @POST("DeleteQmsDataDetais")
    Call<BarcodeAPIResponseModel> deleteDataFromServer(@Body String body);

    //login data api
    @GET("CheckoginQms")
    Call<LoginResponse> loginRequest(@Query("UserName") String username, @Query("UserPassword") String userpass);

    //positionGridList
    @Headers("Content-Type: application/json")
    @GET("PositionGridListAPI")
    Call<PositionGridListAPIResponse> getPosWithGridData(@Query("StyleId") int styleID, @Query("Status") int status);

    //positionGridList
    @Headers("Content-Type: application/json")
    @GET("DefectListByPositionAPI")
    Call<DefectListByPositionAPIResponse> getDefectListData(@Query("StyleId") int styleID, @Query("Status") int status);

    @Headers("Content-Type: application/json")
    @POST("SaveToQmsMasterTable")
    Call<SavedPrimaryKeyDataResponse> getSavedPKdata(@Query("UserId") int userID, @Query("MasterGenarationId") int masterGenerationID, @Query("TabId") String tabID, @Query("OperationSMV") String operationSMV);


}
