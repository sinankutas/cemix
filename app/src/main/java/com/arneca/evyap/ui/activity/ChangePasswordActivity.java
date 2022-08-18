package com.arneca.evyap.ui.activity;/*
 * Created by Sinan KUTAS on 1.02.2021.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.GetValidateSecurtyCode;
import com.arneca.evyap.api.response.cmx.LoginResponse;
import com.arneca.evyap.databinding.ChangePasswordBinding;
import com.arneca.evyap.databinding.ChangePasswordBindingImpl;
import com.arneca.evyap.databinding.ValidateSecurtyCodeBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.cmx.HomeActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import androidx.databinding.DataBindingUtil;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ChangePasswordActivity extends BaseActivity{
    private ChangePasswordBindingImpl binding;
    String email,securtyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Bundle b = getIntent().getExtras();
        if(b != null){
            email = b.getString("email");
            securtyCode = b.getString("securityCode");
        }

        setViewProperties();
    }

    private void setViewProperties() {
        binding = DataBindingUtil.setContentView(this, R.layout.change_password);
        binding.login.setOnClickListener(v -> onLoginClick());
        binding.txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void onLoginClick() {
        if (isValid()) {
            changePassword();
        } else {
            Tool.showInfo(this, getString(R.string.error), "Alanlar boş geçilemez. Ve Yeni Şifre alanları farklı olamaz");
        }
    }


    private void changePassword(){
        Tool.openDialog(this);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("Kullanici",binding.usernameEd.getText().toString())
                .addFormDataPart("Sifre",md5(binding.existingPassEd.getText().toString()))
                .addFormDataPart("YeniSifre1",md5(binding.pass1ed.getText().toString()))
                .addFormDataPart("YeniSifre2",md5(binding.pass2ed.getText().toString()))
                .build();

        Request.changePass(requestBody, this, response -> {
            LoginResponse loginResponse = (LoginResponse) response.body();
            response.headers();

            if (loginResponse.getResult()!=null){
                Tool.showInfo(this, "Bilgi", loginResponse.getResult_message().getMessage());

                Tool.hideDialog();
                PreferencesHelper.setLoginResponse(loginResponse);
                if (PreferencesHelper.isIsRememberMe(this)){
                    PreferencesHelper.setUserName(this,binding.usernameEd.getText().toString());
                    PreferencesHelper.setPassword(this,binding.pass2ed.getText().toString());
                }
                finish();
            }else{
                Tool.hideDialog();
                Tool.showInfo(this, "Hata", loginResponse.getResult_message().getMessage());

            }
        });
    }

    private void goLogin() {
        PreferencesHelper.setPassword(this, binding.pass2ed.getText().toString());
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
        finish();
    }


    private boolean isValid() {
        boolean res = true;
        if (binding.usernameEd.getText().toString().trim().length() < 1) {
            //   Tool.showInfo(this, getString(R.string.error),"Kullanıcı adı boş geçilemez");
            // binding.pass2ed.setError(getString(R.string.enterpass2));
            res = false;
        } else {
            binding.usernameEd.setError(null);
        }

        if (binding.existingPassEd.getText().toString().trim().length() < 1) {
            //   Tool.showInfo(this, getString(R.string.error),"Şifre boş geçilemez");
            // binding.pass2ed.setError(getString(R.string.enterpass2));
            res = false;
        } else {
            binding.existingPassEd.setError(null);
        }


        if (binding.pass1ed.getText().toString().trim().length() < 1) {
            //    Tool.showInfo(this, getString(R.string.error),"Yeni Şifre1 boş geçilemez");
            // binding.pass2ed.setError(getString(R.string.enterpass2));
            res = false;
        } else {
            binding.pass1ed.setError(null);
        }

        if (binding.pass2ed.getText().toString().trim().length() < 1) {
            //   Tool.showInfo(this, getString(R.string.error),"Yeni Şifre2 boş geçilemez");
            // binding.pass2ed.setError(getString(R.string.enterpass2));
            res = false;
        } else {
            binding.pass2ed.setError(null);
        }

        if (!binding.pass1ed.getText().toString().equals(binding.pass2ed.getText().toString())) {
            //    Tool.showInfo(this, getString(R.string.error),"Yeni Şifre 1 ve 2 aynı olmalı");
           // binding.pass2ed.setError(getString(R.string.enterpass2));
            res = false;
        }else{
            binding.pass2ed.setError(null);
            binding.pass1ed.setError(null);
        }
        return res;
    }

    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


}
