package com.arneca.evyap.api.response.cmx;/*
 * Created by Sinan KUTAS on 8/15/22.
 */

import java.util.List;


public class OpenDocumentStockListResponse {


    /**
     * result : [{"belge_detay_id":48,"detay_guid":"663c8e58-85a5-46b3-bca6-68b298bcc97b","kod":"2001-1","ad":"2-6 YANDAN CEPLİ KIZ KOT PANTOLON","renk":"MAVİ","miktar":"10"},{"belge_detay_id":49,"detay_guid":"2eab4063-1451-4729-82bb-4abe3c88526c","kod":"2001-2","ad":"7-11 YANDAN CEPLİ KIZ KOT PANTOLON","renk":"MAVİ","miktar":"20"},{"belge_detay_id":50,"detay_guid":"3383cbc8-5c2e-4839-8343-bcccf691714e","kod":"2001-3","ad":"11-15 YANDAN CEPLİ KIZ KOT PANTOLON","renk":"MAVİ","miktar":"30"}]
     * result_message : {"code":"0000","message":"İşlem Başarılı","type":"S"}
     */

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
        /**
         * belge_detay_id : 48
         * detay_guid : 663c8e58-85a5-46b3-bca6-68b298bcc97b
         * kod : 2001-1
         * ad : 2-6 YANDAN CEPLİ KIZ KOT PANTOLON
         * renk : MAVİ
         * miktar : 10
         */

        private int belge_detay_id;
        private String detay_guid;
        private String kod;
        private String ad;
        private String renk;
        private String miktar;

        public int getBelge_detay_id() {
            return belge_detay_id;
        }

        public void setBelge_detay_id(int belge_detay_id) {
            this.belge_detay_id = belge_detay_id;
        }

        public String getDetay_guid() {
            return detay_guid;
        }

        public void setDetay_guid(String detay_guid) {
            this.detay_guid = detay_guid;
        }

        public String getKod() {
            return kod;
        }

        public void setKod(String kod) {
            this.kod = kod;
        }

        public String getAd() {
            return ad;
        }

        public void setAd(String ad) {
            this.ad = ad;
        }

        public String getRenk() {
            return renk;
        }

        public void setRenk(String renk) {
            this.renk = renk;
        }

        public String getMiktar() {
            return miktar;
        }

        public void setMiktar(String miktar) {
            this.miktar = miktar;
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
