package com.arneca.evyap.api.service;

import android.app.Activity;
import android.util.Log;

import com.arneca.evyap.api.response.ResultMessage;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Client {
    public static String BASE_URL =  "https://EVYTWXDMZ01.evyap.com.tr/restApi/";



    private static Retrofit client;
    private static Services services;

    private static Services connectionSetup() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
               .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .build();

        client = new Retrofit.Builder().client(httpClient).baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        services = client.create(Services.class);

        return services;
    }

    public static void clearServices() {
        services = null;
    }

    public static synchronized Services Instance() {
        if (services == null) {
            services = connectionSetup();
        }
        return services;
    }

    public static boolean handleError2( Response<?> response) {
        if(response != null && !response.isSuccessful() && response.errorBody() != null) {
            Converter<ResponseBody, Error> converter = client.responseBodyConverter(Error.class, new Annotation[0]);
            try {
                String errorResponse = converter.convert(response.errorBody()).toString();
                errorResponse.toString();
                // do something
            } catch(IOException e) {
                Log.e("", "An error occurred", e);
            }
            return true;
        }
        return false;
    }


    public static ResultMessage handleError(ResponseBody errorBody) {
        try {
            ResultMessage myError = (ResultMessage) client.responseBodyConverter(ResultMessage.class, ResultMessage.class.getAnnotations()).convert(errorBody);
            return myError;
        } catch (IOException e) {

        }
        return null;
    }

    public static HashMap<String, Object> standardHashMap() {
        HashMap<String, Object> map = new HashMap<>();
        return map;
    }

    public static HashMap<String, Object> headersHashMap(Activity activity) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("Accept", "application/json");
        map.put("Content-Type", "application/json");
        return map;
    }

    public static HashMap<String, RequestBody> standardMultiPartHashMap() {
        HashMap<String, RequestBody> map = new HashMap<>();
        return map;
    }
}
