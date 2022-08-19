package com.arneca.evyap.api.response.cmx;/*
 * Created by  on 8/16/22.
 */

import java.util.List;


public class RBMatrisResponse {


    /**
     * result : [{"kod":"9013","ad":"2-6 FOSFOR GARNİLİ YANDAN FERMUARLI ERKEK KOT PANTOLON","ana_grup":"2-6 ERKEK KOT PANTOLON","bdn_ismi":"2-6 YAŞ","fiyat":54.5,"RenkDetay":[{"id":"9013_1_1_2-6_152","rnk_kirilim_id":1,"rnk_kirilim":"MAVİ","d1":0,"d14":0,"d89":0},{"id":"9013_1_2_2-6_152","rnk_kirilim_id":2,"rnk_kirilim":"SİYAH","d1":0,"d14":0,"d89":0}]},{"kod":"9113","ad":"7-11 FOSFOR GARNİLİ YANDAN FERMUARLI ERKEK KOT PANTOLON ","ana_grup":"7-11 ERKEK KOT PANTOLON","bdn_ismi":"7-11 YAŞ","fiyat":58,"RenkDetay":[{"id":"9113_1_1_7-11_152","rnk_kirilim_id":1,"rnk_kirilim":"MAVİ","d1":0,"d14":0,"d89":0},{"id":"9113_1_2_7-11_152","rnk_kirilim_id":2,"rnk_kirilim":"SİYAH","d1":0,"d14":0,"d89":0}]},{"kod":"9213","ad":"11-15 FOSFOR GARNİLİ YANDAN FERMUARLI ERKEK KOT PANTOLON","ana_grup":"11-15 ERKEK KOT PANTOLON","bdn_ismi":"11-15 YAŞ","fiyat":61.5,"RenkDetay":[{"id":"9213_1_1_11-15_152","rnk_kirilim_id":1,"rnk_kirilim":"MAVİ","d1":0,"d14":0,"d89":0},{"id":"9213_1_2_11-15_152","rnk_kirilim_id":2,"rnk_kirilim":"SİYAH","d1":0,"d14":0,"d89":0}]}]
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
         * kod : 9013
         * ad : 2-6 FOSFOR GARNİLİ YANDAN FERMUARLI ERKEK KOT PANTOLON
         * ana_grup : 2-6 ERKEK KOT PANTOLON
         * bdn_ismi : 2-6 YAŞ
         * fiyat : 54.5
         * RenkDetay : [{"id":"9013_1_1_2-6_152","rnk_kirilim_id":1,"rnk_kirilim":"MAVİ","d1":0,"d14":0,"d89":0},{"id":"9013_1_2_2-6_152","rnk_kirilim_id":2,"rnk_kirilim":"SİYAH","d1":0,"d14":0,"d89":0}]
         */

        private String kod;
        private String ad;
        private String ana_grup;
        private String bdn_ismi;
        private double fiyat;
        private List<RenkDetayBean> RenkDetay;


        public static class RenkDetayBean {
            /**
             * id : 9013_1_1_2-6_152
             * rnk_kirilim_id : 1
             * rnk_kirilim : MAVİ
             * d1 : 0
             * d14 : 0
             * d89 : 0
             */

            private String id;
            private int rnk_kirilim_id;
            private String rnk_kirilim;
            private int d1;
            private int d14;
            private int d89;
            private int stock;
            private String renk;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getRnk_kirilim_id() {
                return rnk_kirilim_id;
            }

            public void setRnk_kirilim_id(int rnk_kirilim_id) {
                this.rnk_kirilim_id = rnk_kirilim_id;
            }

            public String getRnk_kirilim() {
                return rnk_kirilim;
            }

            public void setRnk_kirilim(String rnk_kirilim) {
                this.rnk_kirilim = rnk_kirilim;
            }

            public int getD1() {
                return d1;
            }

            public void setD1(int d1) {
                this.d1 = d1;
            }

            public int getD14() {
                return d14;
            }

            public void setD14(int d14) {
                this.d14 = d14;
            }

            public int getD89() {
                return d89;
            }

            public void setD89(int d89) {
                this.d89 = d89;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public String getRenk() {
                return renk;
            }

            public void setRenk(String renk) {
                this.renk = renk;
            }
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

        public String getAna_grup() {
            return ana_grup;
        }

        public void setAna_grup(String ana_grup) {
            this.ana_grup = ana_grup;
        }

        public String getBdn_ismi() {
            return bdn_ismi;
        }

        public void setBdn_ismi(String bdn_ismi) {
            this.bdn_ismi = bdn_ismi;
        }

        public double getFiyat() {
            return fiyat;
        }

        public void setFiyat(double fiyat) {
            this.fiyat = fiyat;
        }

        public List<RenkDetayBean> getRenkDetay() {
            return RenkDetay;
        }

        public void setRenkDetay(List<RenkDetayBean> renkDetay) {
            RenkDetay = renkDetay;
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
