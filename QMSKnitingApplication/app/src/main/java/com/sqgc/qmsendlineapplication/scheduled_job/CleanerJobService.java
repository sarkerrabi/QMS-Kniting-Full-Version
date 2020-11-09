/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 11/7/20 10:21 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 11/7/20 10:21 AM
 *
 */

package com.sqgc.qmsendlineapplication.scheduled_job;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.sqgc.qmsendlineapplication.LoginActivity;
import com.sqgc.qmsendlineapplication.sharedDB.CleanTotalSetShared;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CleanerJobService extends BroadcastReceiver {

    CleanTotalSetShared cleanTotalSetShared;

    @Override
    public void onReceive(Context context, Intent intent) {
        cleanTotalSetShared = new CleanTotalSetShared(context);
        int clean_time = intent.getIntExtra("CLEAN_TIME", -1);
        if (clean_time == 0) {

            if (getDate().equals(cleanTotalSetShared.getDate())) {
                if (!cleanTotalSetShared.isMorningShiftCleanedSuccessfully()) {
/*                    Toast.makeText(context, "Do my cleaner job: !!!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                    Log.e("TAGJOB", "Do my cleaner job: !!!!!!!!!!!!!!!!!!");
                    MediaPlayer mediaPlayer = MediaPlayer.create(context,
                            Settings.System.DEFAULT_RINGTONE_URI);
                    mediaPlayer.start();*/
                    clearDBData(context);

                    cleanTotalSetShared.saveMorningShiftCleanedSuccessfully();
                }

            }
        } else {

            if (getDate().equals(cleanTotalSetShared.getDate())) {
                if (!cleanTotalSetShared.isEveningShiftCleanedSuccessfully()) {
/*                    Toast.makeText(context, "Do my cleaner job: !!!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                    Log.e("TAGJOB", "Do my cleaner job: !!!!!!!!!!!!!!!!!!");
                    MediaPlayer mediaPlayer = MediaPlayer.create(context,
                            Settings.System.DEFAULT_RINGTONE_URI);
                    mediaPlayer.start();*/
                    clearDBData(context);

                    cleanTotalSetShared.saveEveningShiftCleanedSuccessfully();
                }

            }

        }
    }

    private String getDate() {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = dateFormat.format(new Date()); // Find todays date

            return currentDate;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }


    private void clearDBData(Context context) {
        com.sqgc.qmsendlineapplication.preknit.database.DBHelper dbHelper = new com.sqgc.qmsendlineapplication.preknit.database.DBHelper(context);
        dbHelper.clearEntryData();

        com.sqgc.qmsendlineapplication.databases.DBHelper myDB = new com.sqgc.qmsendlineapplication.databases.DBHelper(context);
        myDB.clearDBEntryData();

        Log.e("TAGJOB", "clearDBData: " + context.getClass().getSimpleName());


        Intent i = new Intent();
        i.setClassName(context.getPackageName(), LoginActivity.class.getName());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }
}
