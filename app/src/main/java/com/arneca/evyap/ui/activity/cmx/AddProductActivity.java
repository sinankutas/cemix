package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 8/16/22.
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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
    private boolean isFastSearchActive = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.cmxadd_product_activity);
        try {
            isFastSearchActive = PreferencesHelper.isIsFastSearchActive(AddProductActivity.this);
        }catch (Exception e){
            isFastSearchActive = false;
            PreferencesHelper.setIsFastSearchActive(AddProductActivity.this,false);
        }
        isFastSearchActive = PreferencesHelper.isIsFastSearchActive(AddProductActivity.this);
        Intent myIntent = getIntent(); // gets the previously created intent
        guid = myIntent.getStringExtra("guid");
        docId = myIntent.getStringExtra("docId");
        viewTitle = myIntent.getStringExtra("viewTitle");
        isStockActive = myIntent.getBooleanExtra("isStockActive",false);
        binding.toolbar.txtViewTitle.setText(viewTitle);
      //  showSoftKeyboard( binding.edtSearch);
        binding.edtSearch.setSelected(true);
        binding.edtSearch.setFocusable(true);

        if (PreferencesHelper.isIsFastSearchActive(AddProductActivity.this)){
            binding.btnPlasier1.setBackgroundResource(R.drawable.checkedbox);
            binding.btnPlasier1.setBackgroundTintList(getResources().getColorStateList(R.color.white));
        }else{
            binding.btnPlasier1.setBackgroundResource(R.drawable.uncheckedbox);
            binding.btnPlasier1.setBackgroundTintList(getResources().getColorStateList(R.color.white));
        }


        binding.lytFastSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PreferencesHelper.isIsFastSearchActive(AddProductActivity.this)){
                    PreferencesHelper.setIsFastSearchActive(AddProductActivity.this,false);
                    binding.btnPlasier1.setBackgroundResource(R.drawable.uncheckedbox);
                    binding.btnPlasier1.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                   // PreferencesHelper.setIsFastSearchActive(AddProductActivity.this,true);
                }else{
                    PreferencesHelper.setIsFastSearchActive(AddProductActivity.this,true);
                    binding.btnPlasier1.setBackgroundResource(R.drawable.checkedbox);
                    binding.btnPlasier1.setBackgroundTintList(getResources().getColorStateList(R.color.white));
              //      PreferencesHelper.setIsFastSearchActive(AddProductActivity.this,false);
                }


            }
        });

        binding.edtSearch.requestFocus();
    //    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        binding.edtSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                  //  Toast.makeText(AddProductActivity.this, binding.edtSearch.getText(), Toast.LENGTH_SHORT).show();
                    searchData();
                    return true;
                }
                return false;
            }
        });

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

    public void showSoftKeyboard(View view) {
        if(view.requestFocus()){
            InputMethodManager imm =(InputMethodManager)
                    getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
            imm.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (PreferencesHelper.isIsBackButtonActive()==false){
            if (binding.edtSearch.getText().toString().length()>0)
                searchData();
        }
        PreferencesHelper.setIsBackButtonActive(false);

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
            if (productSearchResponse.getResult()!=null){
                if (productSearchResponse.getResult().size()==1){
                    binding.swipeRefreshLayout.setRefreshing(false);
                    binding.openDocList.setLayoutManager(new LinearLayoutManager(this));
                    adapter = new ProductSearchAdapter(this, productSearchResponse,guid,docId,viewTitle,isStockActive);
                    binding.openDocList.setAdapter(adapter);
                    binding.edtSearch.setText("");
                    gotoRBMatris(productSearchResponse);
                }else{
                    binding.swipeRefreshLayout.setRefreshing(false);
                    binding.openDocList.setLayoutManager(new LinearLayoutManager(this));
                    adapter = new ProductSearchAdapter(this, productSearchResponse,guid,docId,viewTitle,isStockActive);
                    binding.openDocList.setAdapter(adapter);
                    binding.edtSearch.setText("");
                }
            }else{
                binding.swipeRefreshLayout.setRefreshing(false);
                Tool.hideDialog();
                Tool.showInfo(this, "Bilgi", productSearchResponse.getResult_message().getMessage());
            }
        });
    }

    private void gotoRBMatris(ProductSearchResponse productSearchResponse){
        Intent intent = new Intent(AddProductActivity.this, RBMatrisActivity.class);
        intent.putExtra("bedenId",String.valueOf(productSearchResponse.getResult().get(0).getBeden_id()));
        intent.putExtra("guid",guid);
        intent.putExtra("docId",docId);
        intent.putExtra("viewTitle",viewTitle);
        intent.putExtra("selectedCode",productSearchResponse.getResult().get(0).getKod());
        intent.putExtra("isStockActive",isStockActive);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
    }
}
