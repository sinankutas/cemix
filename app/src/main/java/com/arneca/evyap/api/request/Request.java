package com.arneca.evyap.api.request;


import android.content.Context;

import com.arneca.evyap.api.service.Client;
import com.arneca.evyap.ui.Completed;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;

public class Request {

    public static void GetFactories(@HeaderMap HashMap<String, Object> headers, HashMap<String, Object> map, Context context, Completed completed) {
        Client.Instance().GetFactories(headers,map).enqueue(CallBack.callback(context, completed));
    }

    public static void GetAllLineInfo(@HeaderMap HashMap<String, Object> headers,HashMap<String, Object> map, Context context, Completed completed) {
        Client.Instance().GetAllLineInfo(headers,map).enqueue(CallBack.callback(context, completed));
    }

    public static void GetAllLineInfoByLine(@HeaderMap HashMap<String, Object> headers,HashMap<String, Object> map, Context context, Completed completed) {
        Client.Instance().GetAllLineInfoByLine(headers,map).enqueue(CallBack.callback(context, completed));
    }

    public static void GetAllLineInfoByKey(@HeaderMap HashMap<String, Object> headers,HashMap<String, Object> map, Context context, Completed completed) {
        Client.Instance().GetAllLineInfoByKey(headers,map).enqueue(CallBack.callback(context, completed));
    }

    public static void GetKPIKeys(@HeaderMap HashMap<String, Object> headers,HashMap<String, Object> map, Context context, Completed completed) {
        Client.Instance().GetKPIKeys(headers,map).enqueue(CallBack.callback(context, completed));
    }


    public static void getTokens(HashMap<String, Object> headersMap,  Context context, Completed completed) {
        Client.Instance().getTokens(headersMap).enqueue(CallBack.callback(context, completed));
    }

    public static void getKvkkText(Context context, Completed completed) {
        Client.Instance().getKvkkText().enqueue(CallBack.callback(context, completed));
    }

    public static void confirmKvkkByUserName( HashMap<String, Object> map, Context context, Completed completed) {
        Client.Instance().confirmKvkkByUserName(map).enqueue(CallBack.callback(context, completed));
    }

    public static void confirmKvkkByMail( HashMap<String, Object> map, Context context, Completed completed) {
        Client.Instance().confirmKvkkByMail(map).enqueue(CallBack.callback(context, completed));
    }


    public static void sendSecurtyCode(String mail, Context context, Completed completed) {
        Client.Instance().sendSecurtyCode(mail).enqueue(CallBack.callback(context, completed));
    }

    public static void validateSecurtyCode(@Body HashMap<String, Object> map, Context context, Completed completed) {
        Client.Instance().validateSecurtyCode(map).enqueue(CallBack.callback(context, completed));
    }

    public static void changePassword(@Body HashMap<String, Object> map, Context context, Completed completed) {
        Client.Instance().changePassword(map).enqueue(CallBack.callback(context, completed));
    }


    /*
     *  Cemix
     *
     * */

    public static void login(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().login(body).enqueue(CallBack.callback(context, completed));
    }

    public static void changePass(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().changePass(body).enqueue(CallBack.callback(context, completed));
    }

    public static void openDocs(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().openDocs(body).enqueue(CallBack.callback(context, completed));
    }

    public static void openDocStocks(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().openDocStocks(body).enqueue(CallBack.callback(context, completed));
    }

    public static void productSearch(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().productSearch(body).enqueue(CallBack.callback(context, completed));
    }

    public static void rbMAtris(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().rbMAtris(body).enqueue(CallBack.callback(context, completed));
    }

    public static void sthEkle(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().sthEkle(body).enqueue(CallBack.callback(context, completed));
    }

    public static void openDocCompleted(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().openDocCompleted(body).enqueue(CallBack.callback(context, completed));
    }

    public static void openDocRecords(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().openDocRecords(body).enqueue(CallBack.callback(context, completed));
    }


    public static void createNewDoc(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().createNewDoc(body).enqueue(CallBack.callback(context, completed));
    }

    public static void deleteDoc(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().deleteDoc(body).enqueue(CallBack.callback(context, completed));
    }

    public static void deleteDocFromUplist(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().deleteDocFromUplist(body).enqueue(CallBack.callback(context, completed));
    }

    public static void docUpdate(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().docUpdate(body).enqueue(CallBack.callback(context, completed));
    }

    public static void getTanim(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().getTanim(body).enqueue(CallBack.callback(context, completed));
    }

    public static void getFooterInfo(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().getFooterInfo(body).enqueue(CallBack.callback(context, completed));
    }


    public static void getPDF(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().getPDF(body).enqueue(CallBack.callback(context, completed));
    }


    public static void getPrint(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().getPrint(body).enqueue(CallBack.callback(context, completed));
    }

    public static void docNote(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().docNote(body).enqueue(CallBack.callback(context, completed));
    }

    public static void karsilamaList(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().karsilamaList(body).enqueue(CallBack.callback(context, completed));
    }

    public static void karsilamaListDetail(@Body RequestBody body, Context context, Completed completed) {
        Client.Instance().karsilamaListDetail(body).enqueue(CallBack.callback(context, completed));
    }


}
