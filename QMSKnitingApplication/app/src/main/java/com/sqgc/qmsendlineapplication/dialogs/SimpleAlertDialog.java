/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/4/20 4:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/4/20 12:51 PM
 *
 */

package com.sqgc.qmsendlineapplication.dialogs;


import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class SimpleAlertDialog {
    Context context;
    AlertDialog alertDialog;

    public SimpleAlertDialog(Context context, String message) {
        this.context = context;
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

    }

    public void show() {
        alertDialog.show();
    }


}
