package com.arneca.evyap.api.response.cmx;/*
 * Created by Sinan KUTAS on 9/8/22.
 */

public class PDFResponse {


    /**
     * result : {"Url":"https://mys.cemixtextile.com/public/pdftemp/SatisRaporuPDF/fc04400a-6b85-4b0d-a164-23e073375cc1.pdf"}
     * result_message : {"code":"0000","message":"İşlem Başarılı ","type":"S"}
     */

    private ResultBean result;
    private ResultMessageBean result_message;

    public static class ResultBean {
        /**
         * Url : https://mys.cemixtextile.com/public/pdftemp/SatisRaporuPDF/fc04400a-6b85-4b0d-a164-23e073375cc1.pdf
         */

        private String Url;

        public String getUrl() {
            return Url;
        }

        public void setUrl(String url) {
            Url = url;
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
