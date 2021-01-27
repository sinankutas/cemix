package com.arneca.evyap.helper;/*
 * Created by Sinan KUTAS  on 22.01.2021.
 */

import android.content.Context;

import com.arneca.evyap.api.response.GetFactories;

import androidx.databinding.BaseObservable;

public class PreferencesHelper extends BaseObservable {
    private static boolean isResumePassive;
    private static boolean isRememberMe;
    private static String userName ;
    private static String password ;
    private static GetFactories getFactories;
    private static GetFactories.data selectedFactory;


    public static boolean isIsRememberMe(Context context) {
        return SharedPreferenceHelper.getSharedPreferenceBoolean(context, "isRememberMe", false);
    }

    public static void setIsRememberMe(Context context,boolean isRememberMe) {
        PreferencesHelper.isRememberMe = isRememberMe;
        SharedPreferenceHelper.setSharedPreferenceBoolean(context, "isRememberMe", isRememberMe);
    }

    public static String getUserName(Context context) {
        return  SharedPreferenceHelper.getSharedPreferenceString(context, "userName", "");
    }

    public static void setUserName(Context context, String userName) {
        PreferencesHelper.userName = userName;
        SharedPreferenceHelper.setSharedPreferenceString(context, "userName", userName);
    }

    public static String getPassword(Context context) {
        return  SharedPreferenceHelper.getSharedPreferenceString(context, "password", "");
    }

    public static void setPassword(Context context, String password) {
        PreferencesHelper.password = password;
        SharedPreferenceHelper.setSharedPreferenceString(context, "password", password);
    }

    public static GetFactories getGetFactories() {
        return getFactories;
    }

    public static void setGetFactories(GetFactories getFactories) {
        PreferencesHelper.getFactories = getFactories;
    }

    public static GetFactories.data getSelectedFactory() {
        return selectedFactory;
    }

    public static void setSelectedFactory(GetFactories.data selectedFactory) {
        PreferencesHelper.selectedFactory = selectedFactory;
    }
}
