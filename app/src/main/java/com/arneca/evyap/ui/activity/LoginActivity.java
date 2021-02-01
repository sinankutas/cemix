package com.arneca.evyap.ui.activity;/*
 * Created by Sinan Kutas on 22.01.2021.
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.WindowManager;import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.GetLogin;
import com.arneca.evyap.databinding.LoginBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import okhttp3.Headers;
import retrofit2.http.Header;

public class LoginActivity extends BaseActivity{
    private LoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setViewProperties();
    }

    private void setViewProperties() {

        loginBinding = DataBindingUtil.setContentView(this, R.layout.login);
        loginBinding.login.setOnClickListener(v -> onLoginClick());
        loginBinding.rememberMe.setOnClickListener(v -> onRememberClick());
        loginBinding.restorePass.setOnClickListener(v -> onRestorePass());
        if (PreferencesHelper.isIsRememberMe(this)){
            loginBinding.rememberMeButton.setBackgroundResource(R.drawable.checked);
            loginBinding.loginPasswordEd.setText(PreferencesHelper.getPassword(this));
            loginBinding.loginEmailEd.setText(PreferencesHelper.getUserName(this));
        }
    }

    private void onRestorePass() {
        Intent intent = new Intent(this, RestorePasswordActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
        finish();
    }


    private void onRememberClick() {
        if (PreferencesHelper.isIsRememberMe(this)){
            loginBinding.rememberMeButton.setBackgroundResource(R.drawable.uncehecked);
            PreferencesHelper.setPassword(this,"");
            PreferencesHelper.setUserName(this,"");
            PreferencesHelper.setIsRememberMe(this,false);
        }else{
            loginBinding.rememberMeButton.setBackgroundResource(R.drawable.checked);
            PreferencesHelper.setIsRememberMe(this,true);
        }
    }

    public void onLoginClick() {
        if (isValid()) {
            loginRequest();
        } else {
            showInfo(getString(R.string.warning), getString(R.string.YOU_HAVE_TO_MAKE_SURE_YOU_FILL_ALL_THE_FIELDS), this);
        }
    }


    private void goSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
        finish();
    }





    private boolean isValid() {
        boolean res = true;
        if (loginBinding.loginEmailEd.getText().toString().trim().length() < 1) {
            loginBinding.loginEmailEd.setError(getString(R.string.enter_valid_email));
            res = false;
        } else {
            loginBinding.loginEmailEd.setError(null);
        }
        if (loginBinding.loginPasswordEd.getText().toString().trim().length() < 1) {
            loginBinding.loginPasswordEd.setError(getString(R.string.enter_password));
            res = false;
        } else {
            loginBinding.loginPasswordEd.setError(null);
        }
        return res;
    }

    private void loginRequest(){
        Tool.openDialog(this);
        HashMap<String, Object> headerMap = headersMap(false);
        String base64Str = loginBinding.loginEmailEd.getText().toString()+":"+loginBinding.loginPasswordEd.getText().toString();
        headerMap.put("Authorization","Basic "+toBase64(base64Str).replaceAll("\n",""));
        Request.getTokens(headerMap, this, response -> {
            GetLogin loginResponse = (GetLogin) response.body();
            response.headers();

            if (loginResponse!=null){
                if(loginResponse.isResponse()){ // burada status kontrolü yapılacak
                    Tool.hideDialog();
                    if (PreferencesHelper.isIsRememberMe(this)){
                        PreferencesHelper.setUserName(this,loginBinding.loginEmailEd.getText().toString());
                        PreferencesHelper.setPassword(this,loginBinding.loginPasswordEd.getText().toString());
                     }
                    PreferencesHelper.setAppKey(this,response.headers().get("appKey")); // burada appKey set edilecek
                    goSettingsActivity();
                }else{
                    Tool.showInfo(this, getString(R.string.error), getString(R.string.available_token_not_found));
                }
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
