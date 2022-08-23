package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 8/23/22.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.arneca.evyap.R;
import com.arneca.evyap.databinding.CmxaddProductActivityBinding;
import com.arneca.evyap.databinding.NewSayimBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.arneca.evyap.ui.adapter.cmx.ProductSearchAdapter;
import com.arneca.evyap.ui.fragment.TanimBottomSheetFragment;

import androidx.databinding.DataBindingUtil;

public class NewSayimActivity  extends BaseActivity {

    private NewSayimBinding binding;
    private ProductSearchAdapter adapter;
    private String guid = "";
    private String docId = "";
    private String viewTitle;
    private boolean isStockActive;
    private TanimBottomSheetFragment tanimBottomSheetFragment;




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

        binding.tanimSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tanimBottomSheetFragment = new TanimBottomSheetFragment().newInstance();
                tanimBottomSheetFragment.show(getSupportFragmentManager(), "");
            }
        });

        binding.toolbar.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tanimBottomSheetFragment = new TanimBottomSheetFragment().newInstance();
        tanimBottomSheetFragment.show(getSupportFragmentManager(), "");

    }

    public void gotoRBMatris() {
        Intent intent = new Intent(NewSayimActivity.this, LocalRBMatrisActivity.class);
        intent.putExtra("viewTitle","Yeni Sayım");
        intent.putExtra("bedenId", PreferencesHelper.getTanimlarResultModel().getBeden_kodu());
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
    }
}
