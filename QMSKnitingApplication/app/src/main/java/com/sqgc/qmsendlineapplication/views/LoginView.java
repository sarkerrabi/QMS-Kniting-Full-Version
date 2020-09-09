/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/4/20 4:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/4/20 12:29 PM
 *
 */

package com.sqgc.qmsendlineapplication.views;

import com.sqgc.qmsendlineapplication.models.api_models.LoginResponse;

public interface LoginView {
    void onLoginSuccess(LoginResponse result);

    void onLoginFailed(String message);


}
