package com.arneca.evyap.ui.fragment;/*
 * Created by Sinan KUTAS on 8/23/22.
 */

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.arneca.evyap.R;
import com.arneca.evyap.api.response.cmx.LoginResponse;
import com.arneca.evyap.api.response.cmx.TanimlarResponse;
import com.arneca.evyap.api.response.cmx.TanimlarResultModel;
import com.arneca.evyap.databinding.CmxCompanyBottomfragmentBinding;
import com.arneca.evyap.databinding.TanimBottomSheetBinding;
import com.arneca.evyap.helper.DBHelper;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.ui.activity.cmx.NewSayimActivity;
import com.arneca.evyap.ui.activity.cmx.OpenDocRecordsActivity;
import com.arneca.evyap.ui.activity.cmx.OpenDocStockListActivity;
import com.arneca.evyap.ui.activity.cmx.TanimlarActivity;
import com.arneca.evyap.ui.adapter.cmx.CompanyBottomSheetAdapter;
import com.arneca.evyap.ui.adapter.cmx.TanimlarBottomSheetAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

public class TanimBottomSheetFragment extends BottomSheetDialogFragment {
    private TanimBottomSheetBinding mBinding;
    private TanimlarBottomSheetAdapter adapter;
    private boolean isRecordActivity = false;
    private DBHelper dbHelper ;
    //  public List<LoginResponse.ResultBean.CarilerBean> carilerBeans = new ArrayList<>();
    ArrayList<TanimlarResultModel> tanimlar = new ArrayList<>();
    Map<String,List<TanimlarResultModel>> tanimMap = new HashMap<>();

    public static TanimBottomSheetFragment newInstance() {
        TanimBottomSheetFragment fragment = new TanimBottomSheetFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.tanim_bottom_sheet, container, false);
        setViews();
        return mBinding.getRoot();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public void dissmisView(){
        dismiss();
     //    ((NewSayimActivity) getActivity()).gotoRBMatris();

    }

    private void setViews() {

        mBinding.getRoot().post(() -> {
            Display mDisplay = getActivity().getWindowManager().getDefaultDisplay();
            final int width = mDisplay.getWidth();
            final int height = mDisplay.getHeight() -50;/// 2;

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
            mBinding.getRoot().setLayoutParams(layoutParams);
        });

        dbHelper =  new DBHelper(getContext());

        adapter= new TanimlarBottomSheetAdapter(getContext(), tanimlar,this,tanimMap);
        // mAdapter = new PlanAdapter(result -> mListener.onClicked(result));
        mBinding.recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        mBinding.recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        mBinding.recycler.setAdapter(adapter);
        mBinding.topLL.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark2));

        mBinding.topLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        mBinding.edtSearchTanim.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()){
                }
            }

            public void afterTextChanged(Editable s) {
                if (s.length()==0){
               //     adapter.setData(carilerBeansOrj); clear data
                }
                else{
                    ArrayList<TanimlarResultModel> tanimlarResultModels = dbHelper.getRecord(s.toString());
                    Map<String,List<TanimlarResultModel>> tanimMap = new HashMap<>();
                    for(TanimlarResultModel p : tanimlarResultModels){
                        if(!tanimMap.containsKey(p.getKod()))
                        {
                            tanimMap.put(p.getKod(), new ArrayList<>());
                        }
                        tanimMap.get(p.getKod()).add(p);
                    }
                    PreferencesHelper.setTanimMap(tanimMap);
                    adapter.setData(tanimlarResultModels);
                }
            }

        });
    }


    public TanimlarResponse.ResultBean filterTanim(String searchValue){
        TanimlarResponse.ResultBean tanimlarResponse = new TanimlarResponse.ResultBean();
     /*   List<LoginResponse.ResultBean.CarilerBean>  newCariler = new ArrayList<>();
        List<LoginResponse.ResultBean.CarilerBean>  carilerBeans = new ArrayList<>();

        for (TanimlarResponse tanimlarResponse : tanimlar){
            carilerBeans.add(tanimlarResponse);
        }
        for(LoginResponse.ResultBean.CarilerBean cari : carilerBeans){
            boolean found =  cari.getAd().toLowerCase().toString().contains(searchValue.toLowerCase().toString());//clearedTurkishCharacter(cari.getAd().replaceAll("I","i").replaceAll("İ","i")).toLowerCase().toString().matches("(?i).*" + clearedTurkishCharacter(searchValue.replaceAll("I","i").replaceAll("İ","i")).toLowerCase().toString()+ ".*");
            if(found){
                newCariler.add(cari);
            }
        }*/
        return tanimlarResponse;

    }
    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }

}