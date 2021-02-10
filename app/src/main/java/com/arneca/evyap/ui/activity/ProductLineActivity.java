package com.arneca.evyap.ui.activity;/*
 * Created by Sinan KUTAS on 25.01.2021.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.arneca.evyap.IOnClickListener;
import com.arneca.evyap.R;
import com.arneca.evyap.api.DataModel;
import com.arneca.evyap.api.ReportMap;
import com.arneca.evyap.api.ReportModel;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.GetAllLineInfo;
import com.arneca.evyap.api.response.GetLines;
import com.arneca.evyap.databinding.ProductLineBinding;
import com.arneca.evyap.databinding.ProductLineBindingImpl;
import com.arneca.evyap.helper.LinearLayoutManagerWithSmoothScroller;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.ReportEnum;
import com.arneca.evyap.helper.ReportIndex;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.adapter.RecAdapter;
import com.arneca.evyap.ui.adapter.RecAdapterFactory;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


public class ProductLineActivity extends  BaseActivity{

    private ProductLineBinding binding;
    private GetLines lines;
    private GetAllLineInfo lineInfo;
    private boolean isFactoryListExpanded, isNormalReportActive;
    RecAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<ReportModel> reportModelList = new ArrayList<>();
    ReportMap reportMap = new ReportMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        isNormalReportActive = true;

    }


    @Override
    protected void onResume() {
        super.onResume();
        setViewProperties();
        loadFactories();
        getAllLineInfo();
    }

    public void getAllLineInfo(){
        binding.swipeContainer.setRefreshing(false);
        recyclerView.setVisibility(View.GONE);
        lineInfo = new GetAllLineInfo();
        reportModelList = new ArrayList<>();
        Tool.openDialog(this);
        HashMap<String, Object> map = new HashMap<>();
        map.put("FactoryName", PreferencesHelper.getSelectedFactory().getFactoryName());
        Request.GetAllLineInfo( headersMap(true),map, this, response -> {
            Tool.hideDialog();
            lineInfo = (GetAllLineInfo) response.body();
            if (lineInfo!=null){
                reportModelList =  ReportIndex.getInstance().configureReportModel(lineInfo.getData().getMyArrayList());

                if (isNormalReportActive)
                    loadNormalReport();
                else
                    loadDetilReportView();

            }else{
                Tool.showInfo(this, getString(R.string.error), getString(R.string.available_token_not_found));
            }
        });
    }



    private void loadFactories() {
        binding.swipeContainer.setRefreshing(false);
        RecAdapterFactory adapter = new RecAdapterFactory(PreferencesHelper.getGetFactories(), this);

        RecyclerView recyclerView = findViewById(R.id.gridViewFactory);

        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

    }


    private void setViewProperties() {
        binding = DataBindingUtil.setContentView(this, R.layout.product_line);
       binding.toolbar.back.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.recview);
        binding.factoryTitle.setText(PreferencesHelper.getSelectedFactory().getFactoryName());
        binding.factoryTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFactoryListExpanded){
                    isFactoryListExpanded = true;

                }else{
                    isFactoryListExpanded = false;
                }
                showFactoryList(isFactoryListExpanded);
            }
        });


     binding.toolbar.changeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNormalReportActive){
                    isNormalReportActive = true;
                    loadNormalReport();
                }else{
                    isNormalReportActive = false;
                    loadDetilReportView();

                }
            }
        });

       binding.toolbar.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        binding.toolbar.settins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoPreferences();
            }
        });

        binding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadFactories();
                getAllLineInfo();
            }});
    }

    private void gotoPreferences() {
        Intent intent = new Intent(this, PreferencesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Tool.showInfo(this,this.getString(R.string.info),this.getString(R.string.closeapp), (dialog, which) -> ((BaseActivity) this).finish(),null);
    }

    private void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
        finish();
    }

    private void loadDetilReportView(){

        ArrayList<ReportModel> reportModelListtmp = new ArrayList<>();
        for (ReportModel reportModel : reportModelList){
            if (reportModel.isPrefSelected()){
                if (reportModel.getModels().size()>0)
                    reportModel.setExpanded(false);
                     reportModelListtmp.add(reportModel);
            }
        }
        reportMap.setReportModels(reportModelListtmp);
        adapter = new RecAdapter(this, reportMap, false);
        recyclerView.setVisibility(View.VISIBLE);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        recyclerView.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void loadNormalReport() {
       //lineInfo.setExpanded(false);

        for (GetAllLineInfo.DataBean.MyArrayListBean myArrayListBean: lineInfo.getData().getMyArrayList() ){
            myArrayListBean.getMap().setExpanded(false);
        }
        adapter = new RecAdapter(this, lineInfo, true);
        recyclerView.setVisibility(View.VISIBLE);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);


    }


    public void scrollToPosition(int pos){
        recyclerView.smoothScrollToPosition((pos++));
    }

    public void showFactoryList(boolean isShow){
        if (isShow){
            isFactoryListExpanded = true;
            binding.subItem.setVisibility(View.VISIBLE);
            binding.factoryTitle.setText("Fabrika Seç");
        }else{
            isFactoryListExpanded = false;
            binding.subItem.setVisibility(View.GONE);
            binding.factoryTitle.setText(PreferencesHelper.getSelectedFactory().getFactoryName());
        }
    }


    /*
    *         arrayList.add(new DataModel(ReportEnum.preStrackAmount.toString(), ""+lineInfo.getData().get(position).getPreviousShiftScrapAmount(),false));
            arrayList.add(new DataModel(ReportEnum.currStrackAmount.toString(), ""+lineInfo.getData().get(position).getCurrentShiftScrapAmount(),false));
            arrayList.add(new DataModel(ReportEnum.prePorduct.toString(),  ""+lineInfo.getData().get(position).getPreviousShiftTotalProduction(),false));
            arrayList.add(new DataModel(ReportEnum.currPorduct.toString(), ""+lineInfo.getData().get(position).getCurrentShiftTotalProduction(),false));
            arrayList.add(new DataModel(ReportEnum.vardiyaOEE.toString(),"% "+lineInfo.getData().get(position).getCurrentShiftOEEStr(),false));
            arrayList.add(new DataModel(ReportEnum.prePorduct.toString(), " "+lineInfo.getData().get(position).getProductName(),false));
            arrayList.add(new DataModel(ReportEnum.currentStop.toString(),""+lineInfo.getData().get(position).getCurrentStopReason(),isRedColorActive));
            arrayList.add(new DataModel(ReportEnum.totalStop.toString(), " "+lineInfo.getData().get(position).getCurrentStopDurationStr(),isRedColorActive));

    * */


}
