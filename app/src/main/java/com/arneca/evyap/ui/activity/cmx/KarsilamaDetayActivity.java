package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 9/20/22.
 */

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.cmx.KarsilamaDetailResponse;
import com.arneca.evyap.api.response.cmx.KarsilamaModel;
import com.arneca.evyap.api.response.cmx.KarsilamaResponse;
import com.arneca.evyap.api.response.cmx.PDFResponse;
import com.arneca.evyap.databinding.KarsilamaListBinding;
import com.arneca.evyap.databinding.KarsilamaListDetailBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.arneca.evyap.ui.adapter.cmx.KarsilamaListAdapter;
import com.arneca.evyap.ui.adapter.cmx.KarsilamaListDetailAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    private String adet = "";
    private String sayi = "0";
    private String subeName = "";
    KarsilamaDetailResponse karsilamaResponseFromAdapter = new KarsilamaDetailResponse() ;
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
        adet = myIntent.getStringExtra("adet");
        sayi = myIntent.getStringExtra("sayi");
        subeName = myIntent.getStringExtra("subeName");
        binding.toolbar.txtViewTitle.setText(viewTitle);
        binding.toolbar2.addNote.setVisibility(View.GONE);
        binding.txtSube.setText("Şube: "+subeName);
        binding.txtSeriId.setText("Seri: "+seri);
        binding.txtOrderId.setText("Sıra: "+sira);
        binding.txtCompleted.setPaintFlags(binding.txtCompleted.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        binding.txtAmount.setText("Adet: "+adet);
        binding.txtSayi.setText("Sayı: "+sayi);

        binding.toolbar2.print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // print
                printPDF();
            }
        });

        binding.toolbar2.openPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // openPDF
                Intent intent = new Intent(KarsilamaDetayActivity.this, PDFViewerActivity.class);
                intent.putExtra("pdfUrl","");
                intent.putExtra("guid","guid");
                intent.putExtra("seri",seri);
                intent.putExtra("sira",sira);
                intent.putExtra("viewTitle","PDF Görüntüleme");
                intent.putExtra("isKarsilamaAcvite",true);
                intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(intent);
            }
        });

        binding.txtCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completedDoc();
            }
        });

        showCompletedButton(false,karsilamaResponseFromAdapter);
/*        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(false);
            }
        });*/
        binding.toolbar.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        // set up the RecyclerView

    }


    private void printPDF() {
        Tool.openDialog(this);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("BelgeTuru", PreferencesHelper.getActiveDocType())
                .addFormDataPart("Seri", seri)
                .addFormDataPart("Sira", sira)
                .build();

        Request.getKarsilamaPrint(requestBody, this, response -> {
            PDFResponse pdfResponse = ( PDFResponse) response.body();
            response.headers();
            hideDialog();
            Tool.showInfo(this, "Bilgi", pdfResponse.getResult_message().getMessage());
        });
    }


    private void completedDoc() {
        JSONArray jsArray =  prepareDetailObjects();
        Tool.openDialog(this);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("BelgeTuru", PreferencesHelper.getActiveDocType())
                .addFormDataPart("Seri",seri)
                .addFormDataPart("Sira", sira)
                .addFormDataPart("Detay", jsArray.toString())
                .build();

        Request.karsilamaTamamla(requestBody, this, response -> {
            KarsilamaDetailResponse  karsilamaResponse = ( KarsilamaDetailResponse) response.body();
            response.headers();
            hideDialog();


            if (karsilamaResponse.getResult()!=null){
                //    binding.swipeRefreshLayout.setRefreshing(false);
                binding.openDocList.setLayoutManager(new LinearLayoutManager(this));
                adapter = new KarsilamaListDetailAdapter(this, karsilamaResponse,viewTitle,seri,sira);
                binding.openDocList.setAdapter(adapter);
                binding.txtSayi.setText("Sayı: "+""+karsilamaResponse.getResult().size());
            }else{
                //      binding.swipeRefreshLayout.setRefreshing(false);
                Tool.hideDialog();
                Tool.showInfo(this, "Bilgi", karsilamaResponse.getResult_message().getMessage());
            }
        });
    }

    private  JSONArray prepareDetailObjects() {
        JSONArray jsArray = new JSONArray();

        for (KarsilamaDetailResponse.ResultBean resultBean : karsilamaResponseFromAdapter.getResult()){
            JSONObject obj = new JSONObject();
            try {
                obj.put("verilen_miktar",""+resultBean.getStock());
                obj.put("sth_uuid",resultBean.getSth_uuid());
                obj.put("har_uuid",resultBean.getHar_uuid());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsArray.put(obj);
        }
        return jsArray;
    }

    public void showCompletedButton(boolean isShow, KarsilamaDetailResponse karsilamaDetailResponse){
        if (isShow){
            binding.txtCompleted.setVisibility(View.VISIBLE);
        } else{
            binding.txtCompleted.setVisibility(View.INVISIBLE);
        }
        karsilamaResponseFromAdapter = karsilamaDetailResponse;

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
                .addFormDataPart("Sira", sira)
                .build();

        Request.karsilamaListDetail(requestBody, this, response -> {
            KarsilamaDetailResponse karsilamaResponse = ( KarsilamaDetailResponse) response.body();
            response.headers();
            hideDialog();


            if (karsilamaResponse.getResult()!=null){
            //    binding.swipeRefreshLayout.setRefreshing(false);
                binding.openDocList.setLayoutManager(new LinearLayoutManager(this));
                adapter = new KarsilamaListDetailAdapter(this, karsilamaResponse,viewTitle,seri,sira);
                binding.openDocList.setAdapter(adapter);
                binding.txtSayi.setText("Sayı: "+""+karsilamaResponse.getResult().size());
            }else{
                //      binding.swipeRefreshLayout.setRefreshing(false);
                Tool.hideDialog();
                if (!isFromDeleted)
                    Tool.showInfo(this, "Bilgi", karsilamaResponse.getResult_message().getMessage());
            }
        });
    }
}
