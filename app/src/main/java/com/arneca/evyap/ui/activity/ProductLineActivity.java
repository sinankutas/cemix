package com.arneca.evyap.ui.activity;/*
 * Created by Sinan KUTAS on 25.01.2021.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.arneca.evyap.R;
import com.arneca.evyap.api.DataModel;
import com.arneca.evyap.api.Movie;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.GetAllLineInfo;
import com.arneca.evyap.api.response.GetFactories;
import com.arneca.evyap.api.response.GetLineInfo;
import com.arneca.evyap.api.response.GetLines;
import com.arneca.evyap.databinding.ProductLineBinding;
import com.arneca.evyap.helper.AutoFitGridLayoutManager;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.adapter.RecAdapter;
import com.arneca.evyap.ui.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;


public class ProductLineActivity extends  BaseActivity{

    private ProductLineBinding binding;
    private GetLines lines;
    private GetAllLineInfo lineInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        getAllLineInfo();
    }


    private void getAllLineInfo(){
        Tool.openDialog(this);
        HashMap<String, Object> map = new HashMap<>();
        map.put("FactoryName", PreferencesHelper.getSelectedFactory().getFactoryName());
        Request.GetAllLineInfo( map, this, response -> {
            Tool.hideDialog();
            lineInfo = (GetAllLineInfo) response.body();
            if (lineInfo!=null){
                setViewProperties();
            }else{
                Tool.showInfo(this, getString(R.string.error), getString(R.string.available_token_not_found));
            }
        });
    }

    private void getLineInfo(String currentLine){
        Tool.openDialog(this);
        HashMap<String, Object> map = new HashMap<>();
        map.put("FactoryName", PreferencesHelper.getSelectedFactory().getFactoryName());
        map.put("LineName", currentLine);
        Request.GetLineInfo( map, this, response -> {
            Tool.hideDialog();
            GetLineInfo lineInfo = (GetLineInfo) response.body();
            if (lines!=null){
                setViewProperties();
            }else{
                Tool.showInfo(this, getString(R.string.error), getString(R.string.available_token_not_found));
            }
        });
    }

    private void setViewProperties() {
        binding = DataBindingUtil.setContentView(this, R.layout.product_line);

        RecAdapter adapter = new RecAdapter(lineInfo);

        RecyclerView recyclerView = findViewById(R.id.recview);

        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}
