package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 8/23/22.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.arneca.evyap.R;
import com.arneca.evyap.api.response.cmx.TanimlarResultModel;
import com.arneca.evyap.databinding.CmxaddProductActivityBinding;
import com.arneca.evyap.databinding.NewSayimBinding;
import com.arneca.evyap.helper.DBHelper;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.arneca.evyap.ui.adapter.cmx.NewSayimAdapter;
import com.arneca.evyap.ui.adapter.cmx.ProductSearchAdapter;
import com.arneca.evyap.ui.fragment.TanimBottomSheetFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

public class NewSayimActivity  extends BaseActivity {

    private NewSayimBinding binding;
    private NewSayimAdapter adapter;
    private String guid = "";
    private String docId = "";
    private String viewTitle;
    private boolean isStockActive;
    private TanimBottomSheetFragment tanimBottomSheetFragment;
    private DBHelper dbHelper ;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.new_sayim);

        Intent myIntent = getIntent(); // gets the previously created intent
        guid = myIntent.getStringExtra("guid");
        docId = myIntent.getStringExtra("docId");
        viewTitle = myIntent.getStringExtra("viewTitle");
        isStockActive = myIntent.getBooleanExtra("isStockActive", false);
        binding.toolbar.txtViewTitle.setText(viewTitle);
        dbHelper =  new DBHelper(this);

        binding.openDocList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewSayimAdapter(this, guid,docId,viewTitle,isStockActive);
        binding.openDocList.setAdapter(adapter);


      /*  binding.tanimSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tanimBottomSheetFragment = new TanimBottomSheetFragment().newInstance();
                tanimBottomSheetFragment.show(getSupportFragmentManager(), "");
            }
        });*/

        binding.toolbar.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

   /*     tanimBottomSheetFragment = new TanimBottomSheetFragment().newInstance();
        tanimBottomSheetFragment.show(getSupportFragmentManager(), "");*/

        binding.edtSearch.setSelected(true);
        binding.edtSearch.setFocusable(true);

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

    }

    private void searchData() {
        int i = dbHelper.numberOfRows();
        if (i==0){

        }
        ArrayList<TanimlarResultModel> tanimlarResultModels = dbHelper.getRecordWithGroupByBeden("");//dbHelper.getRecordWithGroupBy(binding.edtSearch.getText().toString());
        Map<String, List<TanimlarResultModel>> tanimMap = new HashMap<>();
        for(TanimlarResultModel p : tanimlarResultModels){
            if(!tanimMap.containsKey(p.getKod()))
            {
                tanimMap.put(p.getKod(), new ArrayList<>());
            }
            tanimMap.get(p.getKod()).add(p);
        }
        PreferencesHelper.setTanimMap(tanimMap);



     //   binding.edtSearch.setText("");
        adapter.setData(tanimlarResultModels);
    }

    public void gotoRBMatris() {
        Intent intent = new Intent(NewSayimActivity.this, LocalRBMatrisActivity.class);
        intent.putExtra("viewTitle","Yeni Sayım");
        intent.putExtra("bedenId", PreferencesHelper.getTanimlarResultModel().getBeden_kodu());
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
    }
}
