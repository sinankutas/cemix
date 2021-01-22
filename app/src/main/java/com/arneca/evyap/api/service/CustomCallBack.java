package com.arneca.evyap.api.service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class CustomCallBack<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.isSuccessful())
            onSuccess(call, response);
        else {

            String err = response.message();
            err.toString();
            Client.handleError2(response);
            if (response.code() == 401) {

            }
          //  Client.handleError(response.errorBody());

          onFail(null,call, response);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onError(call,t);
    }

    public abstract void onSuccess(Call<T> call, Response<T> response);
    public abstract void onFail(Call message,Call<T> call, Response<T> response);
    public abstract void onError(Call<T> call, Throwable t);

}
