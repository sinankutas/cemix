package com.arneca.evyap.ui.activity;/*
 * Created by Sinan KUTAS on 1.02.2021.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.GetSendSecurtyCode;
import com.arneca.evyap.api.response.GetValidateSecurtyCode;
import com.arneca.evyap.databinding.RestorePasswordBinding;
import com.arneca.evyap.databinding.ValidateSecurtyCodeBinding;
import com.arneca.evyap.helper.Tool;

import java.util.HashMap;

import androidx.databinding.DataBindingUtil;

public class ValidateSecurtyCodeActivity extends BaseActivity{
    private ValidateSecurtyCodeBinding binding;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Bundle b = getIntent().getExtras();
        if(b != null)
            email = b.getString("email");
        setViewProperties();
    }

    private void setViewProperties() {
        binding = DataBindingUtil.setContentView(this, R.layout.validate_securty_code);
        binding.login.setText(R.string.verifysecurtycodesend);
        binding.login.setOnClickListener(v -> onLoginClick());
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
        HashMap<String, Object> params = new HashMap<>();
        params.put("securityCode",binding.loginEmailEd.getText().toString());
        params.put("email",email);

        Request.validateSecurtyCode(params, this, response -> {

            GetSendSecurtyCode validateSecurtyCode = (GetSendSecurtyCode) response.body();
            response.headers();

            if (validateSecurtyCode!=null){
                if(validateSecurtyCode.isResponse()){ // burada status kontrolü yapılacak
                    Tool.hideDialog();
                    goChangePass();

                }else{
                    Tool.showInfo(this, getString(R.string.error), getString(R.string.incorrectcode));
                }
            }
        });
    }



    private void goChangePass() {
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        Bundle b = new Bundle();
        b.putString("email", email); //Your id
        b.putString("securityCode", binding.loginEmailEd.getText().toString()); //Your id
        intent.putExtras(b);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
        finish();

    }

    private boolean isValid() {
        boolean res = true;
        if (binding.loginEmailEd.getText().toString().trim().length() < 1) {
            binding.loginEmailEd.setError(getString(R.string.entersecurtycodesend));
            res = false;
        } else {
            binding.loginEmailEd.setError(null);
        }

        return res;
    }


    }
