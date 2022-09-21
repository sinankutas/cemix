package com.arneca.evyap.api.response.cmx;/*
 * Created by Sinan KUTAS on 9/20/22.
 */

import java.util.List;

public class KarsilamaResponse {


    /**
     * result : [{"seri":"ANT-1","sira":218,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-1","sira":219,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-1","sira":220,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-1","sira":221,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-1","sira":222,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-1","sira":223,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-1","sira":225,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-1","sira":226,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-1","sira":227,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-6","sira":217,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-6","sira":270,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":233,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":233,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":233,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":233,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":233,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":233,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":235,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":235,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":235,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":235,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":235,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":235,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":235,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":236,"tarih":"2022-09-16","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":236,"tarih":"2022-09-16","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":236,"tarih":"2022-09-16","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":236,"tarih":"2022-09-16","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":236,"tarih":"2022-09-16","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":236,"tarih":"2022-09-16","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":236,"tarih":"2022-09-16","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":237,"tarih":"2022-09-16","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":237,"tarih":"2022-09-16","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":237,"tarih":"2022-09-16","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":237,"tarih":"2022-09-16","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":237,"tarih":"2022-09-16","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":237,"tarih":"2022-09-16","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":237,"tarih":"2022-09-16","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":237,"tarih":"2022-09-16","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":243,"tarih":"2022-09-17","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":243,"tarih":"2022-09-17","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":243,"tarih":"2022-09-17","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":245,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":245,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":245,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":245,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":245,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":245,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":245,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":245,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":245,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":245,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":245,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":251,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":257,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":257,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":257,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":257,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":257,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":257,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":261,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":265,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":265,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":265,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":265,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":265,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":265,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":265,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":265,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":265,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":265,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":265,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":265,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":265,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":265,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":265,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":265,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":265,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":265,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":266,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":266,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":266,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":266,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":266,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":266,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":266,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":266,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":266,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":244,"tarih":"2022-09-17","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":244,"tarih":"2022-09-17","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":244,"tarih":"2022-09-17","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":244,"tarih":"2022-09-17","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":264,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":264,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":275,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":275,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":275,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":275,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":275,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":275,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":275,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":275,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":275,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-8","sira":275,"tarih":"2022-09-20","kaynak_depo":"Merkez depo"},{"seri":"ANT-9","sira":246,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-9","sira":246,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-9","sira":246,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-9","sira":252,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-9","sira":252,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-9","sira":252,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-9","sira":252,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-9","sira":252,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-9","sira":252,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"ANT-9","sira":252,"tarih":"2022-09-19","kaynak_depo":"Merkez depo"},{"seri":"LET-6","sira":217,"tarih":"2022-09-15","kaynak_depo":"LALELİ DEPO"},{"seri":"LET-7","sira":233,"tarih":"2022-09-15","kaynak_depo":"LALELİ DEPO"},{"seri":"LET-7","sira":233,"tarih":"2022-09-15","kaynak_depo":"LALELİ DEPO"},{"seri":"LET-7","sira":233,"tarih":"2022-09-15","kaynak_depo":"LALELİ DEPO"},{"seri":"LET-7","sira":233,"tarih":"2022-09-15","kaynak_depo":"LALELİ DEPO"},{"seri":"LET-7","sira":266,"tarih":"2022-09-20","kaynak_depo":"LALELİ DEPO"},{"seri":"LET-7","sira":266,"tarih":"2022-09-20","kaynak_depo":"LALELİ DEPO"},{"seri":"LET-7","sira":266,"tarih":"2022-09-20","kaynak_depo":"LALELİ DEPO"},{"seri":"LET-7","sira":266,"tarih":"2022-09-20","kaynak_depo":"LALELİ DEPO"},{"seri":"LET-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"LALELİ DEPO"},{"seri":"LET-7","sira":273,"tarih":"2022-09-20","kaynak_depo":"LALELİ DEPO"},{"seri":"LET-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"LALELİ DEPO"},{"seri":"LET-8","sira":260,"tarih":"2022-09-19","kaynak_depo":"LALELİ DEPO"},{"seri":"LET-8","sira":275,"tarih":"2022-09-20","kaynak_depo":"LALELİ DEPO"},{"seri":"siparis_d1","sira":215,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"siparis_d1","sira":215,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"siparis_d1","sira":215,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"siparis_d1","sira":215,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"},{"seri":"siparis_d1","sira":216,"tarih":"2022-09-15","kaynak_depo":"Merkez depo"}]
     * result_message : {"code":"0000","message":"İşlem Başarılı ","type":"S"}
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
         * seri : ANT-1
         * sira : 218
         * tarih : 2022-09-15
         * kaynak_depo : Merkez depo
         */

        private String seri;
        private int sira;
        private String tarih;
        private String kaynak_depo;
        private int adet;
        private int sayi;

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

        public String getKaynak_depo() {
            return kaynak_depo;
        }

        public void setKaynak_depo(String kaynak_depo) {
            this.kaynak_depo = kaynak_depo;
        }

        public int getAdet() {
            return adet;
        }

        public void setAdet(int adet) {
            this.adet = adet;
        }

        public int getSayi() {
            return sayi;
        }

        public void setSayi(int sayi) {
            this.sayi = sayi;
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
