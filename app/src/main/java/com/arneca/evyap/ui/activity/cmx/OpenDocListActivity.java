package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 8/15/22.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.cmx.LoginResponse;
import com.arneca.evyap.api.response.cmx.OpenDocumentListResponse;
import com.arneca.evyap.databinding.CmxmenuGridItemBinding;
import com.arneca.evyap.databinding.CmxopenDocsActivityBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.arneca.evyap.ui.adapter.cmx.OpenDocListAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class OpenDocListActivity extends BaseActivity {
    private CmxopenDocsActivityBinding cmxopenDocsActivityBinding;
    private OpenDocListAdapter adapter;
    private String viewTitle = "";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        cmxopenDocsActivityBinding = DataBindingUtil.setContentView(this, R.layout.cmxopen_docs_activity);


        Intent myIntent = getIntent(); // gets the previously created intent
        viewTitle = myIntent.getStringExtra("viewTitle");
        cmxopenDocsActivityBinding.toolbar.txtViewTitle.setText(viewTitle);
        cmxopenDocsActivityBinding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        cmxopenDocsActivityBinding.toolbar.backButton.setOnClickListener(new View.OnClickListener() {
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

    private void loadData() {

        Tool.openDialog(this);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("BelgeTuru", "satis")
                .build();

        Request.openDocs(requestBody, this, response -> {
            OpenDocumentListResponse openDocumentListResponse = ( OpenDocumentListResponse) response.body();
            response.headers();
            hideDialog();
            if (openDocumentListResponse!=null){
                cmxopenDocsActivityBinding.swipeRefreshLayout.setRefreshing(false);
                cmxopenDocsActivityBinding.openDocList.setLayoutManager(new LinearLayoutManager(this));
                adapter = new OpenDocListAdapter(this, openDocumentListResponse,viewTitle);
                cmxopenDocsActivityBinding.openDocList.setAdapter(adapter);

            }else{

            }
        });
    }
}
