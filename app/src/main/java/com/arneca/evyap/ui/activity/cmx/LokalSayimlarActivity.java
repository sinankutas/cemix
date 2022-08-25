package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 8/25/22.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import com.arneca.evyap.R;
import com.arneca.evyap.api.response.cmx.NewSayimModel;
import com.arneca.evyap.databinding.CmxopenDocsActivityBinding;
import com.arneca.evyap.databinding.LokalSayimlarBinding;
import com.arneca.evyap.helper.DBHelper;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.arneca.evyap.ui.adapter.cmx.LokalSayimlarAdapter;
import com.arneca.evyap.ui.adapter.cmx.OpenDocListAdapter;

import java.util.ArrayList;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


public class LokalSayimlarActivity extends BaseActivity {
    private DBHelper dbHelper ;
    private LokalSayimlarBinding binding;
    private LokalSayimlarAdapter adapter;
    private String viewTitle = "";
    private ArrayList<NewSayimModel> newSayimModels = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.lokal_sayimlar);
        dbHelper =  new DBHelper(this);
        PreferencesHelper.setSelectedCompany(null);
        Intent myIntent = getIntent(); // gets the previously created intent
        viewTitle = myIntent.getStringExtra("viewTitle");
        binding.toolbar.txtViewTitle.setText(viewTitle);
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        binding.toolbar.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        // set up the RecyclerView

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData() {
        newSayimModels = dbHelper.getAllNewSayim();
        binding.openDocList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LokalSayimlarAdapter(this, newSayimModels,viewTitle);
        binding.openDocList.setAdapter(adapter);

    }
}
