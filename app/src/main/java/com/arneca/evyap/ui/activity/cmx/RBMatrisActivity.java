package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 8/16/22.
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.cmx.ProductSearchResponse;
import com.arneca.evyap.api.response.cmx.RBMatrisResponse;
import com.arneca.evyap.api.response.cmx.STHEkleRespone;
import com.arneca.evyap.databinding.CmxOpendocStockActivityBinding;
import com.arneca.evyap.databinding.RbmatrisActivityBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.arneca.evyap.ui.adapter.cmx.ProductSearchAdapter;
import com.arneca.evyap.ui.adapter.cmx.RBMatrisAdapter;
import com.google.gson.JsonArray;

import org.json.JSONArray;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RBMatrisActivity extends BaseActivity {

    private int currentSelectedIndex = 0;
    private RbmatrisActivityBinding binding;
    private RBMatrisAdapter adapter;
    private String bedenId = "";
    private String guid = "";
    private String docId = "";
    private String viewTitle = "";
    private boolean isStockActive;

    private JSONArray jsonArray = new JSONArray();
    RBMatrisResponse rbMatrisResponse ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.rbmatris_activity);

        Intent myIntent = getIntent(); // gets the previously created intent
        bedenId = myIntent.getStringExtra("bedenId");
        guid = myIntent.getStringExtra("guid");
        docId = myIntent.getStringExtra("docId");
        viewTitle = myIntent.getStringExtra("viewTitle");
        isStockActive = myIntent.getBooleanExtra("isStockActive",false);
        binding.toolbar.txtViewTitle.setText(viewTitle);
        binding.toolbar2.rightContainer.setVisibility(View.INVISIBLE);
        loadData();

        if (isStockActive)
            binding.toolbar2.rightContainer.setVisibility(View.INVISIBLE);


        binding.toolbar2.rightContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDoc();
            }
        });

        binding.toolbar2.txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDoc();
            }
        });

        binding.toolbar2.txtRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.toolbar2.leftContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        binding.toolbar.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.txtTab1.setBackground(getResources().getDrawable(R.drawable.circle_blue_back));
        binding.txtTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSelectedIndex = 0;
                binding.txtTab1.setBackground(getResources().getDrawable(R.drawable.circle_blue_back));

                binding.txtTab2.setBackgroundColor(getResources().getColor(R.color.white));
                binding.txtTab3.setBackgroundColor(getResources().getColor(R.color.white));
                loadTableData(rbMatrisResponse);
            }
        });

        binding.txtTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSelectedIndex = 1;
                binding.txtTab2.setBackground(getResources().getDrawable(R.drawable.circle_blue_back));

                binding.txtTab1.setBackgroundColor(getResources().getColor(R.color.white));
                binding.txtTab3.setBackgroundColor(getResources().getColor(R.color.white));
                loadTableData(rbMatrisResponse);
            }
        });

        binding.txtTab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSelectedIndex = 2;
                binding.txtTab3.setBackground(getResources().getDrawable(R.drawable.circle_blue_back));

                binding.txtTab2.setBackgroundColor(getResources().getColor(R.color.white));
                binding.txtTab1.setBackgroundColor(getResources().getColor(R.color.white));
                loadTableData(rbMatrisResponse);
            }
        });
    }

    public void setJsonArray(JSONArray jsonArray){
        this.jsonArray = jsonArray;
        if (jsonArray.length()>0){
            binding.toolbar2.rightContainer.setVisibility(View.VISIBLE);
        }else{
            binding.toolbar2.rightContainer.setVisibility(View.INVISIBLE);
        }
    }

    private void saveDoc() {
        Tool.openDialog(this);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("BelgeTuru", PreferencesHelper.getActiveDocType())
                .addFormDataPart("guid", guid)
                .addFormDataPart("belge_id", docId)
                .addFormDataPart("Detay", this.jsonArray.toString())
                .build();

        Request.sthEkle(requestBody, this, response -> {
            STHEkleRespone sTHEkleRespone = ( STHEkleRespone) response.body();
            response.headers();
            hideDialog();
            if (sTHEkleRespone!=null){
               // iF SUCCESS  show alert
                Tool.showInfo(this,"Bilgi",sTHEkleRespone.getResult_message().getMessage());
                binding.toolbar2.rightContainer.setVisibility(View.GONE);
                finish();
            }else{
                Tool.showInfo(this,"Hata","Kayıt İşlemi Başarısız.");
            }
        });
    }

    private void loadData() {
        Tool.openDialog(this);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("beden_id", bedenId)
                .build();

        Request.rbMAtris(requestBody, this, response -> {
            rbMatrisResponse = ( RBMatrisResponse) response.body();
            response.headers();
            hideDialog();
            if (rbMatrisResponse.getResult()!=null){
                loadTableData(rbMatrisResponse);
            }else{
                Tool.hideDialog();
                Tool.showInfo(this, "Bilgi", rbMatrisResponse.getResult_message().getMessage());
            }
        });
    }

    private void loadTableData(RBMatrisResponse rbMatrisResponse) {
        setViews(rbMatrisResponse);
        binding.openDocList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RBMatrisAdapter(this, rbMatrisResponse,currentSelectedIndex,isStockActive);
        binding.openDocList.setAdapter(adapter);
    }


    private void setViews(RBMatrisResponse rbMatrisResponse) {
        if (rbMatrisResponse.getResult()!=null){
            if (rbMatrisResponse.getResult().get(0)!=null){

                binding.txtTab1.setText(rbMatrisResponse.getResult().get(currentSelectedIndex).getKod());
                binding.txtProductTitle.setText(rbMatrisResponse.getResult().get(currentSelectedIndex).getAd());
                binding.txtProductTitle.setText(rbMatrisResponse.getResult().get(currentSelectedIndex).getAd());
                binding.txtPrice.setText(rbMatrisResponse.getResult().get(currentSelectedIndex).getFiyat()+" $ ");


            }

            if (rbMatrisResponse.getResult().get(1)!=null){
                binding.txtTab2.setText(rbMatrisResponse.getResult().get(1).getKod());
                binding.txtProductTitle.setText(rbMatrisResponse.getResult().get(1).getAd());
                binding.txtPrice.setText(rbMatrisResponse.getResult().get(1).getFiyat()+" $ ");
            }

            if (rbMatrisResponse.getResult().get(2)!=null){
                binding.txtTab3.setText(rbMatrisResponse.getResult().get(2).getKod());
                binding.txtProductTitle.setText(rbMatrisResponse.getResult().get(2).getAd());
                binding.txtPrice.setText(rbMatrisResponse.getResult().get(2).getFiyat()+" $ ");
            }

        }

    }
}
