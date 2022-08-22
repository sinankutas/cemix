package com.arneca.evyap.api.response.cmx;

import java.util.List;

public class TanimlarResponse {
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
         * id : 18878
         * kod : 6187
         * ad : 8-12 BELİ VE PAÇA LASTİKLİ RENKLİ KIZ KOT PANTOLON
         * anagrup_kod : 8-12 KIZ KOT PANTOLON
         * beden :
         * beden_kodu :
         * renk :
         * renk_id : 0
         * pkadet : 5
         * dvz : USD
         * satis_fiyat : 0
         * d1 : 0
         * d14 : 0
         * d89 : 0
         * src : 6187_8-12 BELI VE PACA LASTIKLI RENKLI KIZ KOT PANTOLON_8681150061879
         */

        private int id;
        private String kod;
        private String ad;
        private String anagrup_kod;
        private String beden;
        private String beden_kodu;
        private String renk;
        private int renk_id;
        private String pkadet;
        private String dvz;
        private String satis_fiyat;
        private String d1;
        private String d14;
        private String d89;
        private String src;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getAnagrup_kod() {
            return anagrup_kod;
        }

        public void setAnagrup_kod(String anagrup_kod) {
            this.anagrup_kod = anagrup_kod;
        }

        public String getBeden() {
            return beden;
        }

        public void setBeden(String beden) {
            this.beden = beden;
        }

        public String getBeden_kodu() {
            return beden_kodu;
        }

        public void setBeden_kodu(String beden_kodu) {
            this.beden_kodu = beden_kodu;
        }

        public String getRenk() {
            return renk;
        }

        public void setRenk(String renk) {
            this.renk = renk;
        }

        public int getRenk_id() {
            return renk_id;
        }

        public void setRenk_id(int renk_id) {
            this.renk_id = renk_id;
        }

        public String getPkadet() {
            return pkadet;
        }

        public void setPkadet(String pkadet) {
            this.pkadet = pkadet;
        }

        public String getDvz() {
            return dvz;
        }

        public void setDvz(String dvz) {
            this.dvz = dvz;
        }

        public String getSatis_fiyat() {
            return satis_fiyat;
        }

        public void setSatis_fiyat(String satis_fiyat) {
            this.satis_fiyat = satis_fiyat;
        }

        public String getD1() {
            return d1;
        }

        public void setD1(String d1) {
            this.d1 = d1;
        }

        public String getD14() {
            return d14;
        }

        public void setD14(String d14) {
            this.d14 = d14;
        }

        public String getD89() {
            return d89;
        }

        public void setD89(String d89) {
            this.d89 = d89;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
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
