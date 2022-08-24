package com.arneca.evyap.ui.fragment;/*
 * Created by Sinan KUTAS on 8/15/22.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.arneca.evyap.R;
import com.arneca.evyap.api.response.cmx.LoginResponse;
import com.arneca.evyap.databinding.CmxCompanyBottomfragmentBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.ui.activity.cmx.OpenDocRecordsActivity;
import com.arneca.evyap.ui.activity.cmx.OpenDocStockListActivity;
import com.arneca.evyap.ui.adapter.cmx.CompanyBottomSheetAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

public class CompanyBottomFragment extends BottomSheetDialogFragment {
    private CmxCompanyBottomfragmentBinding mBinding;
    private CompanyBottomSheetAdapter adapter;
    private boolean isRecordActivity = false;
  //  public List<LoginResponse.ResultBean.CarilerBean> carilerBeans = new ArrayList<>();
    ArrayList<LoginResponse.ResultBean.CarilerBean> carilerBeansOrj = new ArrayList<>();

    public static CompanyBottomFragment newInstance(boolean isRecordActivity) {
        CompanyBottomFragment fragment = new CompanyBottomFragment();
            fragment.isRecordActivity = isRecordActivity;
    //    fragment.carilerBeans = PreferencesHelper.getLoginResponse().getResult().getCariler();
        return fragment;
    }


    private void setupFullHeight(BottomSheetDialog bottomSheetDialog) {
        FrameLayout bottomSheet = (FrameLayout) bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();

        int windowHeight = getWindowHeight();
        if (layoutParams != null) {
            layoutParams.height = windowHeight;
        }
        bottomSheet.setLayoutParams(layoutParams);
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private int getWindowHeight() {
        // Calculate window height for fullscreen use
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.cmx_company_bottomfragment, container, false);
        setViews();
        return mBinding.getRoot();
    }


    @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override public void onShow(DialogInterface dialogInterface) {
                BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
                setupFullHeight(bottomSheetDialog);
            }
        });
        return  dialog;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public void dissmisView(){
         dismiss();
        if (isRecordActivity)
            ((OpenDocRecordsActivity) getActivity()).gotoOpenDocStockList();
        else
            ((OpenDocStockListActivity) getActivity()).setCompanyName();
    }

    private void setViews() {

        mBinding.getRoot().post(() -> {
            Display mDisplay = getActivity().getWindowManager().getDefaultDisplay();
            final int width = mDisplay.getWidth();
            final int height = mDisplay.getHeight() +1000;/// 2;

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
        //    mBinding.getRoot().setLayoutParams(layoutParams);
        });

       adapter= new CompanyBottomSheetAdapter(getContext(),PreferencesHelper.getLoginResponse().getResult().getCariler(),this);
        // mAdapter = new PlanAdapter(result -> mListener.onClicked(result));
        mBinding.recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        mBinding.recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        mBinding.recycler.setAdapter(adapter);
        mBinding.topLL.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark2));

        for (LoginResponse.ResultBean.CarilerBean carilerBean : PreferencesHelper.getLoginResponse().getResult().getCariler()){
            carilerBeansOrj.add(carilerBean);
        }

        mBinding.topLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        mBinding.edtSearchCompany.addTextChangedListener(new TextWatcher() {
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
                    adapter.setData(carilerBeansOrj);
                }
                else{
                //    carilerBeans = filterCity(PreferencesHelper.getLoginResponse().getResult().getCariler(),s.toString().toLowerCase());
                    adapter.setData(filterCity(s.toString().toLowerCase()));
                }
            }

        });
    }

    public String clearedTurkishCharacter(String oldStr){
        String ret = oldStr;
        char[] turkishChars = new char[] {0x131, 0x130, 0xFC, 0xDC, 0xF6, 0xD6, 0x15F, 0x15E, 0xE7, 0xC7, 0x11F, 0x11E};
        char[] englishChars = new char[] {'i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g', 'G'};
        for (int ij = 0; ij < turkishChars.length; ij++) {
            ret = ret.replaceAll(new String(new char[]{turkishChars[ij]}), new String(new char[]{englishChars[ij]}));
        }
        return ret;
    }

    public List<LoginResponse.ResultBean.CarilerBean>  filterCity( String searchValue){
        List<LoginResponse.ResultBean.CarilerBean>  newCariler = new ArrayList<>();
        List<LoginResponse.ResultBean.CarilerBean>  carilerBeans = new ArrayList<>();

        for (LoginResponse.ResultBean.CarilerBean carilerBean : carilerBeansOrj){
            carilerBeans.add(carilerBean);
        }
        for(LoginResponse.ResultBean.CarilerBean cari : carilerBeans){
            boolean found =  cari.getAd().toLowerCase().toString().contains(searchValue.toLowerCase().toString());//clearedTurkishCharacter(cari.getAd().replaceAll("I","i").replaceAll("İ","i")).toLowerCase().toString().matches("(?i).*" + clearedTurkishCharacter(searchValue.replaceAll("I","i").replaceAll("İ","i")).toLowerCase().toString()+ ".*");
            if(found){
                newCariler.add(cari);
            }
        }
        return newCariler;

    }
    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }

}