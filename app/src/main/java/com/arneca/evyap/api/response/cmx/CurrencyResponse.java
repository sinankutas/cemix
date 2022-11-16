package com.arneca.evyap.api.response.cmx;/*
 * Created by Sinan KUTAS on 11/16/22.
 */

import java.util.List;


public class CurrencyResponse {


    private ResultMessageBean result_message;
    private List<ResultBean> result;

    public static class ResultMessageBean {
        /**
         * code : 0000
         * message : İşlem Başarılı
         * type : S
         */

        private String code;
        private String message;
        private String type;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class ResultBean {
        private int dvz_id;
        private String dvz;

        public int getDvz_id() {
            return dvz_id;
        }

        public void setDvz_id(int dvz_id) {
            this.dvz_id = dvz_id;
        }

        public String getDvz() {
            return dvz;
        }

        public void setDvz(String dvz) {
            this.dvz = dvz;
        }
    }

    public ResultMessageBean getResult_message() {
        return result_message;
    }

    public void setResult_message(ResultMessageBean result_message) {
        this.result_message = result_message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }
}
