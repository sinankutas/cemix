package com.arneca.evyap.ui.activity;/*
 * Created by Sinan KUTAS on 1.02.2021.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.GetValidateSecurtyCode;
import com.arneca.evyap.databinding.ChangePasswordBinding;
import com.arneca.evyap.databinding.ChangePasswordBindingImpl;
import com.arneca.evyap.databinding.ValidateSecurtyCodeBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;

import java.util.HashMap;

import androidx.databinding.DataBindingUtil;

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
    }

    private void onLoginClick() {
        if (isValid()) {
            changePassword();
        } else {
            Tool.showInfo(this, getString(R.string.error), getString(R.string.enterpass2));
        }
    }


    private void changePassword(){
        Tool.openDialog(this);
        HashMap<String, Object> params = new HashMap<>();

        params.put("newPassword",binding.pass1ed.getText().toString());
        params.put("newPasswordConfirm",binding.pass2ed.getText().toString());
        params.put("userEmail",email);
        params.put("securityCode",securtyCode);

        Request.changePassword(params, this, response -> {

            ChangePassword validateSecurtyCode = (ChangePassword) response.body();
            response.headers();

            if (validateSecurtyCode!=null){
                if(validateSecurtyCode.isResponse()){ // burada status kontrolü yapılacak
                    Tool.hideDialog();
                    Tool.showInfo(this,
                            getString(R.string.info),
                            validateSecurtyCode.getStatus(),
                            (dialog, which) -> goLogin());

                }else{
                    Tool.showInfo(this, getString(R.string.error), getString(R.string.available_token_not_found));
                }
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
        if (binding.pass1ed.getText().toString().trim().length() < 10) {
            //  Tool.showInfo(this, getString(R.string.error), getString(R.string.enterpass2));
            // binding.pass2ed.setError(getString(R.string.enterpass2));
            res = false;
        } else {
            binding.pass1ed.setError(null);
        }

        if (binding.pass2ed.getText().toString().trim().length() < 10) {
            //   Tool.showInfo(this, getString(R.string.error), getString(R.string.enterpass2));
            // binding.pass2ed.setError(getString(R.string.enterpass2));
            res = false;
        } else {
            binding.pass2ed.setError(null);
        }

        if (!binding.pass1ed.getText().toString().equals(binding.pass2ed.getText().toString())) {
            //   Tool.showInfo(this, getString(R.string.error), getString(R.string.enterpass2));
           // binding.pass2ed.setError(getString(R.string.enterpass2));
            res = false;
        }else{
            binding.pass2ed.setError(null);
            binding.pass1ed.setError(null);
        }



        return res;
    }


}
