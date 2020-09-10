/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/24/20 11:29 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/24/20 10:21 AM
 *
 */

package com.sqgc.qmsendlineapplication.network;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

//    public static final String IP_URL = "http://10.12.0.130:8080";
public static final String IP_URL = "http://119.148.12.173:8110";
    //        public static final String IP_URL = "http://192.168.0.107:8080";
    public static final String BASE_URL = IP_URL + "/api/BasicQMSData/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();

        }
        return retrofit;
    }
}