package com.arneca.evyap.api.response.cmx;/*
 * Created by Sinan KUTAS on 8/17/22.
 */

public class OpenDocRecordsResponse {

    /**
     * result : {"AcikBelgeSayisi":91}
     * result_message : {"code":"0007","message":"Açık Belge Var","type":"E"}
     */

    private ResultBean result;
    private ResultMessageBean result_message;

    public static class ResultBean {
        /**
         * AcikBelgeSayisi : 91
         */

        private int AcikBelgeSayisi;

        public int getAcikBelgeSayisi() {
            return AcikBelgeSayisi;
        }

        public void setAcikBelgeSayisi(int acikBelgeSayisi) {
            AcikBelgeSayisi = acikBelgeSayisi;
        }
    }

    public static class ResultMessageBean {
        /**
         * code : 0007
         * message : Açık Belge Var
         * type : E
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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public ResultMessageBean getResult_message() {
        return result_message;
    }

    public void setResult_message(ResultMessageBean result_message) {
        this.result_message = result_message;
    }
}
