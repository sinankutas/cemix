package com.arneca.evyap.helper;/*
 * Created by Sinan KUTAS  on 22.01.2021.
 */

import android.content.Context;

import com.arneca.evyap.api.ReportMap;
import com.arneca.evyap.api.ReportModel;
import com.arneca.evyap.api.response.GetFactories;
import com.arneca.evyap.api.response.cmx.LoginResponse;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;

import java.util.ArrayList;

import androidx.databinding.BaseObservable;

public class PreferencesHelper extends BaseObservable {
    private static boolean isResumePassive;
    private static boolean isRememberMe;
    private static boolean isAppOpenedFirst;
    private static boolean isKVKKReaded;
    private static String userName ;
    private static String password ;
    private static String appKey ;
    private static GetFactories getFactories;
    private static GetFactories.DataBean.MyArrayListBean.MapBean selectedFactory;
    private static ArrayList<ReportModel> reportModels;
    private static int totalSelection;


    /*
     Cemix
     */

    private static LoginResponse loginResponse;
    private static LoginResponse.ResultBean.CarilerBean selectedCompany;
    private static JSONArray jsonArrayForMatris = new JSONArray();
    private static String activeDocType ;

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

    public static GetFactories.DataBean.MyArrayListBean.MapBean getSelectedFactory() {
        return selectedFactory;
    }

    public static void setSelectedFactory(GetFactories.DataBean.MyArrayListBean.MapBean selectedFactory) {
        PreferencesHelper.selectedFactory = selectedFactory;
    }

    public static String getAppKey(Context context) {
        return  SharedPreferenceHelper.getSharedPreferenceString(context, "appKey", "");
    }

    public static void setAppKey(Context context, String appKey) {
        PreferencesHelper.appKey = appKey;
        SharedPreferenceHelper.setSharedPreferenceString(context, "appKey", appKey);
    }

    public static boolean isIsAppOpenedFirst(Context context) {
        return SharedPreferenceHelper.getSharedPreferenceBoolean(context, "isAppOpenedFirst", false);
    }

    public static void setIsAppOpenedFirst(Context context, boolean isAppOpenedFirst) {
        PreferencesHelper.isAppOpenedFirst = isAppOpenedFirst;
        SharedPreferenceHelper.setSharedPreferenceBoolean(context, "isAppOpenedFirst", isAppOpenedFirst);
    }

    public static boolean isIsKVKKReaded(Context context) {
        return SharedPreferenceHelper.getSharedPreferenceBoolean(context, "isKVKKReaded", false);
    }

    public static void setIsKVKKReaded(Context context, boolean isKVKKReaded) {
        PreferencesHelper.isKVKKReaded = isKVKKReaded;
        SharedPreferenceHelper.setSharedPreferenceBoolean(context, "isKVKKReaded", isKVKKReaded);
    }

    public static ArrayList<ReportModel> getReportModels() {
        reportModels = Hawk.get("reportModels");
        return reportModels;
    }

    public static void setReportModels(ArrayList<ReportModel> reportModels) {
        PreferencesHelper.reportModels = reportModels;
        Hawk.put("reportModels",reportModels);
    }

    public static int getTotalSelection(Context contex) {
        return SharedPreferenceHelper.getSharedPreferenceInt(contex, "totalSelection", 0);
    }

    public static void setTotalSelection(Context context,int totalSelection) {
        PreferencesHelper.totalSelection = totalSelection;
        SharedPreferenceHelper.setSharedPreferenceInt(context, "totalSelection", totalSelection);
    }

    public static LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public static void setLoginResponse(LoginResponse loginResponse) {
        PreferencesHelper.loginResponse = loginResponse;
    }

    public static LoginResponse.ResultBean.CarilerBean getSelectedCompany() {
        return selectedCompany;
    }

    public static void setSelectedCompany(LoginResponse.ResultBean.CarilerBean selectedCompany) {
        PreferencesHelper.selectedCompany = selectedCompany;
    }

    public static JSONArray getJsonArrayForMatris() {
        return jsonArrayForMatris;
    }

    public static void setJsonArrayForMatris(JSONArray jsonArrayForMatris) {
        PreferencesHelper.jsonArrayForMatris = jsonArrayForMatris;
    }

    public static String getActiveDocType() {
        return activeDocType;
    }

    public static void setActiveDocType(String activeDocType) {
        PreferencesHelper.activeDocType = activeDocType;
    }
}
