package com.arneca.evyap.ui.activity;/*
 * Created by sinan KUTAS on 1.02.2021.
 */

import java.util.List;

public class ChangePassword {


    /**
     * status : 500 : [Error executing service AssignNewPasswordWithSecurityCode. Message :: TypeError: Cannot find function includes in object com.thingworx.common.exceptions.InvalidRequestException: Unable to Load Data Table Entry - Key [_undefined] Does Not Exist. - See Script Error Log for more details.]
     * response : false
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

        public List<?> getMyArrayList() {
            return myArrayList;
        }

        public void setMyArrayList(List<?> myArrayList) {
            this.myArrayList = myArrayList;
        }
    }
}
