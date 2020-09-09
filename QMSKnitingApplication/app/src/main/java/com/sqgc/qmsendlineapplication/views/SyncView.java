/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/24/20 4:32 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/24/20 2:03 PM
 *
 */

package com.sqgc.qmsendlineapplication.views;

public interface SyncView {

    void onSendDataFailed(String failed_to_insert_data);

    void onUpdateLocalDBDefectSuccessful(String msg);
}
