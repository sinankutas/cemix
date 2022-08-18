package com.arneca.evyap.api.response.cmx;/*
 * Created by Sinan KUTAS on 8/17/22.
 */

public class NewDocResponse {


    /**
     * result : {"belge_id":156,"guid":"d5863ff6-2156-4cc3-ad3d-71ac09aef1c6","seri":"satis_d14","sira":117,"tarih":"17.08.2022 23:25:52","tarih_":"2022-08-17","detay_kayit_sayisi":0,"detay_stok_miktari":"0"}
     * result_message : {"code":"0000","message":"İşlem Başarılı","type":"S"}
     */

    private ResultBean result;
    private ResultMessageBean result_message;

    public static class ResultBean {
        /**
         * belge_id : 156
         * guid : d5863ff6-2156-4cc3-ad3d-71ac09aef1c6
         * seri : satis_d14
         * sira : 117
         * tarih : 17.08.2022 23:25:52
         * tarih_ : 2022-08-17
         * detay_kayit_sayisi : 0
         * detay_stok_miktari : 0
         */

        private int belge_id;
        private String guid;
        private String seri;
        private int sira;
        private String tarih;
        private String tarih_;
        private int detay_kayit_sayisi;
        private String detay_stok_miktari;

        public int getBelge_id() {
            return belge_id;
        }

        public void setBelge_id(int belge_id) {
            this.belge_id = belge_id;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getSeri() {
            return seri;
        }

        public void setSeri(String seri) {
            this.seri = seri;
        }

        public int getSira() {
            return sira;
        }

        public void setSira(int sira) {
            this.sira = sira;
        }

        public String getTarih() {
            return tarih;
        }

        public void setTarih(String tarih) {
            this.tarih = tarih;
        }

        public String getTarih_() {
            return tarih_;
        }

        public void setTarih_(String tarih_) {
            this.tarih_ = tarih_;
        }

        public int getDetay_kayit_sayisi() {
            return detay_kayit_sayisi;
        }

        public void setDetay_kayit_sayisi(int detay_kayit_sayisi) {
            this.detay_kayit_sayisi = detay_kayit_sayisi;
        }

        public String getDetay_stok_miktari() {
            return detay_stok_miktari;
        }

        public void setDetay_stok_miktari(String detay_stok_miktari) {
            this.detay_stok_miktari = detay_stok_miktari;
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
