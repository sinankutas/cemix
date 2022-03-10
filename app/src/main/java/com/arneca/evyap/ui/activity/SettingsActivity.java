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
import com.arneca.evyap.api.ReportModel;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.GetFactories;
import com.arneca.evyap.api.response.GetKPIKeys;
import com.arneca.evyap.databinding.SettingsBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.ReportIndex;
import com.arneca.evyap.helper.Tool;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.databinding.DataBindingUtil;

public class SettingsActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private SettingsBinding binding;
    ArrayList<String> factoryList,factoryListReplaced ;
    GetFactories factories;
    GetKPIKeys getKPIKeys;
    private ArrayList<ReportModel> reportNames = new ArrayList<>();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            factoryList = new ArrayList<>();
            getFactories();
            if (!PreferencesHelper.isIsAppOpenedFirst(this)){
                loadedReportPref();
            }
        }

    private void loadedReportPref() {
     /*   ArrayList<ReportModel> remportNames = new ArrayList<>();
        for (ReportModel reportModel: ReportIndex.getInstance().getReportModel()){
            reportModel.setPrefSelected(true);
            remportNames.add(reportModel);
        }
        PreferencesHelper.setReportModels(remportNames);

        PreferencesHelper.setTotalSelection(this,8);*/

        HashMap<String, Object> map = new HashMap<>();
        Request.GetKPIKeys( headersMap(true),map, this, response -> {
            Tool.hideDialog();
            getKPIKeys = (GetKPIKeys) response.body();
            if (getKPIKeys!=null){
                for (GetKPIKeys.DataBean data:this.getKPIKeys.getData()) {
                    ReportModel rp = new ReportModel();
                    rp.setReportName(data.getName());
                    rp.setReportId(data.getId());
                    rp.setPrefSelected(false);
                    reportNames.add(rp);
                }
                PreferencesHelper.setReportModels(reportNames);

            }else{
                Tool.showInfo(this, getString(R.string.error), getString(R.string.available_token_not_found));
            }
        });

        PreferencesHelper.setIsAppOpenedFirst(this,true);
    }

    private void setViewProperties() {
            factoryListReplaced = new ArrayList<>();
            for (String name : factoryList){
                if (name.equalsIgnoreCase("krem")){
                    name = "Kozmetik - Kişisel Bakım";
                }
                if (name.equalsIgnoreCase("likit")){
                    name = "Kozmetik - Kişisel Temizlik";
                }
                factoryListReplaced.add(name);
            }
        binding = DataBindingUtil.setContentView(this, R.layout.settings);
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   gotoFactoryActivity();
                }
            });

        Spinner spin = binding.spinner;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, factoryListReplaced);
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
                for (GetFactories.DataBean.MyArrayListBean factories : factories.getData().getMyArrayList()) {
                    factoryList.add(factories.getMap().getFactoryName());
                }
                setViewProperties();
                }else{
                    Tool.showInfo(this, getString(R.string.error), getString(R.string.available_token_not_found));
                }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
       PreferencesHelper.setSelectedFactory(factories.getData().getMyArrayList().get(position).getMap());
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }

}
