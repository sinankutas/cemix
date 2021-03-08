package com.arneca.evyap.ui.activity;/*
 * Created by Sinan KUTAS on 8.03.2021.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.GetKVKConfirm;
import com.arneca.evyap.api.response.GetKVKK;
import com.arneca.evyap.databinding.KvkkBinding;
import com.arneca.evyap.databinding.LoginBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;

import java.util.HashMap;

import androidx.databinding.DataBindingUtil;

public class KVKKActivity extends BaseActivity{
    private KvkkBinding kvkkBinding;
    private boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        kvkkBinding = DataBindingUtil.setContentView(this, R.layout.kvkk);
        kvkkBinding.toolbar.changeData.setVisibility(View.INVISIBLE);
        kvkkBinding.toolbar.logout.setVisibility(View.INVISIBLE);
        kvkkBinding.toolbar.settins.setVisibility(View.INVISIBLE);
        getKvkkText();
    }

    private void setViewProperties(GetKVKK getKVKK) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            kvkkBinding.kvkkText.setText(Html.fromHtml(getKVKK.getData(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            kvkkBinding.kvkkText.setText(Html.fromHtml(getKVKK.getData()));
        }

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
        String userName = "";
        if(b != null){
            userName = b.getString("UserName");
        }

        Tool.openDialog(this);
        HashMap<String, Object> params = new HashMap<>();
        params.put("UserName",userName);
        Request.confirmKvkkByUserName(params, this, response -> {
            Tool.hideDialog();
            GetKVKConfirm getKVKK = (GetKVKConfirm) response.body();

            if (getKVKK!=null){
                finish();
            }else{
                Tool.showInfo(this, getString(R.string.error), getString(R.string.available_token_not_found));
            }
        });

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
}
