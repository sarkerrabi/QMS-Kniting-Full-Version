/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 9/11/20 1:58 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 9/11/20 1:58 AM
 *
 */

package com.sqgc.qmsendlineapplication.services;

import com.sqgc.qmsendlineapplication.models.api_models.BarcodeAPIResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiServiceUpdated {
    @Headers("Content-Type: application/json")
    @POST("SaveDataToQmsTable")
    Call<BarcodeAPIResponseModel> sendCSVDataUpdated(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("DeleteDataQMS")
    Call<BarcodeAPIResponseModel> deleteDataFromServerUpdated(@Body String body);
}
