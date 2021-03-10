package com.arneca.evyap.ui.activity;/*
 * Created by Sinan KUTAS on 8.03.2021.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.GetKVKConfirm;
import com.arneca.evyap.api.response.GetKVKK;
import com.arneca.evyap.api.response.GetLogin;
import com.arneca.evyap.databinding.KvkkBinding;
import com.arneca.evyap.databinding.LoginBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import androidx.databinding.DataBindingUtil;

public class KVKKActivity extends BaseActivity{
    private KvkkBinding kvkkBinding;
    private boolean isChecked;
    private String userName, password;
    private boolean isRestorePassActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        kvkkBinding = DataBindingUtil.setContentView(this, R.layout.kvkk);
       // kvkkBinding.toolbar.changeData.setVisibility(View.INVISIBLE);
        kvkkBinding.toolbar.changeData.setBackgroundResource(R.drawable.back);
        kvkkBinding.toolbar.changeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        kvkkBinding.toolbar.logout.setVisibility(View.INVISIBLE);
        kvkkBinding.toolbar.settins.setVisibility(View.INVISIBLE);
        getKvkkText();
    }

    private void setViewProperties(GetKVKK getKVKK) {


        kvkkBinding. webview.loadData(getKVKK.getData(), "text/html", "UTF-8");
        kvkkBinding. webview.setVerticalScrollBarEnabled(true);
        kvkkBinding.lytChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isChecked){
                    kvkkBinding.checkbox.setBackgroundResource(R.drawable.uncheckedbox);
                    isChecked = false;
                    kvkkBinding.btnContinue.setVisibility(View.INVISIBLE);
                }else{
                    isChecked = true;
                    kvkkBinding.checkbox.setBackgroundResource(R.drawable.checkedbox);
                    kvkkBinding.btnContinue.setVisibility(View.VISIBLE);
                }
            }
        });

        kvkkBinding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmKVKK();
            }
        });
    }

    private void confirmKVKK() {
        Bundle b = getIntent().getExtras();
        userName = "";
        isRestorePassActivity = false;
        if(b != null){
            userName = b.getString("UserName");
            isRestorePassActivity = b.getBoolean("isRestorePassActivity");
        }

        Tool.openDialog(this);
        HashMap<String, Object> params = new HashMap<>();
        params.put("UserName",userName);
        Request.confirmKvkkByUserName(params, this, response -> {
            Tool.hideDialog();
            GetKVKConfirm getKVKK = (GetKVKConfirm) response.body();

            if (getKVKK!=null){
                if (isRestorePassActivity == false){
                    loginRequest();
                }else{
                    goValidate();
                }
            }else{
                Tool.showInfo(this, getString(R.string.error), getString(R.string.available_token_not_found));
            }
        });

    }

    private void goValidate() {
        Intent intent = new Intent(this, ValidateSecurtyCodeActivity.class);
        Bundle b = new Bundle();
        b.putString("email", userName); //Your id
        intent.putExtras(b);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
        finish();
    }


    private void loginRequest(){

        Bundle b = getIntent().getExtras();

        if(b != null){
            userName = b.getString("UserName");
            password = b.getString("password");
        }

        Tool.openDialog(this);
        HashMap<String, Object> headerMap = headersMap(false);
        String base64Str = userName.toString()+":"+password.toString();
        headerMap.put("Authorization","Basic "+toBase64(base64Str).replaceAll("\n",""));
        Request.getTokens(headerMap, this, response -> {
            GetLogin loginResponse = (GetLogin) response.body();
            response.headers();

            if (loginResponse!=null){
                if(loginResponse.isResponse()){ // burada status kontrolü yapılacak
                    Tool.hideDialog();
                    if (PreferencesHelper.isIsRememberMe(this)){
                        PreferencesHelper.setUserName(this,userName.toString());
                        PreferencesHelper.setPassword(this,password.toString());
                    }
                    PreferencesHelper.setAppKey(this,response.headers().get("appKey")); // burada appKey set edilecek
                    goSettingsActivity();
                }else{
                    Tool.showInfo(this, getString(R.string.error), getString(R.string.available_token_not_found));
                }
            }
        });
    }

    private void goSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
        finish();
    }

    private void getKvkkText(){
        Tool.openDialog(this);
        Request.getKvkkText(this, response -> {
            Tool.hideDialog();
            GetKVKK getKVKK = (GetKVKK) response.body();
            response.headers();
            if (getKVKK!=null){
                setViewProperties(getKVKK);
            }else{
                Tool.showInfo(this, getString(R.string.error), getString(R.string.available_token_not_found));
            }
        });
    }

    public static String toBase64(String message) {
        byte[] data;
        try {
            data = message.getBytes("UTF-8");
            String base64Sms = Base64.encodeToString(data, Base64.DEFAULT);
            return base64Sms;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
