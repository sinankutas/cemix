package com.arneca.evyap.api.request;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.arneca.evyap.R;
import com.arneca.evyap.api.response.MyErrorMessage;
import com.arneca.evyap.helper.Tool;
import com.arneca.evyap.ui.Completed;
import com.arneca.evyap.api.service.CustomCallBack;
import com.arneca.evyap.ui.activity.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Response;

public class CallBack {
    public static <T> CustomCallBack callback(Context context, Completed completed) {

        return new CustomCallBack<T>() {

            @Override
            public void onSuccess(Call<T> call, Response<T> response) {
                Log.d("callback", "success");
                completed.body(response);
            }

         @Override
            public void onFail(Call message, Call<T> call, Response<T> response) {

                Log.d("callback", "fail");
                BaseActivity activity = null;
                try {
                    activity = (BaseActivity) context;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Tool.hideDialog();
                if (activity != null) {
                    if (response.code() == 401) {
                        Tool.showInfo(context,
                                activity.getString(R.string.error),
                                activity.getString(R.string.noauth),
                                (dialog, which) -> gotoLogin(context));

                        return;
                    }
             /*       if (message != null && message.getResult_message() != null && message.getResult_message().getMessage() != null)
                        activity.showPopup(activity
                                , message.getResult_message().getTitle()
                                , message.getResult_message().getMessage());

                    if (response.code() != 200) {
                        Tool.showInfo(context, activity.getString(R.string.error), activity.getString(R.string.timeout), (dialog, which) -> ((BaseActivity) context).finish());
                    }else*/ if (response.code() == 500 || response.code() == 400){
                        Tool.showInfo(context, activity.getString(R.string.error), activity.getString(R.string.incorrectcode), (dialog, which) -> ((BaseActivity) context).finish());

                    }
                }
            }

            private void gotoLogin(Context context) {
             //   ((BaseActivity) context).finish();
            /*    Intent intent = new Intent(context, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                context.startActivity(intent);*/
            }

            @Override
            public void onError(Call<T> call, Throwable t) {
                Log.d("callback", "error");
                BaseActivity activity = null;
                try {
                    activity = (BaseActivity) context;
                    if (t.getMessage().contains("No address associated with hostname")) {
                        activity.showIntentErrorPopup(activity,"error", "info");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Tool.hideDialog();
                //activity.showPopup(activity, activity.getString(R.string.error) ,activity.getString(R.string.connection_error));
            }
        };
    }
}