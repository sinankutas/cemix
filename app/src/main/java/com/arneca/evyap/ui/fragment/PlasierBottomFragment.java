package com.arneca.evyap.ui.fragment;/*
 * Created by  Sinan KUTASon 8/26/22.
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
import com.arneca.evyap.api.response.cmx.TanimlarResponse;
import com.arneca.evyap.api.response.cmx.TanimlarResultModel;
import com.arneca.evyap.databinding.PlasierBottomBinding;
import com.arneca.evyap.databinding.TanimBottomSheetBinding;
import com.arneca.evyap.helper.DBHelper;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.cmx.OpenDocStockListActivity;
import com.arneca.evyap.ui.adapter.cmx.TanimlarBottomSheetAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

public class PlasierBottomFragment extends BottomSheetDialogFragment {
    private PlasierBottomBinding mBinding;

    private String selectedPlasier;

    public static PlasierBottomFragment newInstance() {
        PlasierBottomFragment fragment = new PlasierBottomFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.plasier_bottom, container, false);
        setViews();
        return mBinding.getRoot();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public void dissmisView(){
        dismiss();
            ((OpenDocStockListActivity) getActivity()).gotoCompletedDoc(selectedPlasier,mBinding.nameEdt.getText().toString(),
                    mBinding.countryEdt.getText().toString(),mBinding.cargoEdt.getText().toString(),mBinding.phoneEdt.getText().toString());

    }

    private void setViews() {

        mBinding.getRoot().post(() -> {
            Display mDisplay = getActivity().getWindowManager().getDefaultDisplay();
            final int width = mDisplay.getWidth();
            final int height = mDisplay.getHeight() -50;/// 2;

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
            mBinding.getRoot().setLayoutParams(layoutParams);
        });


        mBinding.topLL.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark2));

        mBinding.topLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        mBinding.txtPlasier1.setText(PreferencesHelper.getLoginResponse().getResult().getPlasiyerKodlari().get(0).getPlasiyer_kodu_isyeri());
        mBinding.txtPlasier2.setText(PreferencesHelper.getLoginResponse().getResult().getPlasiyerKodlari().get(1).getPlasiyer_kodu_isyeri());
        selectedPlasier = PreferencesHelper.getLoginResponse().getResult().getPlasiyerKodlari().get(0).getPlasiyer_kodu_isyeri();
        mBinding.btnPlasier1.setBackgroundResource(R.drawable.checkedbox);
        mBinding.btnPlasier1.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));

        if (PreferencesHelper.getSelectedCompany().getKod().equals("120.99.01")){
            mBinding.lytExtra.setVisibility(View.VISIBLE);
        }
        mBinding.lytPlsyr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.btnPlasier1.setBackgroundResource(R.drawable.checkedbox);
                mBinding.btnPlasier1.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));

                mBinding.btnPlasier2.setBackgroundResource(R.drawable.uncheckedbox);
                mBinding.btnPlasier2.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));
                selectedPlasier = PreferencesHelper.getLoginResponse().getResult().getPlasiyerKodlari().get(0).getPlasiyer_kodu_isyeri();
            }
        });

        mBinding.lytPlsyr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.btnPlasier2.setBackgroundResource(R.drawable.checkedbox);
                mBinding.btnPlasier2.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));

                mBinding.btnPlasier1.setBackgroundResource(R.drawable.uncheckedbox);
                mBinding.btnPlasier1.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));
                selectedPlasier = PreferencesHelper.getLoginResponse().getResult().getPlasiyerKodlari().get(1).getPlasiyer_kodu_isyeri();
            }
        });

        mBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PreferencesHelper.getSelectedCompany().getKod().equals("120.99.01")){
                    String name = mBinding.nameEdt.getText().toString();
                    String phone = mBinding.phoneEdt.getText().toString();
                    String country = mBinding.countryEdt.getText().toString();

                    if (name == null)
                        name = "";
                    if (phone == null)
                        phone = "";
                    if (country == null)
                        country = "";

                    if (!name.equals("") && !phone.equals("") && !country.equals("")){
                        dissmisView();
                    }else{
                        Tool.showInfo(getContext(),"Hata","İsim, Telefon ve Ülke boş geçilemez");
                    }
                }else{
                    dissmisView();
                }


            }
        });

        mBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }



    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }

}