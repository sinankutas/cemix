package com.arneca.evyap.api.response;/*
 * Created by Sinan KUTAS on 28.01.2021.
 */

import java.util.List;

public class GetLogin {


    /**
     * status : Login Successful.Use the appKey in header for the other services
     * data : []
     * response : true
     */

   private String status;
    private boolean response;
    private List<?> data;
    private boolean isKVKKConfirmed;


    public boolean isKVKKConfirmed() {
        return isKVKKConfirmed;
    }

    public void setKVKKConfirmed(boolean KVKKConfirmed) {
        isKVKKConfirmed = KVKKConfirmed;
    }

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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
