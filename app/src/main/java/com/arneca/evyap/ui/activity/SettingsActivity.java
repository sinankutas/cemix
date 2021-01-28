package com.arneca.evyap.ui.activity;/*
 * Created by Sinan KUTAS on 22.01.2021.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.GetFactories;
import com.arneca.evyap.databinding.SettingsBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.databinding.DataBindingUtil;

public class SettingsActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private SettingsBinding binding;
    ArrayList<String> factoryList ;
    GetFactories factories;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            factoryList = new ArrayList<>();
            getFactories();
        }

    private void setViewProperties() {
        binding = DataBindingUtil.setContentView(this, R.layout.settings);
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   gotoFactoryActivity();
                }
            });

        Spinner spin = binding.spinner;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, factoryList);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
    }

    private void gotoFactoryActivity() {
        Intent intent = new Intent(this, ProductLineActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
        finish();
    }

    private void getFactories(){
        Tool.openDialog(this);
        HashMap<String, Object> map = new HashMap<>();
        map.put("string", "1");


        Request.GetFactories(headersMap(true), map, this, response -> {
            Tool.hideDialog();
            factories = (GetFactories) response.body();
            PreferencesHelper.setGetFactories(factories);
            if (factories!=null){
                for (GetFactories.data factories : factories.getData()) {
                    factoryList.add(factories.getFactoryName());
                }
                setViewProperties();
                }else{
                    Tool.showInfo(this, getString(R.string.error), getString(R.string.available_token_not_found));
                }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
       PreferencesHelper.setSelectedFactory(factories.getData().get(position));
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }

}
