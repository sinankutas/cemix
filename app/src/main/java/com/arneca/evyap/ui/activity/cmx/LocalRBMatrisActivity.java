package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 8/23/22.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.cmx.NewSayimModel;
import com.arneca.evyap.api.response.cmx.RBMatrisResponse;
import com.arneca.evyap.api.response.cmx.STHEkleRespone;
import com.arneca.evyap.api.response.cmx.TanimlarResponse;
import com.arneca.evyap.api.response.cmx.TanimlarResultModel;
import com.arneca.evyap.databinding.RbmatrisActivityBinding;
import com.arneca.evyap.helper.AndroidBug5497Workaround2;
import com.arneca.evyap.helper.CustomEditTextBottomPopup;
import com.arneca.evyap.helper.DBHelper;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.arneca.evyap.ui.adapter.cmx.LocalRBMatrisAdapter;
import com.arneca.evyap.ui.adapter.cmx.RBMatrisAdapter;
import com.lxj.xpopup.XPopup;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class LocalRBMatrisActivity  extends BaseActivity {

    private int currentSelectedIndex = 0;
    public RbmatrisActivityBinding binding;
    private LocalRBMatrisAdapter adapter;
    private String bedenId = "";
    private String viewTitle = "";
    private boolean isStockActive;
    private final boolean USE_IMMERSIVE_MODE = true;
    public final boolean DISABLE_IMMERSIVE_MODE_ON_KEYBOARD_OPEN = false; // might be helpful to solve keyboard jumping issue when pop up
    private DBHelper dbHelper ;

    public AndroidBug5497Workaround2 helper;
    ArrayList<TanimlarResultModel> tabsResponse = new ArrayList<>();
    ArrayList<TanimlarResultModel> colorDetails = new ArrayList<>();
    private JSONArray jsonArray = new JSONArray();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.rbmatris_activity);
        binding.toolbar.txtViewTitle.setText(viewTitle);
        dbHelper =  new DBHelper(this);
        PreferencesHelper.setJsonArrayForLocalMatris(new JSONArray());
        loadKeyboard();

        Intent myIntent = getIntent(); // gets the previously created intent
        bedenId = myIntent.getStringExtra("bedenId");

        viewTitle = myIntent.getStringExtra("viewTitle");
        isStockActive = myIntent.getBooleanExtra("isStockActive",false);

        tabsResponse = dbHelper.getRecordWithGroupByBeden(bedenId);
        if (tabsResponse.size()>0)
           colorDetails = dbHelper.getRecordWithGroupRBMatris(bedenId,tabsResponse.get(0).getKod());


        binding.toolbar.txtViewTitle.setText(viewTitle);
        binding.toolbar2.rightContainer.setVisibility(View.INVISIBLE);
        binding.toolbar2.leftContainer.setVisibility(View.INVISIBLE);
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

        if (tabsResponse.size()>0)
            setTabLayouts();
        binding.txtTab1.setBackground(getResources().getDrawable(R.drawable.circle_blue_back));
        loadTableData();
        binding.txtTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSelectedIndex = 0;
                binding.txtTab1.setBackground(getResources().getDrawable(R.drawable.circle_blue_back));

                binding.txtTab2.setBackgroundColor(getResources().getColor(R.color.white));
                binding.txtTab3.setBackgroundColor(getResources().getColor(R.color.white));

                binding.txtTab1.setTextColor(getResources().getColor(R.color.white));
                binding.txtTab2.setTextColor(getResources().getColor(R.color.dropdownColor));
                binding.txtTab3.setTextColor(getResources().getColor(R.color.dropdownColor));
                colorDetails = dbHelper.getRecordWithGroupRBMatris(bedenId,tabsResponse.get(0).getKod());
                loadTableData();
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
                colorDetails = dbHelper.getRecordWithGroupRBMatris(bedenId,tabsResponse.get(1).getKod());
                loadTableData();
            }
        });

        binding.txtTab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSelectedIndex = 2;
                binding.txtTab3.setBackground(getResources().getDrawable(R.drawable.circle_blue_back));

                binding.txtTab2.setBackgroundColor(getResources().getColor(R.color.white));
                binding.txtTab1.setBackgroundColor(getResources().getColor(R.color.white));

                binding.txtTab2.setTextColor(getResources().getColor(R.color.dropdownColor));
                binding.txtTab3.setTextColor(getResources().getColor(R.color.white));
                binding.txtTab1.setTextColor(getResources().getColor(R.color.dropdownColor));
                colorDetails = dbHelper.getRecordWithGroupRBMatris(bedenId,tabsResponse.get(2).getKod());
                loadTableData();
            }
        });
    }

    private void setTabLayouts() {

        binding.openDocList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LocalRBMatrisAdapter(this, colorDetails,currentSelectedIndex,isStockActive,tabsResponse.get(currentSelectedIndex).getSatis_fiyat(),tabsResponse.get(currentSelectedIndex).getAd()); // buraya aktif tab gelecek
        binding.openDocList.setAdapter(adapter);

        binding.txtTab1.setText(tabsResponse.get(0).getKod());
        binding.txtProductTitle.setText(tabsResponse.get(0).getAd());
        binding.txtProductTitle.setText(tabsResponse.get(0).getAd());
        binding.txtPrice.setText(tabsResponse.get(0).getSatis_fiyat()+" $ ");

        if (tabsResponse.size() == 1){
            binding.txtTab1.setVisibility(View.VISIBLE);
            binding.txtTab2.setVisibility(View.INVISIBLE);
            binding.txtTab3.setVisibility(View.INVISIBLE);
        }else if (tabsResponse.size() == 2){
            binding.txtTab2.setText(tabsResponse.get(1).getKod());
            binding.txtTab1.setVisibility(View.VISIBLE);
            binding.txtTab2.setVisibility(View.VISIBLE);
            binding.txtTab3.setVisibility(View.INVISIBLE);
        }else if (tabsResponse.size() == 3){
            binding.txtTab3.setText(tabsResponse.get(2).getKod());
            binding.txtTab2.setText(tabsResponse.get(1).getKod());
            binding.txtTab1.setVisibility(View.VISIBLE);
            binding.txtTab2.setVisibility(View.VISIBLE);
            binding.txtTab3.setVisibility(View.VISIBLE);
        }else if (tabsResponse.size()>3){
            binding.txtTab3.setText(tabsResponse.get(2).getKod());
            binding.txtTab2.setText(tabsResponse.get(1).getKod());
            binding.txtTab1.setText(tabsResponse.get(0).getKod());
            binding.txtTab1.setVisibility(View.VISIBLE);
            binding.txtTab2.setVisibility(View.VISIBLE);
            binding.txtTab3.setVisibility(View.VISIBLE);
        }
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
                new XPopup.Builder(LocalRBMatrisActivity.this)
                        .autoOpenSoftInput(true)
                        .asCustom(new CustomEditTextBottomPopup(LocalRBMatrisActivity.this))
                        .show();
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

    public void setJsonArray(JSONArray jsonArray){
        this.jsonArray = jsonArray;
        if (jsonArray.length()>0){
            binding.toolbar2.rightContainer.setVisibility(View.VISIBLE);
        }else{
            binding.toolbar2.rightContainer.setVisibility(View.INVISIBLE);
        }
    }

    private void saveDoc() {
        ArrayList<NewSayimModel> sayimModels = dbHelper.getAllNewSayim();
        for(int i=0; i<this.jsonArray.length(); i++){
            String value = null;
            try {
                 String stok_kodu = jsonArray.getJSONObject(i).getString("StokKodu");
                 String stok_idx = jsonArray.getJSONObject(i).getString("StokIdx");
                 String renk_id = jsonArray.getJSONObject(i).getString("RenkId");
                 String miktar = jsonArray.getJSONObject(i).getString("Miktar");
                 String fiyat = jsonArray.getJSONObject(i).getString("Fiyat");
                 String dvz = jsonArray.getJSONObject(i).getString("Dvz");
                 String stokAd = jsonArray.getJSONObject(i).getString("StokAdı");
                 String renk = jsonArray.getJSONObject(i).getString("Renk");
                dbHelper.insertNewSayimDetay(""+sayimModels.get(sayimModels.size()-1).getId(),stok_kodu,renk,"arama metni"
                        ,""+PreferencesHelper.getLoginResponse().getResult().getProfil().getSubeID(),""+PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx(),miktar,"cihaz",stokAd);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Tool.showInfo(LocalRBMatrisActivity.this,"Bilgi",
               "İşlem Başarılı",
                (dialog, which) ->  dismissToolDialogFinish(),"Tamam");
    }

    private void dismissToolDialogFinish() {
        finish();
    }



    private void loadTableData() {
        setViews();
    }


    private void setViews() {

        try {
            if (currentSelectedIndex == 0 && tabsResponse.get(0)!=null){

                binding.openDocList.setLayoutManager(new LinearLayoutManager(this));
                adapter = new LocalRBMatrisAdapter(this, colorDetails,currentSelectedIndex,isStockActive,tabsResponse.get(0).getSatis_fiyat(),tabsResponse.get(0).getAd()); // buraya aktif tab gelecek
                binding.openDocList.setAdapter(adapter);

                binding.txtTab1.setText(tabsResponse.get(0).getKod());
                binding.txtProductTitle.setText(tabsResponse.get(0).getAd());
                binding.txtProductTitle.setText(tabsResponse.get(0).getAd());
                binding.txtPrice.setText(tabsResponse.get(0).getSatis_fiyat()+" $ ");

            }
        }catch (Exception e){

    }

        try {
            if (currentSelectedIndex == 1 && tabsResponse.get(1)!=null){
                binding.openDocList.setLayoutManager(new LinearLayoutManager(this));
                adapter = new LocalRBMatrisAdapter(this, colorDetails,currentSelectedIndex,isStockActive,tabsResponse.get(1).getSatis_fiyat(),tabsResponse.get(1).getAd()); // buraya aktif tab gelecek
                binding.openDocList.setAdapter(adapter);

                binding.txtTab2.setText(tabsResponse.get(1).getKod());
                binding.txtProductTitle.setText(tabsResponse.get(1).getAd());
                binding.txtProductTitle.setText(tabsResponse.get(1).getAd());
                binding.txtPrice.setText(tabsResponse.get(1).getSatis_fiyat()+" $ ");
            }
            }catch (Exception e){

            }

            try {
                if (currentSelectedIndex == 2 && tabsResponse.get(2)!=null){
                    binding.openDocList.setLayoutManager(new LinearLayoutManager(this));
                    adapter = new LocalRBMatrisAdapter(this, colorDetails,currentSelectedIndex,isStockActive,tabsResponse.get(2).getSatis_fiyat(),tabsResponse.get(2).getAd()); // buraya aktif tab gelecek
                    binding.openDocList.setAdapter(adapter);
                    
                    binding.txtTab3.setText(tabsResponse.get(2).getKod());
                    binding.txtProductTitle.setText(tabsResponse.get(2).getAd());
                    binding.txtProductTitle.setText(tabsResponse.get(2).getAd());
                    binding.txtPrice.setText(tabsResponse.get(2).getSatis_fiyat()+" $ ");
                }
            }catch (Exception e){

            }
         /**/

    }


}

