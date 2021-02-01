package com.arneca.evyap.api.response;/*
 * Created by Sinan KUTAS on 1.02.2021.
 */

import java.util.List;

public class GetValidateSecurtyCode {


    /**
     * status : Security Code is Valid
     * response : true
     * data : {"myArrayList":[]}
     */

    private String status;
    private boolean response;
    private DataBean data;

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
}
