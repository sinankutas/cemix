package com.arneca.evyap.helper;/*
 * Created by Sinan KUTAS on 8/22/22.
 */

import android.content.Context;

import com.arneca.evyap.R;
import com.lxj.xpopup.core.BottomPopupView;

import androidx.annotation.NonNull;

public class CustomEditTextBottomPopup  extends BottomPopupView {
    public CustomEditTextBottomPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_edittext_bottom_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onShow() {
        super.onShow();
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
//        Log.e("tag", "CustomEditTextBottomPopup  onDismiss");
    }


//    @Override
//    protected int getMaxHeight() {
//        return (int) (XPopupUtils.getWindowHeight(getContext())*0.75);
//    }
}
