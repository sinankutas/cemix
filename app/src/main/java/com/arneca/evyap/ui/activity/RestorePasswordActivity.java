package com.arneca.evyap.ui.activity;/*
 * Created by Sinan KUTAS on 1.02.2021.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.GetLogin;
import com.arneca.evyap.api.response.GetSendSecurtyCode;
import com.arneca.evyap.databinding.LoginBinding;
import com.arneca.evyap.databinding.RestorePasswordBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;

import java.util.HashMap;

import androidx.databinding.DataBindingUtil;

public class RestorePasswordActivity  extends BaseActivity{
    private RestorePasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setViewProperties();
    }

    private void setViewProperties() {
        binding = DataBindingUtil.setContentView(this, R.layout.restore_password);
        binding.login.setOnClickListener(v -> onLoginClick());
        binding.back.setOnClickListener(v -> finish());
    }

    public void onLoginClick() {
        if (isValid()) {
            sendSecurtyCodeRequest();
        } else {
            showInfo(getString(R.string.warning), getString(R.string.YOU_HAVE_TO_MAKE_SURE_YOU_FILL_ALL_THE_FIELDS), this);
        }
    }

    private void sendSecurtyCodeRequest(){
        Tool.openDialog(this);
        HashMap<String, Object> headerMap = headersMap(false);

      //  headerMap.put("Authorization","Basic "+toBase64(base64Str).replaceAll("\n",""));
        Request.sendSecurtyCode(binding.loginEmailEd.getText().toString(), this, response -> {
            GetSendSecurtyCode getSendSecurtyCode = (GetSendSecurtyCode) response.body();
            response.headers();
            Tool.hideDialog();
            if (getSendSecurtyCode!=null){
                if(getSendSecurtyCode.isResponse()){ // burada status kontrolü yapılacak
                    Tool.hideDialog();
                   goValidate();  // burası goValidate() goKvkkActivity(); olacak
                }else{
                    if(getSendSecurtyCode.isKVKKConfirmed()==false){ // burası false olacak
                        // goto kvkk activity
                        goKvkkActivity();
                    }else{
                        Tool.showInfo(this, getString(R.string.error), getString(R.string.available_token_not_found));
                    }
                }
            }
        });
    }


    private void goKvkkActivity() {
        Intent intent = new Intent(this, KVKKActivity.class);
        Bundle b = new Bundle();
        b.putString("UserName", binding.loginEmailEd.getText().toString());
        b.putBoolean("isRestorePassActivity",true);
        intent.putExtras(b);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);

    }

    private void goValidate() {
        Intent intent = new Intent(this, ValidateSecurtyCodeActivity.class);
        Bundle b = new Bundle();
        b.putString("email", binding.loginEmailEd.getText().toString()); //Your id
        intent.putExtras(b);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
        finish();
    }


    private boolean isValid() {
        boolean res = true;
        if (binding.loginEmailEd.getText().toString().trim().length() < 1) {
            binding.loginEmailEd.setError(getString(R.string.enter_valid_email));
            res = false;
        } else {
            binding.loginEmailEd.setError(null);
        }

        return res;
    }


}
