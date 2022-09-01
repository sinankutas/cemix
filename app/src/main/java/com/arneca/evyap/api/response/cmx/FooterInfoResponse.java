package com.arneca.evyap.api.response.cmx;/*
 * Created by Sinan KUTAS on 9/1/22.
 */

public class FooterInfoResponse {


    /**
     * result : {"sayi":0,"miktar":null,"tutar":null}
     * result_message : {"code":"0000","message":"İşlem Başarılı ","type":"S"}
     */

    private ResultBean result;
    private ResultMessageBean result_message;

    public static class ResultBean {
        /**
         * sayi : 0
         * miktar : null
         * tutar : null
         */

        private int sayi;
        private Object miktar;
        private Object tutar;

        public int getSayi() {
            return sayi;
        }

        public void setSayi(int sayi) {
            this.sayi = sayi;
        }

        public Object getMiktar() {
            return miktar;
        }

        public void setMiktar(Object miktar) {
            this.miktar = miktar;
        }

        public Object getTutar() {
            return tutar;
        }

        public void setTutar(Object tutar) {
            this.tutar = tutar;
        }
    }

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
