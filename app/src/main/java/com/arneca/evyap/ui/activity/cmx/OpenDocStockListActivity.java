package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 8/15/22.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.cmx.OpenDocCompletedResponse;
import com.arneca.evyap.api.response.cmx.OpenDocumentListResponse;
import com.arneca.evyap.api.response.cmx.OpenDocumentStockListResponse;
import com.arneca.evyap.databinding.CmxOpendocStockActivityBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.arneca.evyap.ui.adapter.cmx.OpenDocListAdapter;
import com.arneca.evyap.ui.adapter.cmx.OpenDocStockListAdapter;
import com.arneca.evyap.ui.fragment.CompanyBottomFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class OpenDocStockListActivity extends BaseActivity {

    private CmxOpendocStockActivityBinding binding;
    private String guid = "";
    private String docId = "";
    private String orderNo = "";
    private String seriNo = "";
    private String viewTitle ;
    MaterialDialog dialog;
    private OpenDocStockListAdapter adapter;
    private CompanyBottomFragment companyBottomFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.cmx_opendoc_stock_activity);


        binding.toolbar.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (PreferencesHelper.getSelectedCompany()!=null){
            binding.txtSelectedCompany.setText(PreferencesHelper.getSelectedCompany().getAd());
        }
        Intent myIntent = getIntent(); // gets the previously created intent
        guid = myIntent.getStringExtra("guid");
        docId = myIntent.getStringExtra("docId");

        orderNo = myIntent.getStringExtra("orderNo");
        seriNo = myIntent.getStringExtra("seriNo");
        viewTitle = myIntent.getStringExtra("viewTitle");
        binding.toolbar.txtViewTitle.setText(viewTitle);
        binding.txtDocId.setText("Belge No: "+docId);
        binding.txtSeriId.setText("Seri No: "+seriNo);
        binding.txtOrderId.setText("Sıra No: "+orderNo);


        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        binding.lytCompanySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                companyBottomFragment = new CompanyBottomFragment().newInstance(false);
                companyBottomFragment.show(getSupportFragmentManager(), "");
            }
        });


        binding.addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpenDocStockListActivity.this, AddProductActivity.class);
                intent.putExtra("guid",guid);
                intent.putExtra("docId",docId);
                intent.putExtra("viewTitle",viewTitle);
                intent.putExtra("isStockActive",false);

                intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(intent);
            }
        });

        binding.btnCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PreferencesHelper.getSelectedCompany() != null){
                    new MaterialAlertDialogBuilder(OpenDocStockListActivity.this)
                            .setTitle("Bilgi")
                            .setMessage("Açık belgeler kapanacak.")
                            .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    completedDoc();
                                }
                            })
                            .setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .show();
                }else{
                    new MaterialAlertDialogBuilder(OpenDocStockListActivity.this)
                            .setTitle("Uyarı")
                            .setMessage("Cari Seçmelisiniz.")
                            .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .show();
                }
            }
        });
    }

    private void completedDoc() {
        Tool.openDialog(this);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("BelgeTuru", PreferencesHelper.getActiveDocType())
                .addFormDataPart("guid", guid)
                .addFormDataPart("cari_kod", PreferencesHelper.getSelectedCompany().getKod())
                .build();

        Request.openDocCompleted(requestBody, this, response -> {
            OpenDocCompletedResponse openDocCompletedResponse = ( OpenDocCompletedResponse) response.body();
            response.headers();
            hideDialog();
            if (openDocCompletedResponse!=null){
                new MaterialAlertDialogBuilder(OpenDocStockListActivity.this)
                        .setTitle("Bilgi")
                        .setMessage(openDocCompletedResponse.getResult_message().getMessage())
                        .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .show();
            }else{

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    public void setCompanyName(){
        binding.txtSelectedCompany.setText(PreferencesHelper.getSelectedCompany().getAd());
    }


    private void loadData() {
        Tool.openDialog(this);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("BelgeTuru", PreferencesHelper.getActiveDocType())
                .addFormDataPart("guid", guid)
                .build();

        Request.openDocStocks(requestBody, this, response -> {
            OpenDocumentStockListResponse openDocumentStockListResponse = ( OpenDocumentStockListResponse) response.body();
            response.headers();
            hideDialog();
            if (openDocumentStockListResponse.getResult()!=null){
                binding.swipeRefreshLayout.setRefreshing(false);
                binding.openDocList.setLayoutManager(new LinearLayoutManager(this));
                adapter = new OpenDocStockListAdapter(this, openDocumentStockListResponse);
                binding.openDocList.setAdapter(adapter);

            }else{
                Tool.hideDialog();
                Tool.showInfo(this, "Bilgi", openDocumentStockListResponse.getResult_message().getMessage());
            }
        });
    }
}
