package com.arneca.evyap.api.response.cmx;/*
 * Created by Sinan KUTAS on 8/17/22.
 */


public class OpenDocCompletedResponse {


    /**
     * result_message : {"code":"0000","message":"İşlem Başarılı","type":"S"}
     */

    private ResultMessageBean result_message;

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

    public ResultMessageBean getResult_message() {
        return result_message;
    }

    public void setResult_message(ResultMessageBean result_message) {
        this.result_message = result_message;
    }
}
