package com.arneca.evyap.api.response;/*
 * Created by sinan KUTAS on 1.02.2021.
 */

import java.util.List;


public class GetSendSecurtyCode {


    /**
     * status : User Mail is not valid.Please confirm email.
     * response : false
     * data : {"myArrayList":[]}
     */

    private String status;
    private boolean response;
    private DataBean data;
    private boolean  isKVKKConfirmed;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<?> myArrayList;
    }

    public boolean isKVKKConfirmed() {
        return isKVKKConfirmed;
    }

    public void setKVKKConfirmed(boolean KVKKConfirmed) {
        isKVKKConfirmed = KVKKConfirmed;
    }
}
