/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/24/20 4:32 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/24/20 4:32 PM
 *
 */

package com.sqgc.qmsendlineapplication;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.amitshekhar.DebugDB;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.novoda.merlin.Bindable;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;
import com.novoda.merlin.NetworkStatus;
import com.sqgc.qmsendlineapplication.dialogs.Loader;
import com.sqgc.qmsendlineapplication.models.api_models.LoginResponse;
import com.sqgc.qmsendlineapplication.models.sync_model.DataSyncModel;
import com.sqgc.qmsendlineapplication.presenters.LoginPresenter;
import com.sqgc.qmsendlineapplication.sharedDB.LotSetShared;
import com.sqgc.qmsendlineapplication.sharedDB.UUIDSHared;
import com.sqgc.qmsendlineapplication.views.LoginView;
import com.sqgc.qmsendlineapplication.views.SyncView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sqgc.qmsendlineapplication.common.CommonSettings.getCurrentTime;
import static com.sqgc.qmsendlineapplication.common.CommonSettings.getDate;

public class LoginActivity extends AppCompatActivity implements LoginView, Connectable, Bindable, Disconnectable, SyncView {
    LoginPresenter loginPresenter;
    DataSyncModel dataSyncModel;
    @BindView(R.id.et_id)
    EditText etId;
    @BindView(R.id.et_pass)
    EditText etPass;

    UUIDSHared uuidsHared;
    LotSetShared lotSetShared;
    @BindView(R.id.tv_last_sync)
    TextView tvLastSync;
    Loader loader;
    @BindView(R.id.tv_tab_id)
    TextView tvTabId;
    AlertDialog internetAlertDialog;
    @BindView(R.id.iv_sync)
    ImageView ivSync;
    private Merlin merlin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Log.e("DB_DEBUG", "onCreate: " + DebugDB.getAddressLog());
        loader = new Loader(LoginActivity.this);
        loginPresenter = new LoginPresenter(this, getApplicationContext(), this);
        dataSyncModel = new DataSyncModel(LoginActivity.this, LoginActivity.this, this);
        merlin = new Merlin.Builder()
                .withConnectableCallbacks()
                .withDisconnectableCallbacks()
                .withBindableCallbacks()
                .build(LoginActivity.this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;


        uuidsHared = new UUIDSHared(getApplicationContext());
        lotSetShared = new LotSetShared(getApplicationContext());

        if (uuidsHared.getUniqueID() == null) {
            try {
                uuidsHared.saveUniqueID();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        uuidsHared.saveScreenHeight(height);
        uuidsHared.saveScreenWidth(width);

        checkPermission();
        if (uuidsHared.getLastSyncTime() != null) {
            tvLastSync.setText(String.format("Last Syc with server at - %s", uuidsHared.getLastSyncTime()));
        }
        if (uuidsHared.getUniqueID() != null) {
            tvTabId.setText(String.format("TAB ID - %s", uuidsHared.getUniqueID()));
        }

        if (uuidsHared.getUserData() != null) {
            ivSync.setVisibility(View.VISIBLE);
        } else {
            ivSync.setVisibility(View.GONE);
        }

        merlin.registerConnectable(this);
        merlin.registerDisconnectable(this);
        merlin.registerBindable(this);

    }

    private void checkPermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Log.d("TAGFLOOR", "onPermissionGranted: ");


                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */
                        Log.d("TAGFLOOR", "onPermissionDenied: ");
                        checkPermission();

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        Log.d("TAGFLOOR", "onPermissionRationaleShouldBeShown: ");


                    }
                }).check();
    }


    @Override
    public void onLoginSuccess(LoginResponse result) {
        loader.hideDialog();
        if (lotSetShared.getGarmentSettings() != null) {
            if (uuidsHared.getUserData().getLineNumber().equals(result.getLineNumber())
                    && uuidsHared.getUserData().getModuleName().equals(result.getModuleName())
                    && uuidsHared.getUserData().getProductionUnit().equals(result.getProductionUnit())
                    && uuidsHared.getUserData().getBusinessUnitId().equals(result.getBusinessUnitId())) {

                if (lotSetShared.getGarmentsCount() < Integer.parseInt(lotSetShared.getGarmentSettings().getGarmentsTobeChecked())) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    uuidsHared.saveUserData(result);
                    finish();

                } else {
                    startActivity(new Intent(getApplicationContext(), FloorInfoActivity.class));
                    uuidsHared.saveUserData(result);
                    finish();
                }
            } else {

                startActivity(new Intent(getApplicationContext(), FloorInfoActivity.class));
                uuidsHared.saveUserData(result);
                finish();
            }


        } else {
            startActivity(new Intent(getApplicationContext(), FloorInfoActivity.class));
            uuidsHared.saveUserData(result);
            finish();
        }


    }

    @Override
    public void onLoginFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        loader.hideDialog();
    }


    @OnClick(R.id.bt_login)
    public void onViewClicked() {
        if (!etId.getText().toString().isEmpty() && !etPass.getText().toString().isEmpty()) {
            loader.showDialog();
            loginPresenter.loginNow(etId.getText().toString(), etPass.getText().toString());

        } else {
            this.onLoginFailed("Please insert a valid  user id & password");

        }
    }

    @Override
    protected void onResume() {
        merlin.bind();
        super.onResume();
    }

    @Override
    protected void onPause() {
        merlin.unbind();
        super.onPause();
    }

    @Override
    public void onConnect() {
        if (internetAlertDialog != null) {
            if (internetAlertDialog.isShowing()) {
                try {
                    internetAlertDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public void onBind(NetworkStatus networkStatus) {
        if (!networkStatus.isAvailable()) {
            onDisconnect();
        }

    }

    @Override
    public void onDisconnect() {
        internetAlertDialog = new AlertDialog.Builder(LoginActivity.this).create();
        internetAlertDialog.setTitle("Alert");
        internetAlertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        internetAlertDialog.setCancelable(false);
        internetAlertDialog.setCanceledOnTouchOutside(false);
        internetAlertDialog.setMessage("NO INTERNET CONNECTION !!! \nPLEASE CHECK YOUR INTERNET CONNECTION");
        internetAlertDialog.show();
    }

    @OnClick(R.id.iv_sync)
    public void onSyncViewClicked() {
        ivSync.clearAnimation();
        RotateAnimation anim = new RotateAnimation(30, 360, ivSync.getWidth() / 2, ivSync.getHeight() / 2);
        anim.setFillAfter(true);
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(1000);
        ivSync.startAnimation(anim);
        dataSyncModel.sendDefectData();


    }

    @Override
    public void onSendDataFailed(String failed_to_insert_data) {
        ivSync.clearAnimation();
    }

    @Override
    public void onUpdateLocalDBDefectSuccessful(String msg) {
        ivSync.clearAnimation();
        Toast.makeText(this, "Data sync Successful", Toast.LENGTH_SHORT).show();
        uuidsHared.saveLastSyncTime(getDate() + " " + getCurrentTime());
        setLastSyncData();

    }

    private void setLastSyncData() {
        if (uuidsHared.getLastSyncTime() != null) {
            tvLastSync.setText(String.format("Last Syc with server at - %s", uuidsHared.getLastSyncTime()));
        }
    }
}