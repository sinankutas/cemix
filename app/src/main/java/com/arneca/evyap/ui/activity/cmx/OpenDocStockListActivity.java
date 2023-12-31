package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 8/15/22.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.cmx.AddNoteResponse;
import com.arneca.evyap.api.response.cmx.FooterInfoResponse;
import com.arneca.evyap.api.response.cmx.OpenDocCompletedResponse;
import com.arneca.evyap.api.response.cmx.OpenDocumentListResponse;
import com.arneca.evyap.api.response.cmx.OpenDocumentStockListResponse;
import com.arneca.evyap.api.response.cmx.PDFResponse;
import com.arneca.evyap.databinding.CmxOpendocStockActivityBinding;
import com.arneca.evyap.databinding.PlasierBottomBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.arneca.evyap.ui.adapter.cmx.OpenDocListAdapter;
import com.arneca.evyap.ui.adapter.cmx.OpenDocStockListAdapter;
import com.arneca.evyap.ui.fragment.CompanyBottomFragment;
import com.arneca.evyap.ui.fragment.PlasierBottomFragment;
import com.arneca.evyap.ui.fragment.TanimBottomSheetFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
    private String cariKod = "";
    private String belgeNotu = "";
    private PlasierBottomFragment plasierBottomFragment;
    private String viewTitle ;
    MaterialDialog dialog;
    private OpenDocStockListAdapter adapter;
    private CompanyBottomFragment companyBottomFragment;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.cmx_opendoc_stock_activity);
        PreferencesHelper.setOpenDocStockListActivity(this);

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
        belgeNotu = myIntent.getStringExtra("belgeNotu");

        if (belgeNotu == null)
            belgeNotu = "";

        cariKod = myIntent.getStringExtra("cariKod");

        orderNo = myIntent.getStringExtra("orderNo");
        seriNo = myIntent.getStringExtra("seriNo");
        viewTitle = myIntent.getStringExtra("viewTitle");
        binding.toolbar.txtViewTitle.setText(viewTitle);
        binding.txtDocId.setText("Belge No "+docId);
        binding.txtSeriId.setText("Seri "+seriNo);
        binding.txtOrderId.setText("Sıra "+orderNo);


        binding.toolbar2.openPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpenDocStockListActivity.this, PDFViewerActivity.class);
                intent.putExtra("pdfUrl","");
                intent.putExtra("guid",guid);
                intent.putExtra("viewTitle","PDF Görüntüleme");
                intent.putExtra("isKarsilamaAcvite",false);
                intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(intent);
            }
        });

        binding.toolbar2.addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotePopup();
            }
        });


        binding.toolbar2.print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printPDF();
            }
        });


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
                binding.btnCompleted.setVisibility(View.INVISIBLE);
                // call plasier
                if (PreferencesHelper.getActiveDocType().equals("siparis") || PreferencesHelper.getActiveDocType().equals("teklif")|| PreferencesHelper.getActiveDocType().equals("fuar")){
                    plasierBottomFragment = new PlasierBottomFragment().newInstance();
                   // plasierBottomFragment.show(getSupportFragmentManager(), "");
                    binding.btnCompleted.setVisibility(View.VISIBLE);
                    openAnotherFragment(plasierBottomFragment, R.id.mainContainer);
                    binding.frameBottom.setVisibility(View.GONE);
                }else{
                    gotoCompletedDoc("","","","","","","","","");
                }
            }
        });
    }

    public void callOnBack(){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
            binding.frameBottom.setVisibility(View.VISIBLE);
        }

    }

    public void replaceFragment(Fragment fragment, Bundle bundle) {

        if (bundle != null)
            fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment oldFragment = fragmentManager.findFragmentByTag(fragment.getClass().getName());

        //if oldFragment already exits in fragmentManager use it
        if (oldFragment != null) {
            fragment = oldFragment;
        }

        fragmentTransaction.replace(R.id.mainContainer, fragment, fragment.getClass().getName());

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        fragmentTransaction.commit();
    }

    public void closePlasierFragment(){
        binding.mainContainer.setVisibility(View.GONE);
    }

    private void showNotePopup() {
        final EditText txtDescription = new EditText(this);

        if (belgeNotu.length()>0)
         txtDescription.setHint(belgeNotu);
        else
         txtDescription.setHint("Açıklama");
        new AlertDialog.Builder(this)
                .setTitle("Açıklama")
                .setMessage("")
                .setView(txtDescription)
                .setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog1, int whichButton) {
                        String desc = txtDescription.getText().toString();
                        if (desc.length()>0){
                            saveNote(desc);
                        }else{
                            Tool.showInfo(OpenDocStockListActivity.this, "Uyarı", "Açıklama girmelisiniz");
                        }
                    }
                })
                .setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();

    }

    private void saveNote(String note) {
        Tool.openDialog(this);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("BelgeTuru", PreferencesHelper.getActiveDocType())
                .addFormDataPart("guid", guid)
                .addFormDataPart("BelgeNotu", note)
                .build();

        Request.docNote(requestBody, this, response -> {
            AddNoteResponse pdfResponse = ( AddNoteResponse) response.body();
            response.headers();
            hideDialog();
            Tool.showInfo(this, "Bilgi", pdfResponse.getResult_message().getMessage());
        });
    }

    private void showCompanies() {
        binding.btnCompleted.setVisibility(View.VISIBLE);
        companyBottomFragment = new CompanyBottomFragment().newInstance(false);
        companyBottomFragment.show(getSupportFragmentManager(), "");
    }

    private void dismissToolDialog(){
        binding.btnCompleted.setVisibility(View.VISIBLE);
    }


    private void printPDF() {
        Tool.openDialog(this);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("BelgeTuru", PreferencesHelper.getActiveDocType())
                .addFormDataPart("guid", guid)
                .build();

        Request.getPrint(requestBody, this, response -> {
            PDFResponse pdfResponse = ( PDFResponse) response.body();
            response.headers();
            hideDialog();
            Tool.showInfo(this, "Bilgi", pdfResponse.getResult_message().getMessage());
        });
    }

    private void completedDoc(String plasier, String name, String contry, String cargo, String tel, String tahsilat_tutar, String tahsilat_para_birimi, String tahsilat_aciklama, String tahsilatType) {
        binding.btnCompleted.setVisibility(View.VISIBLE);
        Tool.openDialog(this);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("BelgeTuru", PreferencesHelper.getActiveDocType())
                .addFormDataPart("guid", guid)
                .addFormDataPart("cari_kod", PreferencesHelper.getSelectedCompany().getKod())
                .addFormDataPart("plasiyer_kodu", plasier)
                .addFormDataPart("a1", name)
                .addFormDataPart("a2", contry)
                .addFormDataPart("a3", cargo)
                .addFormDataPart("a4", tel)
                .addFormDataPart("gorsel", PreferencesHelper.getCurrentBase64())
                .addFormDataPart("doviz_kuru", PreferencesHelper.getSelectedCurrency())
                .addFormDataPart("tahsilat_tutar", tahsilat_tutar)
                .addFormDataPart("tahsilat_para_birimi", tahsilat_para_birimi)
                .addFormDataPart("tahsilat_aciklama", tahsilat_aciklama)
                .addFormDataPart("tahsilat_turu", tahsilatType)


                .build();

        Request.openDocCompleted(requestBody, this, response -> {
            OpenDocCompletedResponse openDocCompletedResponse = ( OpenDocCompletedResponse) response.body();
            response.headers();
            hideDialog();
            if (openDocCompletedResponse!=null){

                Tool.showInfo(OpenDocStockListActivity.this,"Bilgi",
                        openDocCompletedResponse.getResult_message().getMessage(),
                        (dialog, which) ->  dismissToolDialogFinish(),"Tamam");


            }else{

            }
        });
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

    public void setCompanyName(){
        binding.txtSelectedCompany.setText(PreferencesHelper.getSelectedCompany().getAd());
    }


    public void loadData() {
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
            binding.swipeRefreshLayout.setRefreshing(false);
            binding.openDocList.setLayoutManager(new LinearLayoutManager(this));
            adapter = new OpenDocStockListAdapter(this, openDocumentStockListResponse,guid);
            binding.openDocList.setAdapter(adapter);
            loadFooterData();
            if (openDocumentStockListResponse.getResult()!=null){


            }else{
                binding.swipeRefreshLayout.setRefreshing(false);
                Tool.hideDialog();
                // Tool.showInfo(this, "Bilgi", openDocumentStockListResponse.getResult_message().getMessage());
            }
        });
    }

    private void loadFooterData() {
        Tool.openDialog(this);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("BelgeTuru", PreferencesHelper.getActiveDocType())
                .addFormDataPart("guid", guid)
                .build();

        Request.getFooterInfo(requestBody, this, response -> {
            FooterInfoResponse footerInfoResponse = ( FooterInfoResponse) response.body();
            response.headers();
            hideDialog();

            if (footerInfoResponse.getResult()!=null){
                binding.txtSayi.setText("Sayi: "+footerInfoResponse.getResult().getSayi());
                binding.txtAmount.setText("Adet: "+footerInfoResponse.getResult().getMiktar());
                binding.txtPrice.setText("Tutar: "+footerInfoResponse.getResult().getTutar()+" $");
            }else{
                Tool.hideDialog();
                // Tool.showInfo(this, "Bilgi", openDocumentStockListResponse.getResult_message().getMessage());
            }
        });
    }

    public void gotoCompletedDoc(String selectedPlasier,String name,String country,String cargo,String tel,String tahsilat_tutar,String tahsilat_para_birimi,String tahsilat_aciklama, String tahsilatType) {
        if (PreferencesHelper.getSelectedCompany() != null){
            Tool.showInfo2action(OpenDocStockListActivity.this,"Uyarı",
                    "Belge kapansın mı?",
                    (dialog, which) ->  completedDoc(selectedPlasier, name,country,cargo,tel,tahsilat_tutar,tahsilat_para_birimi,tahsilat_aciklama, tahsilatType),
                    (dialog, which) -> dismissToolDialog(),"Evet","Hayır");
        }else{
            Tool.showInfo(OpenDocStockListActivity.this,"Uyarı",
                    "Cari Seçmelisiniz.",
                    (dialog, which) ->  showCompanies(),"Tamam");


        }
    }
}