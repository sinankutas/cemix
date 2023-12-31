package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 8/16/22.
 */

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.cmx.ProductSearchResponse;
import com.arneca.evyap.api.response.cmx.RBMatrisResponse;
import com.arneca.evyap.api.response.cmx.STHEkleRespone;
import com.arneca.evyap.databinding.CmxOpendocStockActivityBinding;
import com.arneca.evyap.databinding.RbmatrisActivityBinding;
import com.arneca.evyap.helper.AndroidBug5497Workaround2;
import com.arneca.evyap.helper.CustomEditTextBottomPopup;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.arneca.evyap.ui.adapter.cmx.ProductSearchAdapter;
import com.arneca.evyap.ui.adapter.cmx.RBMatrisAdapter;
import com.google.gson.JsonArray;
import com.lxj.xpopup.XPopup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RBMatrisActivity extends BaseActivity {
    private int currentSelectedIndex = 0;
    public RbmatrisActivityBinding binding;
    private RBMatrisAdapter adapter;
    private String bedenId = "";
    private String guid = "";
    private String docId = "";
    private String selectedCode = "";
    private String viewTitle = "";
    private boolean isStockActive;
    private final boolean USE_IMMERSIVE_MODE = true;
    public final boolean DISABLE_IMMERSIVE_MODE_ON_KEYBOARD_OPEN = false; // might be helpful to solve keyboard jumping issue when pop up
    public AndroidBug5497Workaround2 helper;
    private JSONArray jsonArray = new JSONArray();
    RBMatrisResponse rbMatrisResponse ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.rbmatris_activity);
        PreferencesHelper.setJsonArrayForMatris(new JSONArray());
        loadKeyboard();

        if (PreferencesHelper.isIsFastSearchActive(RBMatrisActivity.this)){
            binding.toolbar2.leftContainer.setVisibility(View.VISIBLE);
        }else{
            binding.toolbar2.leftContainer.setVisibility(View.GONE);
        }

        Intent myIntent = getIntent(); // gets the previously created intent
        bedenId = myIntent.getStringExtra("bedenId");
        guid = myIntent.getStringExtra("guid");
        docId = myIntent.getStringExtra("docId");
        viewTitle = myIntent.getStringExtra("viewTitle");
        selectedCode = myIntent.getStringExtra("selectedCode");
        isStockActive = myIntent.getBooleanExtra("isStockActive",false);
        binding.toolbar.txtViewTitle.setText(viewTitle);
        binding.toolbar2.rightContainer.setVisibility(View.INVISIBLE);

        binding.toolbar2.edtSearch.setSelected(true);
        binding.toolbar2.edtSearch.setFocusable(true);
        binding.toolbar2.edtSearch.requestFocus();
        binding.toolbar2.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchData();
            }
        });

        binding.toolbar2.edtSearch.setOnKeyListener(new View.OnKeyListener() {
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

        binding.toolbar2.edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    searchData();
                    return true;
                }
                return false;
            }
        });


        binding.toolbar2.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchData();
            }
        });

        loadData();

      //  getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

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

    /*   binding.toolbar2.txtRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferencesHelper.setIsBackButtonActive(true);
                onBackPressed();
            }
        });*/

        binding.toolbar2.leftContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferencesHelper.setIsBackButtonActive(true);
                onBackPressed();
            }
        });



        binding.toolbar.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferencesHelper.setIsBackButtonActive(true);
                onBackPressed();
            }
        });



        binding.txtTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSelectedIndex = 0;
                binding.txtTab1.setBackground(getResources().getDrawable(R.drawable.circle_blue_back));

                binding.txtTab1.setTextColor(getResources().getColor(R.color.white));
                binding.txtTab2.setTextColor(getResources().getColor(R.color.dropdownColor));
                binding.txtTab3.setTextColor(getResources().getColor(R.color.dropdownColor));

                binding.txtTab2.setBackgroundColor(getResources().getColor(R.color.white));
                binding.txtTab3.setBackgroundColor(getResources().getColor(R.color.white));
                loadTableData(rbMatrisResponse, false);
            }
        });

        binding.txtTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSelectedIndex = 1;
                binding.txtTab2.setBackground(getResources().getDrawable(R.drawable.circle_blue_back));

                binding.txtTab1.setBackgroundColor(getResources().getColor(R.color.white));
                binding.txtTab3.setBackgroundColor(getResources().getColor(R.color.white));

                binding.txtTab1.setTextColor(getResources().getColor(R.color.dropdownColor));
                binding.txtTab2.setTextColor(getResources().getColor(R.color.white));
                binding.txtTab3.setTextColor(getResources().getColor(R.color.dropdownColor));
                loadTableData(rbMatrisResponse,false);
            }
        });

        binding.txtTab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSelectedIndex = 2;
                binding.txtTab3.setBackground(getResources().getDrawable(R.drawable.circle_blue_back));

                binding.txtTab2.setTextColor(getResources().getColor(R.color.dropdownColor));
                binding.txtTab3.setTextColor(getResources().getColor(R.color.white));
                binding.txtTab1.setTextColor(getResources().getColor(R.color.dropdownColor));

                binding.txtTab2.setBackgroundColor(getResources().getColor(R.color.white));
                binding.txtTab1.setBackgroundColor(getResources().getColor(R.color.white));
                loadTableData(rbMatrisResponse,false);
            }
        });
    }

    private void loadKeyboard() {
        AndroidBug5497Workaround2.SoftKeyBoardStatusListener listener = new AndroidBug5497Workaround2.SoftKeyBoardStatusListener() {
            @Override
            public void onKeyBoardShow(View rootView, int totalScreenHeight) {
            }

            @Override
            public void onKeyBoardHide(View rootView, int totalScreenHeight) {

                if (USE_IMMERSIVE_MODE){
                    returnToImmersiveMode();
                }
            }
        };
        helper = AndroidBug5497Workaround2.assistActivity(this, listener);


        View v = findViewById(R.id.toolbar2);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("XPopup","XPopup");
            /*    new XPopup.Builder(RBMatrisActivity.this)
                        .autoOpenSoftInput(true)
                        .asCustom(new CustomEditTextBottomPopup(RBMatrisActivity.this))
                        .show();*/
            }
        });
    }

    private void returnToImmersiveMode(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                immersiveMode();
            }
        }, 300);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (USE_IMMERSIVE_MODE){
            immersiveMode();
        }
    }

    public void disableImmersiveMode() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }


    public void immersiveMode() {
        final View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }


    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public void focusSearch() {
        binding.toolbar2.edtSearch.setSelected(true);
        binding.toolbar2.edtSearch.setFocusable(true);
        binding.toolbar2.edtSearch.requestFocus();
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
  /*      String miktar = "";


        for (int i = 0; i < this.jsonArray.length(); i++) {
            JSONObject row = null;
            try {
                row = this.jsonArray.getJSONObject(i);
                miktar = row.getString("Miktar");

                if (miktar.equals("0")){

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
*/

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
            if (PreferencesHelper.isIsFastSearchActive(RBMatrisActivity.this)){
                focusSearch();
            }
            if (sTHEkleRespone!=null){
               // iF SUCCESS  show alert
                Tool.showInfo(RBMatrisActivity.this,"Bilgi",
                        sTHEkleRespone.getResult_message().getMessage(),
                        (dialog, which) ->  dismissToolDialogFinish(),"Tamam");
             //   Tool.showInfo(this,"Bilgi",sTHEkleRespone.getResult_message().getMessage());
                binding.toolbar2.rightContainer.setVisibility(View.GONE);
                PreferencesHelper.setJsonArrayForMatris(new JSONArray());

            }else{
                Tool.showInfo(this,"Hata","Kayıt İşlemi Başarısız.");
            }
        });
    }

    private void dismissToolDialogFinish() {
        PreferencesHelper.setIsBackButtonActive(true);
        if (!PreferencesHelper.isIsFastSearchActive(RBMatrisActivity.this))
               finish();
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
            if (PreferencesHelper.isIsFastSearchActive(RBMatrisActivity.this)){
                focusSearch();
            }
            if (rbMatrisResponse.getResult()!=null){
                loadTableData(rbMatrisResponse, true);
            }else{
                Tool.hideDialog();
                Tool.showInfo(this, "Bilgi", rbMatrisResponse.getResult_message().getMessage());
            }
        });
    }

    private void loadTableData(RBMatrisResponse rbMatrisResponse , boolean isAutoActive) {
        if (isAutoActive){
            int i = 0;

            for (RBMatrisResponse.ResultBean response : rbMatrisResponse.getResult()){
                if (response.getKod().equals(selectedCode)){
                    currentSelectedIndex = i;
                }
                i=i+1;
            }
        }

        setViews(rbMatrisResponse);
        binding.openDocList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RBMatrisAdapter(this, rbMatrisResponse,currentSelectedIndex,isStockActive);
        binding.openDocList.setAdapter(adapter);
    }


    private void setViews(RBMatrisResponse rbMatrisResponse) {
        if (rbMatrisResponse.getResult()!=null){
            binding.txtTab1.setText(rbMatrisResponse.getResult().get(0).getKod());
            binding.txtTab2.setText(rbMatrisResponse.getResult().get(1).getKod());
            binding.txtTab3.setText(rbMatrisResponse.getResult().get(2).getKod());
            if (currentSelectedIndex == 0 && rbMatrisResponse.getResult().get(0)!=null){

                binding.txtTab1.setText(rbMatrisResponse.getResult().get(currentSelectedIndex).getKod());
                binding.txtProductTitle.setText(rbMatrisResponse.getResult().get(currentSelectedIndex).getAd());
                binding.txtProductTitle.setText(rbMatrisResponse.getResult().get(currentSelectedIndex).getAd());
                binding.txtPrice.setText(rbMatrisResponse.getResult().get(currentSelectedIndex).getFiyat()+" $ ");
                binding.txtTab1.setBackground(getResources().getDrawable(R.drawable.circle_blue_back));
                binding.txtTab2.setBackgroundColor(getResources().getColor(R.color.white));;
                binding.txtTab3.setBackgroundColor(getResources().getColor(R.color.white));

                binding.txtTab1.setTextColor(getResources().getColor(R.color.white));
                binding.txtTab2.setTextColor(getResources().getColor(R.color.dropdownColor));
                binding.txtTab3.setTextColor(getResources().getColor(R.color.dropdownColor));


            }

            if (currentSelectedIndex == 1 && rbMatrisResponse.getResult().get(1)!=null){
                binding.txtTab2.setText(rbMatrisResponse.getResult().get(1).getKod());
                binding.txtProductTitle.setText(rbMatrisResponse.getResult().get(1).getAd());
                binding.txtPrice.setText(rbMatrisResponse.getResult().get(1).getFiyat()+" $ ");
                binding.txtTab2.setBackground(getResources().getDrawable(R.drawable.circle_blue_back));;
                binding.txtTab1.setBackgroundColor(getResources().getColor(R.color.white));
                binding.txtTab3.setBackgroundColor(getResources().getColor(R.color.white));

                binding.txtTab1.setTextColor(getResources().getColor(R.color.dropdownColor));
                binding.txtTab2.setTextColor(getResources().getColor(R.color.white));
                binding.txtTab3.setTextColor(getResources().getColor(R.color.dropdownColor));
            }

            if (currentSelectedIndex == 2 && rbMatrisResponse.getResult().get(2)!=null){
                binding.txtTab3.setText(rbMatrisResponse.getResult().get(2).getKod());
                binding.txtProductTitle.setText(rbMatrisResponse.getResult().get(2).getAd());
                binding.txtPrice.setText(rbMatrisResponse.getResult().get(2).getFiyat()+" $ ");
                binding.txtTab3.setBackground(getResources().getDrawable(R.drawable.circle_blue_back));
                binding.txtTab1.setBackgroundColor(getResources().getColor(R.color.white));
                binding.txtTab2.setBackgroundColor(getResources().getColor(R.color.white));;

                binding.txtTab1.setTextColor(getResources().getColor(R.color.dropdownColor));
                binding.txtTab2.setTextColor(getResources().getColor(R.color.dropdownColor));
                binding.txtTab3.setTextColor(getResources().getColor(R.color.white));

            }
        }
    }

    // search new data

    private void searchData() {
        Tool.openDialog(this);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                .addFormDataPart("src", binding.toolbar2.edtSearch.getText().toString())
                .build();

        Request.productSearch(requestBody, this, response -> {
            ProductSearchResponse productSearchResponse = ( ProductSearchResponse) response.body();
            response.headers();
            hideDialog();
            binding.toolbar2.edtSearch.setText("");
            if (productSearchResponse.getResult()!=null){
                if (productSearchResponse.getResult().size()>0){
                    bedenId = productSearchResponse.getResult().get(0).getBeden_id();
                    selectedCode = productSearchResponse.getResult().get(0).getKod();

                    loadData();
                }else{

                }
            }else{
                Tool.hideDialog();
                Tool.showInfo(this, "Bilgi", productSearchResponse.getResult_message().getMessage());
            }
        });
    }
}
