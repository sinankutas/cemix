package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 9/20/22.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.cmx.KarsilamaDetailResponse;
import com.arneca.evyap.api.response.cmx.KarsilamaResponse;
import com.arneca.evyap.databinding.KarsilamaListBinding;
import com.arneca.evyap.databinding.KarsilamaListDetailBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.arneca.evyap.ui.adapter.cmx.KarsilamaListAdapter;
import com.arneca.evyap.ui.adapter.cmx.KarsilamaListDetailAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class KarsilamaDetayActivity extends BaseActivity {

    private KarsilamaListDetailBinding binding;
    private KarsilamaListDetailAdapter adapter;
    private String viewTitle = "";
    private String seri = "";
    private String sira = "";
    protected void onCreate(Bundle savedInstanceState) {
        PreferencesHelper.setActiveDocType("Karsilama");

        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.karsilama_list_detail);

        PreferencesHelper.setSelectedCompany(null);
        Intent myIntent = getIntent(); // gets the previously created intent
        viewTitle = myIntent.getStringExtra("viewTitle");
        seri = myIntent.getStringExtra("seri");
        sira = myIntent.getStringExtra("sira");
        binding.toolbar.txtViewTitle.setText(viewTitle);
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(false);
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
        loadData(false);
    }

    public void loadData(boolean isFromDeleted) {

        Tool.openDialog(this);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("BelgeTuru", PreferencesHelper.getActiveDocType())
                .addFormDataPart("Seri",seri)
                .addFormDataPart("Sıra", sira)
                .build();

        Request.karsilamaListDetail(requestBody, this, response -> {
            KarsilamaDetailResponse karsilamaResponse = ( KarsilamaDetailResponse) response.body();
            response.headers();
            hideDialog();
            binding.swipeRefreshLayout.setRefreshing(false);
            binding.openDocList.setLayoutManager(new LinearLayoutManager(this));
            adapter = new KarsilamaListDetailAdapter(this, karsilamaResponse,viewTitle);
            binding.openDocList.setAdapter(adapter);

            if (karsilamaResponse.getResult()!=null){

            }else{
                binding.swipeRefreshLayout.setRefreshing(false);
                Tool.hideDialog();
                if (!isFromDeleted)
                    Tool.showInfo(this, "Bilgi", karsilamaResponse.getResult_message().getMessage());
            }
        });
    }
}
