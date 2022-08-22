package com.arneca.evyap.ui.activity.cmx;/*
 * Created by Sinan KUTAS on 8/22/22.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.arneca.evyap.R;
import com.arneca.evyap.databinding.CmxopenDocsActivityBinding;
import com.arneca.evyap.databinding.ProfileBinding;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.ui.activity.BaseActivity;

import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class ProfileActivity extends BaseActivity {

    private ProfileBinding binding;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.profile);
        binding.toolbar.txtViewTitle.setText("Profil");
        binding.toolbar.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.txtUser.setText(PreferencesHelper.getLoginResponse().getResult().getProfil().getKullanici());
        binding.txtGSM.setText(PreferencesHelper.getLoginResponse().getResult().getProfil().getGsm());
        binding.txtMail.setText(PreferencesHelper.getLoginResponse().getResult().getProfil().getEposta());
        binding.txtSubeId.setText(""+PreferencesHelper.getLoginResponse().getResult().getProfil().getSubeID());
        binding.txtSubeKod.setText(""+PreferencesHelper.getLoginResponse().getResult().getProfil().getSubeKodu());
        binding.txtSubeName.setText(""+PreferencesHelper.getLoginResponse().getResult().getProfil().getSubeAdi());
    }
}
