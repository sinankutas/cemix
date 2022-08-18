package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 8/16/22.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.cmx.OpenDocumentStockListResponse;
import com.arneca.evyap.api.response.cmx.ProductSearchResponse;
import com.arneca.evyap.databinding.CmxOpendocStockActivityBinding;
import com.arneca.evyap.databinding.CmxaddProductActivityBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.arneca.evyap.ui.adapter.cmx.OpenDocStockListAdapter;
import com.arneca.evyap.ui.adapter.cmx.ProductSearchAdapter;
import com.arneca.evyap.ui.fragment.CompanyBottomFragment;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddProductActivity  extends BaseActivity {
    private CmxaddProductActivityBinding binding;
    private ProductSearchAdapter adapter;
    private String guid = "";
    private String docId = "";
    private String viewTitle;
    private boolean isStockActive;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.cmxadd_product_activity);

        Intent myIntent = getIntent(); // gets the previously created intent
        guid = myIntent.getStringExtra("guid");
        docId = myIntent.getStringExtra("docId");
        viewTitle = myIntent.getStringExtra("viewTitle");
        isStockActive = myIntent.getBooleanExtra("isStockActive",false);
        binding.toolbar.txtViewTitle.setText(viewTitle);

        binding.edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                   searchData();
                    return true;
                }
                return false;
            }
        });

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchData();
            }
        });
        binding.toolbar.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchData();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (binding.edtSearch.getText().toString().length()>0)
           searchData();
    }

    private void searchData() {
        Tool.openDialog(this);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("src", binding.edtSearch.getText().toString())
                .build();

        Request.productSearch(requestBody, this, response -> {
            ProductSearchResponse productSearchResponse = ( ProductSearchResponse) response.body();
            response.headers();
            hideDialog();
            if (productSearchResponse!=null){
                binding.swipeRefreshLayout.setRefreshing(false);
                binding.openDocList.setLayoutManager(new LinearLayoutManager(this));
                adapter = new ProductSearchAdapter(this, productSearchResponse,guid,docId,viewTitle,isStockActive);
                binding.openDocList.setAdapter(adapter);

            }else{

            }
        });
    }
}
