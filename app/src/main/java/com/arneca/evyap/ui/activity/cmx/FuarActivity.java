package com.arneca.evyap.ui.activity.cmx;/*
 * Created by sinan KUTAS on 11/17/22.
 */

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.arneca.evyap.R;
import com.arneca.evyap.databinding.OrderActivityBinding;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.arneca.evyap.ui.adapter.cmx.StandartListAdapter;

import java.util.ArrayList;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

public class FuarActivity  extends BaseActivity {
    private OrderActivityBinding orderActivityBinding;
    StandartListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        orderActivityBinding = DataBindingUtil.setContentView(this, R.layout.order_activity);
        orderActivityBinding.toolbar.txtViewTitle.setText("Fuar");
        ArrayList<String> menuNames = new ArrayList<>();

        menuNames.add("Fuar Siparişine Devam");
        menuNames.add("Yeni Fuar Siparişi");

        orderActivityBinding.toolbar.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // set up the RecyclerView
        orderActivityBinding.subList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StandartListAdapter(this, menuNames);
        orderActivityBinding.subList.setAdapter(adapter);
    }
}
