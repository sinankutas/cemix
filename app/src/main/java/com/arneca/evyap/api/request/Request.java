package com.arneca.evyap.api.request;


import android.content.Context;

import com.arneca.evyap.api.service.Client;
import com.arneca.evyap.ui.Completed;

import java.util.HashMap;

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

    public static void sendSecurtyCode(String mail, Context context, Completed completed) {
        Client.Instance().sendSecurtyCode(mail).enqueue(CallBack.callback(context, completed));
    }

    public static void validateSecurtyCode(@Body HashMap<String, Object> map, Context context, Completed completed) {
        Client.Instance().validateSecurtyCode(map).enqueue(CallBack.callback(context, completed));
    }

    public static void changePassword(@Body HashMap<String, Object> map, Context context, Completed completed) {
        Client.Instance().changePassword(map).enqueue(CallBack.callback(context, completed));
    }

}
