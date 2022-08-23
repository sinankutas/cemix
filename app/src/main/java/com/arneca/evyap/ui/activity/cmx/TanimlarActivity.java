package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 8/22/22.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.arneca.evyap.R;
import com.arneca.evyap.databinding.OrderActivityBinding;
import com.arneca.evyap.databinding.TanimlarBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.arneca.evyap.ui.adapter.cmx.LocalRBMatrisAdapter;
import com.arneca.evyap.ui.adapter.cmx.StandartListAdapter;

import java.util.ArrayList;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

public class TanimlarActivity extends BaseActivity {
    private TanimlarBinding binding;
    StandartListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.tanimlar);

        binding.toolbar.txtViewTitle.setText("Sayım");
        ArrayList<String> menuNames = new ArrayList<>();
        menuNames.add("Tanımları Çek");
        menuNames.add("Yeni Sayım");
        menuNames.add("Eski Sayım");
        menuNames.add("Sayımı Bitir");
        binding.toolbar.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // set up the RecyclerView
        binding.subList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StandartListAdapter(this, menuNames);
        binding.subList.setAdapter(adapter);
    }

    @Override
    public void hideDialog() {
        super.hideDialog();
    }


}
