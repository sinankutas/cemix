package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 8/25/22.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.arneca.evyap.R;

import com.arneca.evyap.api.response.cmx.NewSayimDetailModel;
import com.arneca.evyap.api.response.cmx.NewSayimModel;
import com.arneca.evyap.databinding.SayimUrunEkleBinding;
import com.arneca.evyap.helper.DBHelper;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.arneca.evyap.ui.adapter.cmx.OpenDocStockListAdapter;
import com.arneca.evyap.ui.fragment.CompanyBottomFragment;

import java.util.ArrayList;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class SayimUrunEkleActivity extends BaseActivity {

    private SayimUrunEkleBinding binding;

    private ArrayList<NewSayimModel> newSayimModels = new ArrayList<>();
    private String viewTitle;
    private String currentId;
    MaterialDialog dialog;
    private SayimUrunEkleAdapter adapter;
    private CompanyBottomFragment companyBottomFragment;
    private DBHelper dbHelper ;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.sayim_urun_ekle);

        dbHelper =  new DBHelper(this);
        newSayimModels =  dbHelper.getAllNewSayim();
        binding.toolbar.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        Intent myIntent = getIntent(); // gets the previously created intent
        viewTitle = myIntent.getStringExtra("viewTitle");
        currentId = myIntent.getStringExtra("currentId");
        binding.toolbar.txtViewTitle.setText(viewTitle);
        if (currentId.equals("")){
            currentId = ""+newSayimModels.get(newSayimModels.size()-1).getId();
        }

        binding.txtSayimId.setText("Sayim Id : "+currentId);
        binding.txtSeriId.setText("Açıklama : "+newSayimModels.get(newSayimModels.size()-1).getDesc());
        binding.txtOrderId.setText("Şube Kod : "+newSayimModels.get(newSayimModels.size()-1).getSubeCode());


        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });




        binding.addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SayimUrunEkleActivity.this, NewSayimActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                intent.putExtra("viewTitle", viewTitle);
                startActivity(intent);
            }
        });

        binding.btnCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PreferencesHelper.getSelectedCompany() != null){
                    Tool.showInfo2action(SayimUrunEkleActivity.this,"Uyarı",
                            "Belge kapansın mı?",
                            (dialog, which) ->  completedDoc(),
                            (dialog, which) -> dismissToolDialog(),"Evet","Hayır");
                }else{
                    Tool.showInfo(SayimUrunEkleActivity.this,"Uyarı",
                            "Cari Seçmelisiniz.",
                            (dialog, which) ->  showCompanies(),"Tamam");


                }
            }
        });
    }

    private void showCompanies() {
        companyBottomFragment = new CompanyBottomFragment().newInstance(false);
        companyBottomFragment.show(getSupportFragmentManager(), "");
    }

    private void dismissToolDialog(){

    }


    private void completedDoc() {
        Tool.openDialog(this);

    }

    private void dismissToolDialogFinish() {
        finish();
        PreferencesHelper.setIsRecordScreenClose(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData() {
        // idler eski sayim ve yeni sayıma göre değie
      ArrayList<NewSayimDetailModel> newSayimDetailModels = dbHelper.getSayimDetail(""+currentId);
        binding.openDocList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SayimUrunEkleAdapter(this, newSayimDetailModels);
        binding.openDocList.setAdapter(adapter);
    }
}
