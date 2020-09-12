/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/12/20 8:33 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/11/20 3:46 PM
 *
 */

package com.sqgc.qmsendlineapplication.presenters;

import android.app.Activity;
import android.content.Context;

import com.sqgc.qmsendlineapplication.databases.DBHelper;
import com.sqgc.qmsendlineapplication.models.api_models.LoginResponse;
import com.sqgc.qmsendlineapplication.network.ApiClient;
import com.sqgc.qmsendlineapplication.services.ApiService;
import com.sqgc.qmsendlineapplication.views.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private LoginView loginView;
    private Context context;
    private Activity activity;
    private ApiService apiService;
    private DBHelper dbHelper;
    private com.sqgc.qmsendlineapplication.preknit.database.DBHelper myDBHelper;

    public LoginPresenter(LoginView loginView, Context context, Activity activity) {
        this.loginView = loginView;
        dbHelper = new DBHelper(context);
        myDBHelper = new com.sqgc.qmsendlineapplication.preknit.database.DBHelper(context);
        dbHelper.getWritableDatabase();
        myDBHelper.getWritableDatabase();
        dbHelper.close();
        this.context = context;
        this.activity = activity;
        if (apiService == null) {
            apiService = ApiClient.getRetrofit().create(ApiService.class);
        }

    }

    public void loginNow(String username, String password) {

        apiService.loginRequest(username, password)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                if (response.body().isIsSuccessful()) {
                                    loginView.onLoginSuccess(response.body());
                                } else {
                                    loginView.onLoginFailed(response.body().getMessage());
                                }
                            } else {
                                loginView.onLoginFailed(response.message());
                            }

                        } else {
                            loginView.onLoginFailed(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        loginView.onLoginFailed(t.getMessage());

                    }
                });


    }

}
