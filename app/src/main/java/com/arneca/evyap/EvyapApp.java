package com.arneca.evyap;/*
 * Created by Sinan KUTAS on 29.01.2021.
 */

import android.app.Application;
import android.content.SharedPreferences;
import android.os.StrictMode;

import com.orhanobut.hawk.Hawk;

public class EvyapApp extends Application {

    SharedPreferences prefs = null;


    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(getApplicationContext()).build();
        prefs = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
    }
}
