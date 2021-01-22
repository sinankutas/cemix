package com.arneca.evyap.ui.activity;/*
 * Created by Sinan Kutas on 22.01.2021.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;

import com.arneca.evyap.R;
import com.scottyab.rootbeer.RootBeer;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class SplashActivity extends  BaseActivity{
    private boolean isRoutedControlActive = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
 /*       Intent intent = new Intent(this, TestActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
        finish();*/
        final Handler handler = new Handler();
         handler.postDelayed(new Runnable() {
        @Override
         public void run() {
           //             goLoginActivity();
         }
          }, 600);
    }


    private void goMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
        finish();
    }



}

