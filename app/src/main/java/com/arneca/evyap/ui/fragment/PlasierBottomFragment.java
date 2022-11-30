package com.arneca.evyap.ui.fragment;/*
 * Created by  Sinan KUTASon 8/26/22.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.arneca.evyap.R;
import com.arneca.evyap.api.request.Request;
import com.arneca.evyap.api.response.cmx.AddNoteResponse;
import com.arneca.evyap.api.response.cmx.CurrencyResponse;
import com.arneca.evyap.api.response.cmx.TanimlarResponse;
import com.arneca.evyap.api.response.cmx.TanimlarResultModel;
import com.arneca.evyap.databinding.PlasierBottomBinding;
import com.arneca.evyap.databinding.PlasierBottomNewBinding;
import com.arneca.evyap.databinding.TanimBottomSheetBinding;
import com.arneca.evyap.helper.DBHelper;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.activity.cmx.OpenDocStockListActivity;
import com.arneca.evyap.ui.activity.cmx.Take2;
import com.arneca.evyap.ui.activity.cmx.TakePhotoActivity;
import com.arneca.evyap.ui.adapter.cmx.TanimlarBottomSheetAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PlasierBottomFragment extends Fragment {
    private PlasierBottomNewBinding mBinding;
    private CountryBottomFragment countryBottomFragment;
    private String selectedPlasier;
    private String selectedCountry;
    private CurrencyResponse currencyResponse;
    private  ArrayList<String> currencyIds = new ArrayList<>();
    private  ArrayList<String> currencyValues = new ArrayList<>();
    private String selectedPriceUnit = "1";
    private String tahsilatType = "Nakit";

    public static PlasierBottomFragment newInstance() {
        PlasierBottomFragment fragment = new PlasierBottomFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.plasier_bottom_new, container, false);
        setViews();
        PreferencesHelper.setCurrentBase64("");
        PreferencesHelper.setSelectedCurrency("");
        return mBinding.getRoot();
    }


    private void showCurrency(){
      //  String[] urls = {"1","2","3"};
      //  String[] urlsAlias = {"TL","USD","EURO"};
        AlertDialog.Builder  builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Döviz Birimi Seç");
        builder.setCancelable(false);

        String[] currencyValuesArray = new String[currencyValues.size()];
        currencyValuesArray = currencyValues.toArray(currencyValuesArray);


        String[] finalCurrencyValuesArray = currencyValuesArray;
        builder.setSingleChoiceItems(currencyValuesArray, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //selectedBase = urls[i];
               // PreferencesHelper.setBaseUrl(selectedBase);
                String selectedCurrency = finalCurrencyValuesArray[i]+"_"+currencyIds.get(i);
                mBinding.btnSelectedCurrenyValue.setText(finalCurrencyValuesArray[i]);
                mBinding.btnSelectedCurrenyKey.setText(Html.fromHtml("<u>Seçilen Döviz Cinsi:</u> "));
                PreferencesHelper.setSelectedCurrency(selectedCurrency);
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("Seç", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (PreferencesHelper.getSelectedCurrency().length()>0)
                  dialogInterface.dismiss();
            }
        });

        Log.d("selectedBase","selectedBase");
        builder.show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void selectedCountryName(String selectedCountry){
        this.selectedCountry = selectedCountry;
        mBinding.countryEdt.setText(selectedCountry);
    }

  /*  @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override public void onShow(DialogInterface dialogInterface) {
                BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
                setupFullHeight(bottomSheetDialog);
            }
        });
        return  dialog;
    }*/

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

    public void dissmisView(){
        //dismiss();
//        getActivity().getFragmentManager().popBackStack();
        PreferencesHelper.getOpenDocStockListActivity().callOnBack();
        if (selectedCountry == null)
            selectedCountry = "";
        String total = "";
        String desc = "";
        if (mBinding.edtTotalPrice.getText()!=null)
            total = mBinding.edtTotalPrice.getText().toString();

        if (mBinding.edtDesc.getText()!=null)
            desc = mBinding.edtDesc.getText().toString();

            ((OpenDocStockListActivity) getActivity()).gotoCompletedDoc(selectedPlasier,mBinding.nameEdt.getText().toString(),
                    selectedCountry.toString(),mBinding.cargoEdt.getText().toString(),mBinding.phoneEdt.getText().toString(),total,selectedPriceUnit,desc, tahsilatType);

    }

    private void setViews() {

        loadPriceUnitListeners();

        mBinding.getRoot().post(() -> {
            Display mDisplay = getActivity().getWindowManager().getDefaultDisplay();
            final int width = mDisplay.getWidth();
            final int height = mDisplay.getHeight() +1000;/// 2;

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
            mBinding.getRoot().setLayoutParams(layoutParams);
        });


        mBinding.topLL.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark2));

        mBinding.topLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            //    dismiss();
                getActivity().getFragmentManager().popBackStack();
            }
        });

        mBinding.btnCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCountryList();
            }
        });

        mBinding.lytNakitTahsilat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tahsilatType = "Nakit";
                mBinding.btnNakitTahsilat.setBackgroundResource(R.drawable.checkedbox);
                mBinding.btnNakitTahsilat.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));

                mBinding.btnKargoCeki.setBackgroundResource(R.drawable.uncheckedbox);
                mBinding.btnKargoCeki.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));
            }
        });

          mBinding.lytKargoCeki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tahsilatType = "KargoCek";
                mBinding.btnKargoCeki.setBackgroundResource(R.drawable.checkedbox);
                mBinding.btnKargoCeki.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));

                mBinding.btnNakitTahsilat.setBackgroundResource(R.drawable.uncheckedbox);
                mBinding.btnNakitTahsilat.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));
            }
        });


        mBinding.txtPlasier1.setText(PreferencesHelper.getLoginResponse().getResult().getPlasiyerKodlari().get(0).getPlasiyer_kodu_isyeri());
        mBinding.txtPlasier2.setText(PreferencesHelper.getLoginResponse().getResult().getPlasiyerKodlari().get(1).getPlasiyer_kodu_isyeri());
        selectedPlasier = PreferencesHelper.getLoginResponse().getResult().getPlasiyerKodlari().get(0).getPlasiyer_kodu_isyeri();
        mBinding.btnPlasier1.setBackgroundResource(R.drawable.checkedbox);
        mBinding.btnPlasier1.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));

        if (PreferencesHelper.getActiveDocType().equals("fuar")){
            mBinding.lytTahsilat.setVisibility(View.VISIBLE);
        }else{
            mBinding.lytTahsilat.setVisibility(View.GONE);
        }

        if (PreferencesHelper.getSelectedCompany().getKod().equals("120.99.01")){
            mBinding.lytExtra.setVisibility(View.VISIBLE);
            mBinding.btnSelectedCurreny.setVisibility(View.GONE);
        }else{
            mBinding.lytExtra.setVisibility(View.GONE);
            mBinding.btnSelectedCurreny.setVisibility(View.VISIBLE);
            loadCurrency();
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
               //     String country = mBinding.countryEdt.getText().toString();

                    if (name == null)
                        name = "";
                    if (phone == null)
                        phone = "";
                    if (selectedCountry == null)
                        selectedCountry = "";

                    if (!name.equals("") && !phone.equals("") && !selectedCountry.equals("")){
                        dissmisView();
                    }else{
                        Tool.showInfo(getContext(),"Hata","İsim, Telefon ve Ülke boş geçilemez");
                    }
                }else{
                    dissmisView();
                }


            }
        });




        mBinding.btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TakePhotoActivity.class);
                startActivity(intent);
            }
        });

        if (mBinding.btnSelectedCurrenyValue.getText().length()>0){
            mBinding.btnSelectedCurrenyKey.setText("Seçilen Döviz Cinsi: ");
        }else{
            mBinding.btnSelectedCurrenyKey.setText(Html.fromHtml(" <u> Döviz Cinsi Seç</u>"));

        }

        if (mBinding.btnSelectedCurrenyValue.getText().length()>0){
            mBinding.btnSelectedCurrenyValue.setText("TL");
        }

        mBinding.btnSelectedCurreny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCurrency();
            }
        });
    }



    private void loadCurrency() {
            Tool.openDialog(getActivity());
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("OturumKodu", PreferencesHelper.getLoginResponse().getResult().getOturumKodu())
                    .addFormDataPart("idx", PreferencesHelper.getLoginResponse().getResult().getProfil().getIdx())
                    .addFormDataPart("CariKod", PreferencesHelper.getSelectedCompany().getKod())
                    .build();

            Request.getCariDovizler(requestBody, getActivity(), response -> {
                 currencyResponse = ( CurrencyResponse) response.body();
                 if (currencyResponse!= null){
                        if (currencyResponse.getResult()!= null){
                         if (currencyResponse.getResult().size()>0){
                             for (CurrencyResponse.ResultBean resultBean : currencyResponse.getResult()){
                                 currencyIds.add(resultBean.getDvz_id()+"");
                                 currencyValues.add(resultBean.getDvz());
                             }
                         }
                     }else{
                            currencyIds.add("0");
                            currencyValues.add("USD");
                     }
                 }else{
                     currencyIds.add("0");
                     currencyValues.add("USD");
                 }
                Tool.hideDialog();
           //     Tool.showInfo(getActivity(), "Bilgi", currencyResponse.getResult_message().getMessage());
            });

    }

    private void openCountryList() {
        countryBottomFragment = new CountryBottomFragment().newInstance(this);
        countryBottomFragment.show(getParentFragmentManager(), "");
    }


 /*   @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }
*/

    private void loadPriceUnitListeners() {
        mBinding.lytTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPriceUnit = "0";
                mBinding.btnTL.setBackgroundResource(R.drawable.checkedbox);
                mBinding.btnTL.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));

                mBinding.btnUSD.setBackgroundResource(R.drawable.uncheckedbox);
                mBinding.btnUSD.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));
                mBinding.btnEUR.setBackgroundResource(R.drawable.uncheckedbox);
                mBinding.btnEUR.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));
                mBinding.btnOther.setBackgroundResource(R.drawable.uncheckedbox);
                mBinding.btnOther.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));
            }
        });

        mBinding.lytUSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPriceUnit = "1";
                mBinding.btnUSD.setBackgroundResource(R.drawable.checkedbox);
                mBinding.btnUSD.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));
                mBinding.btnTL.setBackgroundResource(R.drawable.uncheckedbox);
                mBinding.btnTL.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));
                mBinding.btnEUR.setBackgroundResource(R.drawable.uncheckedbox);
                mBinding.btnEUR.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));
                mBinding.btnOther.setBackgroundResource(R.drawable.uncheckedbox);
                mBinding.btnOther.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));
            }
        });

        mBinding.lytEUR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPriceUnit = "2";
                mBinding.btnEUR.setBackgroundResource(R.drawable.checkedbox);
                mBinding.btnEUR.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));
                mBinding.btnTL.setBackgroundResource(R.drawable.uncheckedbox);
                mBinding.btnTL.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));
                mBinding.btnUSD.setBackgroundResource(R.drawable.uncheckedbox);
                mBinding.btnUSD.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));
                mBinding.btnOther.setBackgroundResource(R.drawable.uncheckedbox);
                mBinding.btnOther.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));
            }
        });

        mBinding.lytOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPriceUnit = "3";
                mBinding.btnOther.setBackgroundResource(R.drawable.checkedbox);
                mBinding.btnOther.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));
                mBinding.btnTL.setBackgroundResource(R.drawable.uncheckedbox);
                mBinding.btnTL.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));
                mBinding.btnUSD.setBackgroundResource(R.drawable.uncheckedbox);
                mBinding.btnUSD.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));
                mBinding.btnEUR.setBackgroundResource(R.drawable.uncheckedbox);
                mBinding.btnEUR.setBackgroundTintList(getResources().getColorStateList(R.color.dropdownColor));
            }
        });
    }

}