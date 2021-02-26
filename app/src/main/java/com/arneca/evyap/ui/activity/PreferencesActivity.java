package com.arneca.evyap.ui.activity;/*
 * Created by Sinan KUTAS on 28.01.2021.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.GetAllLineByKey;
import com.arneca.evyap.api.response.GetKPIKeys;
import com.arneca.evyap.databinding.PreferencesBinding;
import com.arneca.evyap.databinding.PreferencesBindingImpl;
import com.arneca.evyap.databinding.ProductLineBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.adapter.PreferencesAdapter;
import com.arneca.evyap.ui.adapter.RecAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

public class PreferencesActivity extends  BaseActivity{

    private PreferencesBinding binding;
    private GetKPIKeys getKPIKeys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        loadPrefData();
    }


    private void loadPrefData(){
        Tool.openDialog(this);
        HashMap<String, Object> map = new HashMap<>();
        Request.GetKPIKeys( headersMap(true),map, this, response -> {
            Tool.hideDialog();
            getKPIKeys = (GetKPIKeys) response.body();
            if (getKPIKeys!=null){
                setViewProperties();
            }else{
                Tool.showInfo(this, getString(R.string.error), getString(R.string.available_token_not_found));
            }
        });
    }

    private void setViewProperties() {
        binding = DataBindingUtil.setContentView(this, R.layout.preferences);
      binding.toolbar.back.setVisibility(View.VISIBLE);
        binding.toolbar.changeData.setVisibility(View.GONE);
        binding.totalSelection.setText("* Toplamda "+PreferencesHelper.getTotalSelection(this)+" seçim");

        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.toolbar.settins.setVisibility(View.GONE);
        PreferencesAdapter adapter = new PreferencesAdapter(getKPIKeys,this);
        binding.recview.setVisibility(View.VISIBLE);
        ((SimpleItemAnimator)   binding.recview.getItemAnimator()).setSupportsChangeAnimations(false);

        binding.recview.setLayoutManager(new LinearLayoutManager(this));
        binding.recview.setAdapter(adapter);
        binding.recview.setHasFixedSize(true);

        binding.toolbar.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
        finish();
    }

    public void changeTotalSelection(boolean isIncresase){
        int totalSec = PreferencesHelper.getTotalSelection(this);
        if (isIncresase)
            totalSec += 1;
        else
            totalSec -= 1;

        PreferencesHelper.setTotalSelection(this, totalSec);
        binding.totalSelection.setText("* Toplamda "+PreferencesHelper.getTotalSelection(this)+" seçim");
    }
}
