package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 8/15/22.
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.arneca.evyap.R;
import com.arneca.evyap.databinding.HomeActivityBinding;
import com.arneca.evyap.databinding.LoginBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.arneca.evyap.ui.adapter.cmx.MenuGridAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeActivity extends BaseActivity implements MenuGridAdapter.ItemClickListener {
    private HomeActivityBinding homeActivityBinding;
    MenuGridAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        homeActivityBinding = DataBindingUtil.setContentView(this, R.layout.home_activity);
        // data to populate the RecyclerView with

        // set up the RecyclerView
       // RecyclerView recyclerView = findViewById(R.id.menuGrid);
        int numberOfColumns = 2;
        homeActivityBinding.menuGrid.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new MenuGridAdapter(this, PreferencesHelper.getLoginResponse().getResult().getModulYetkileri());
        adapter.setClickListener(this);
        homeActivityBinding.menuGrid.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {

        if (PreferencesHelper.getLoginResponse().getResult().getModulYetkileri().get(position).getTip().equals("Satis")){
            // goto siparis 0
            Intent intent = new Intent(this, OrderActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            startActivity(intent);

        }else if ((PreferencesHelper.getLoginResponse().getResult().getModulYetkileri().get(position).getTip().equals("Siparis"))){

        }else if ((PreferencesHelper.getLoginResponse().getResult().getModulYetkileri().get(position).getTip().equals("Teklif"))){

        }else if ((PreferencesHelper.getLoginResponse().getResult().getModulYetkileri().get(position).getTip().equals("Sayim"))){

        }else if ((PreferencesHelper.getLoginResponse().getResult().getModulYetkileri().get(position).getTip().equals("Stokgor"))){
            Intent intent = new Intent(HomeActivity.this, AddProductActivity.class);
            intent.putExtra("guid","guid");
            intent.putExtra("docId","docId");
            intent.putExtra("viewTitle",PreferencesHelper.getLoginResponse().getResult().getModulYetkileri().get(position).getModul_adi());
            intent.putExtra("isStockActive",true);
            intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            startActivity(intent);
        }



        Log.i("TAG", "You clicked number " + adapter.getItem(position) + ", which is at cell position " + position);
    }
}
