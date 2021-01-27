package com.arneca.evyap.api.request;


import android.content.Context;

import com.arneca.evyap.api.service.Client;
import com.arneca.evyap.ui.Completed;

import java.util.HashMap;

public class Request {

    public static void GetFactories(HashMap<String, Object> map, Context context, Completed completed) {
        Client.Instance().GetFactories(map).enqueue(CallBack.callback(context, completed));
    }

    public static void GetLines(HashMap<String, Object> map, Context context, Completed completed) {
        Client.Instance().GetLines(map).enqueue(CallBack.callback(context, completed));
    }

    public static void GetLineInfo(HashMap<String, Object> map, Context context, Completed completed) {
        Client.Instance().GetLineInfo(map).enqueue(CallBack.callback(context, completed));
    }

    public static void GetAllLineInfo(HashMap<String, Object> map, Context context, Completed completed) {
        Client.Instance().GetAllLineInfo(map).enqueue(CallBack.callback(context, completed));
    }
}
