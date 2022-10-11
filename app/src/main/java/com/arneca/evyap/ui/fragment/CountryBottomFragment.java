package com.arneca.evyap.ui.fragment;/*
 * Created by Sinan KUTAS  on 10/10/22.
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
import com.arneca.evyap.databinding.CountryBottomBinding;
import com.arneca.evyap.databinding.PlasierBottomBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.cmx.OpenDocStockListActivity;
import com.arneca.evyap.ui.adapter.cmx.CompanyBottomSheetAdapter;
import com.arneca.evyap.ui.adapter.cmx.CountryBottomSheetAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

public class CountryBottomFragment  extends BottomSheetDialogFragment {
    private CountryBottomBinding mBinding;
    private CountryBottomSheetAdapter adapter;
    private String selectedPlasier;
    private PlasierBottomFragment plasierBottomFragment;
    ArrayList<LoginResponse.ResultBean.UlkelerBean> ulkelerBeanOrj = new ArrayList<>();;
    ArrayList<LoginResponse.ResultBean.UlkelerBean> ulkelerNewSheet = new ArrayList<>();


    public static CountryBottomFragment newInstance(PlasierBottomFragment plasierBottomFragment) {
        CountryBottomFragment fragment = new CountryBottomFragment();
        fragment.plasierBottomFragment = plasierBottomFragment;
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.country_bottom, container, false);
        for (LoginResponse.ResultBean.UlkelerBean ulkelerBean : PreferencesHelper.getLoginResponse().getResult().getUlkeler()){
            ulkelerNewSheet.add(ulkelerBean);
        }
        setViews();
        return mBinding.getRoot();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    public void dissmisView(String selectedCountry){
        dismiss();
        plasierBottomFragment.selectedCountryName(selectedCountry);
      //  ((OpenDocStockListActivity) getActivity()).gotoCompletedDoc(selectedPlasier,mBinding.nameEdt.getText().toString(),
       //         mBinding.countryEdt.getText().toString(),mBinding.cargoEdt.getText().toString(),mBinding.phoneEdt.getText().toString());

    }

    private void setViews() {

        mBinding.getRoot().post(() -> {
            Display mDisplay = getActivity().getWindowManager().getDefaultDisplay();
            final int width = mDisplay.getWidth();
            final int height = mDisplay.getHeight() -50;/// 2;

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
            mBinding.getRoot().setLayoutParams(layoutParams);
        });

        for (LoginResponse.ResultBean.UlkelerBean ulkelerBean : PreferencesHelper.getLoginResponse().getResult().getUlkeler()){
            ulkelerBeanOrj.add(ulkelerBean);
        }

        mBinding.topLL.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark2));

        mBinding.topLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        mBinding.edtSearchCountry.addTextChangedListener(new TextWatcher() {
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
                    adapter.setData(ulkelerBeanOrj);
                }
                else{
                    //    carilerBeans = filterCity(PreferencesHelper.getLoginResponse().getResult().getCariler(),s.toString().toLowerCase());
                    adapter.setData(filterCity(s.toString().toLowerCase()));
                }
            }

        });

        adapter= new CountryBottomSheetAdapter(getContext(),PreferencesHelper.getLoginResponse().getResult().getUlkeler(),this);
        // mAdapter = new PlanAdapter(result -> mListener.onClicked(result));
        mBinding.listCountry.setLayoutManager(new LinearLayoutManager(requireContext()));
        //       mBinding.listCountry.setLayoutManager(new LinearLayoutManager(requireContext()));
        mBinding.listCountry.setAdapter(adapter);

    }

    private void openCountryList() {
    }


    public List<LoginResponse.ResultBean.UlkelerBean> filterCity(String searchValue){
        List<LoginResponse.ResultBean.UlkelerBean>  newUlkeler = new ArrayList<>();
        List<LoginResponse.ResultBean.UlkelerBean>  ulkelerBeans = new ArrayList<>();

        for (LoginResponse.ResultBean.UlkelerBean ulkelerBean : ulkelerBeanOrj){
            ulkelerBeans.add(ulkelerBean);
        }
        for(LoginResponse.ResultBean.UlkelerBean cari : ulkelerBeans){
            boolean found =  cari.getUlke().toLowerCase().toString().contains(searchValue.toLowerCase().toString());//clearedTurkishCharacter(cari.getAd().replaceAll("I","i").replaceAll("İ","i")).toLowerCase().toString().matches("(?i).*" + clearedTurkishCharacter(searchValue.replaceAll("I","i").replaceAll("İ","i")).toLowerCase().toString()+ ".*");
            if(found){
                newUlkeler.add(cari);
            }
        }
        return newUlkeler;

    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }

}