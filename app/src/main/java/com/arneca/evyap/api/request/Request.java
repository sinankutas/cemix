package com.arneca.evyap.api.request;


import android.content.Context;

import com.arneca.evyap.api.service.Client;
import com.arneca.evyap.ui.Completed;

import java.util.HashMap;

import retrofit2.http.HeaderMap;

public class Request {

    public static void GetFactories(@HeaderMap HashMap<String, Object> headers, HashMap<String, Object> map, Context context, Completed completed) {
        Client.Instance().GetFactories(headers,map).enqueue(CallBack.callback(context, completed));
    }

    public static void GetLines(@HeaderMap HashMap<String, Object> headers,HashMap<String, Object> map, Context context, Completed completed) {
        Client.Instance().GetLines(headers,map).enqueue(CallBack.callback(context, completed));
    }

    public static void GetLineInfo(@HeaderMap HashMap<String, Object> headers,HashMap<String, Object> map, Context context, Completed completed) {
        Client.Instance().GetLineInfo(headers,map).enqueue(CallBack.callback(context, completed));
    }

    public static void GetAllLineInfo(@HeaderMap HashMap<String, Object> headers,HashMap<String, Object> map, Context context, Completed completed) {
        Client.Instance().GetAllLineInfo(headers,map).enqueue(CallBack.callback(context, completed));
    }

    public static void getTokens(HashMap<String, Object> headersMap,  Context context, Completed completed) {
        Client.Instance().getTokens(headersMap).enqueue(CallBack.callback(context, completed));
    }


}
