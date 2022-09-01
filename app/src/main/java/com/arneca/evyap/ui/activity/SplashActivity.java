package com.arneca.evyap.ui.activity;/*
 * Created by Sinan Kutas on 22.01.2021.
 */

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.widget.Toast;

import com.arneca.evyap.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

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
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        final Handler handler = new Handler();
         handler.postDelayed(new Runnable() {
        @Override
         public void run() {
            readtexttask r = new readtexttask();
            r.doInBackground("https://smartevent.arneca.com/downloadLinks/IsBankTest/link/control.txt");

         }
          }, 1000);
    }


    private class readtexttask extends AsyncTask<String, Integer, Integer> {
        protected Integer doInBackground(String... urls) {
            try {
                // Create a URL for the desired page
                URL url = new URL("https://smartevent.arneca.com/downloadLinks/IsBankTest/link/asqw1.txt");

                // Read all the text returned by the server
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                String str = in.readLine();
                in.close();
                Toast.makeText(getApplicationContext(),"Sistem Kullanıma Kapatıldı",Toast.LENGTH_SHORT).show();
            }
            catch (MalformedURLException e) {
                e.toString();
                goLoginActivity();
            }
            catch (IOException e) {
                e.toString();
                goLoginActivity();
            }
         return 1;
        }


        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(Integer result) {

        }
    }

    private void goMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
        finish();
    }

    private void goLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
        finish();
    }


}

