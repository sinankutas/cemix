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
import com.arneca.evyap.api.response.cmx.RBMatrisResponse;
import com.arneca.evyap.api.response.cmx.STHEkleRespone;
import com.arneca.evyap.api.response.cmx.TanimlarResponse;
import com.arneca.evyap.api.response.cmx.TanimlarResultModel;
import com.arneca.evyap.databinding.RbmatrisActivityBinding;
import com.arneca.evyap.helper.AndroidBug5497Workaround2;
import com.arneca.evyap.helper.CustomEditTextBottomPopup;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.arneca.evyap.ui.adapter.cmx.LocalRBMatrisAdapter;
import com.arneca.evyap.ui.adapter.cmx.RBMatrisAdapter;
import com.lxj.xpopup.XPopup;

import org.json.JSONArray;

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

    public AndroidBug5497Workaround2 helper;


    private JSONArray jsonArray = new JSONArray();
    TanimlarResultModel tanimlarResponse  = PreferencesHelper.getTanimlarResultModel();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.rbmatris_activity);

        loadKeyboard();

        Intent myIntent = getIntent(); // gets the previously created intent
        bedenId = myIntent.getStringExtra("bedenId");

        viewTitle = myIntent.getStringExtra("viewTitle");
        isStockActive = myIntent.getBooleanExtra("isStockActive",false);
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

        binding.txtTab1.setBackground(getResources().getDrawable(R.drawable.circle_blue_back));
        loadTableData(tanimlarResponse);
        binding.txtTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSelectedIndex = 0;
                binding.txtTab1.setBackground(getResources().getDrawable(R.drawable.circle_blue_back));

                binding.txtTab2.setBackgroundColor(getResources().getColor(R.color.white));
                binding.txtTab3.setBackgroundColor(getResources().getColor(R.color.white));
                loadTableData(tanimlarResponse);
            }
        });

        binding.txtTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSelectedIndex = 1;
                binding.txtTab2.setBackground(getResources().getDrawable(R.drawable.circle_blue_back));

                binding.txtTab1.setBackgroundColor(getResources().getColor(R.color.white));
                binding.txtTab3.setBackgroundColor(getResources().getColor(R.color.white));
                loadTableData(tanimlarResponse);
            }
        });

        binding.txtTab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSelectedIndex = 2;
                binding.txtTab3.setBackground(getResources().getDrawable(R.drawable.circle_blue_back));

                binding.txtTab2.setBackgroundColor(getResources().getColor(R.color.white));
                binding.txtTab1.setBackgroundColor(getResources().getColor(R.color.white));
                loadTableData(tanimlarResponse);
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

    }



    private void loadTableData(TanimlarResultModel tanimlarResponse) {
        setViews();

    }


    private void setViews() {
        ArrayList<String> keys = new ArrayList<>();
        for ( String key : PreferencesHelper.getTanimMap().keySet() ) {
            System.out.println( key );
            keys.add(key);
        }
          ArrayList<TanimlarResultModel>   rbMatrisResponse = ( ArrayList<TanimlarResultModel>) PreferencesHelper.getTanimMap().get(keys.get(0));
         if (currentSelectedIndex == 0 && keys.get(0)!=null){

             binding.openDocList.setLayoutManager(new LinearLayoutManager(this));
             adapter = new LocalRBMatrisAdapter(this, rbMatrisResponse,currentSelectedIndex,isStockActive); // buraya aktif tab gelecek
             binding.openDocList.setAdapter(adapter);

                binding.txtTab1.setText(keys.get(0));
                binding.txtProductTitle.setText(rbMatrisResponse.get(0).getAd());
                binding.txtProductTitle.setText(rbMatrisResponse.get(0).getAd());
                binding.txtPrice.setText(rbMatrisResponse.get(0).getSatis_fiyat()+" $ ");

            }

        try {
            if (keys.get(1)!=null){
                binding.txtTab2.setText(keys.get(1));
                binding.txtProductTitle.setText(rbMatrisResponse.get(01).getAd());
                binding.txtProductTitle.setText(rbMatrisResponse.get(1).getAd());
                binding.txtPrice.setText(rbMatrisResponse.get(1).getSatis_fiyat()+" $ ");
            }
            }catch (Exception e){

            }

            try {
                if (keys.get(2)!=null){
                    binding.txtTab3.setText(keys.get(2));
                    binding.txtProductTitle.setText(rbMatrisResponse.get(2).getAd());
                    binding.txtProductTitle.setText(rbMatrisResponse.get(2).getAd());
                    binding.txtPrice.setText(rbMatrisResponse.get(2).getSatis_fiyat()+" $ ");
                }
            }catch (Exception e){

            }
         /**/

    }


}

