package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 8/17/22.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.cmx.LoginResponse;
import com.arneca.evyap.api.response.cmx.NewDocResponse;
import com.arneca.evyap.api.response.cmx.OpenDocCompletedResponse;
import com.arneca.evyap.api.response.cmx.OpenDocRecordsResponse;
import com.arneca.evyap.api.response.cmx.ProductSearchResponse;
import com.arneca.evyap.databinding.CmxaddProductActivityBinding;
import com.arneca.evyap.databinding.OpenDocrecordActivityBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.arneca.evyap.ui.adapter.cmx.ProductSearchAdapter;
import com.arneca.evyap.ui.fragment.CompanyBottomFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class OpenDocRecordsActivity extends BaseActivity {
    private OpenDocrecordActivityBinding binding;
    private ProductSearchAdapter adapter;
    private String viewTitle = "";
    private CompanyBottomFragment companyBottomFragment;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.open_docrecord_activity);
        Intent myIntent = getIntent(); // gets the previously created intent
        viewTitle = myIntent.getStringExtra("viewTitle");
        binding.toolbar.txtViewTitle.setText(viewTitle);
        binding.toolbar.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

             /*
        * [17:59, 18.08.2022] Mahmut Dokumacı: PERAKENDE SATIŞ MÜŞTERİLERİ
        default code120.34.001
        * */
        for (LoginResponse.ResultBean.CarilerBean carilerBean : PreferencesHelper.getLoginResponse().getResult().getCariler()){
            if (carilerBean.getKod().equals("120.99.01")){//"120.34.001")){
                PreferencesHelper.setSelectedCompany(carilerBean);
            }
        }


    }

    private void loadData() {
    //    Tool.openDialog(this);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("BelgeTuru", PreferencesHelper.getActiveDocType())
                .build();

        Request.openDocRecords(requestBody, this, response -> {
            OpenDocRecordsResponse openDocRecordsResponse = ( OpenDocRecordsResponse) response.body();
     //       response.headers();
            hideDialog();
            if (openDocRecordsResponse.getResult()!=null){
                if (openDocRecordsResponse.getResult().getAcikBelgeSayisi()>0){
                    getRecordMessage(openDocRecordsResponse.getResult_message().getMessage(),openDocRecordsResponse.getResult().getAcikBelgeSayisi());
                }else{

                    gotoOpenDocStockList();
                 //   companyBottomFragment = new CompanyBottomFragment().newInstance(true);
                  //  companyBottomFragment.show(getSupportFragmentManager(), "");
                }
            }else{
                Tool.hideDialog();
                Tool.showInfo(this, "Bilgi", openDocRecordsResponse.getResult_message().getMessage());
            }
        });
    }

    public void finishPage(){
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

       if ( PreferencesHelper.isIsRecordScreenClose()){
           finish();
           PreferencesHelper.setIsRecordScreenClose(false);
       }else{
           loadData();
       }
    }

    private void getRecordMessage(String message, int acikBelgeSayisi) {
        hideDialog();
        Tool.showInfo2action(OpenDocRecordsActivity.this,"Bilgi",
                acikBelgeSayisi+" "+message,
                (dialog, which) -> gotoDocList(),
                (dialog, which) -> openCariBottom(),"Açık Belge Listesi","Yeni Belge Oluştur");
    }


    public void openCariBottom(){
       // companyBottomFragment = new CompanyBottomFragment().newInstance(true);
        //companyBottomFragment.show(getSupportFragmentManager(), "");
        gotoOpenDocStockList();
    }

    public void gotoDocList(){
        Intent intent = new Intent(OpenDocRecordsActivity.this, OpenDocListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        intent.putExtra("viewTitle",viewTitle);
        OpenDocRecordsActivity.this.startActivity(intent);
    }

    public void gotoOpenDocStockList() {
        if (PreferencesHelper.getSelectedCompany() == null){
            for (LoginResponse.ResultBean.CarilerBean carilerBean : PreferencesHelper.getLoginResponse().getResult().getCariler()){
                if (carilerBean.getKod().equals("120.99.01")){//"120.34.001")){
                    PreferencesHelper.setSelectedCompany(carilerBean);
                }
            }
        }


        Tool.openDialog(this);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("BelgeTuru", PreferencesHelper.getActiveDocType())
                .addFormDataPart("cari_kod", PreferencesHelper.getSelectedCompany().getKod())
                .build();

        Request.createNewDoc(requestBody, this, response -> {
            NewDocResponse newDocResponse = ( NewDocResponse) response.body();
            response.headers();
            hideDialog();
            if (newDocResponse.getResult()!=null){
                Intent intent = new Intent(OpenDocRecordsActivity.this, OpenDocStockListActivity.class);
                intent.putExtra("guid",String.valueOf(newDocResponse.getResult().getGuid()));
                intent.putExtra("docId",String.valueOf(newDocResponse.getResult().getBelge_id()));
                intent.putExtra("orderNo",String.valueOf(newDocResponse.getResult().getSira()));
                intent.putExtra("seriNo",String.valueOf(newDocResponse.getResult().getSeri()));
                intent.putExtra("viewTitle",viewTitle);

                intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(intent);
            }else{
                Tool.hideDialog();
                Tool.showInfo(this, "Bilgi", newDocResponse.getResult_message().getMessage());
            }
        });
    }
}
