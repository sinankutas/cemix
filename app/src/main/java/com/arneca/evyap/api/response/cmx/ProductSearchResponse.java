package com.arneca.evyap.api.response.cmx;/*
 * Created by Sinan KUTAS on 8/16/22.
 */

import java.util.List;


public class ProductSearchResponse {


    /**
     * result : [{"kod":"9213","ad":"11-15 FOSFOR GARNİLİ YANDAN FERMUARLI ERKEK KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":61.5,"beden_id":"152"},{"kod":"3015-3","ad":"11-15 YANDAN BAĞCIKLI KIZ GÖMLEK","pkadet":5,"birim":"AD.","fiyat":0,"beden_id":"700"},{"kod":"4252","ad":"11-15 YANDAN BAĞLAMALI KIZ T-SHİRT","pkadet":5,"birim":"AD.","fiyat":43,"beden_id":"472"},{"kod":"3009-3","ad":"11-15 YANDAN BÜZGÜLÜ KIZ GÖMLEK","pkadet":5,"birim":"AD.","fiyat":93,"beden_id":"668"},{"kod":"3214","ad":"11-15 YANDAN BÜZGÜLÜ T-SHİRT ŞORT KIZ TAKIM","pkadet":5,"birim":"AD.","fiyat":52.5,"beden_id":"238"},{"kod":"9210","ad":"11-15 YANDAN CEPLİ ERKEK KETEN KAPRİ","pkadet":5,"birim":"AD.","fiyat":40,"beden_id":"155"},{"kod":"9205","ad":"11-15 YANDAN CEPLİ KARGO ERKEK KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":51.5,"beden_id":"147"},{"kod":"3265","ad":"11-15 YANDAN CEPLİ KIZ KETEN ETEK","pkadet":5,"birim":"AD.","fiyat":90.5,"beden_id":"470"},{"kod":"2001-3","ad":"11-15 YANDAN CEPLİ KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":115.5,"beden_id":"639"},{"kod":"2008-3","ad":"11-15 YANDAN CEPLİ KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":124,"beden_id":"661"},{"kod":"3234","ad":"11-15 YANDAN CEPLİ KIZ TAKIM","pkadet":5,"birim":"AD.","fiyat":181.5,"beden_id":"443"},{"kod":"9214","ad":"11-15 YANDAN CEPLİ PENSLİ ERKEK KETEN PANTOLON","pkadet":5,"birim":"AD.","fiyat":38.5,"beden_id":"149"},{"kod":"3526","ad":"11-15 YANDAN CEPLİ PENSLİ KIZ KETEN PANTOLON","pkadet":5,"birim":"AD.","fiyat":32.5,"beden_id":"186"},{"kod":"3813","ad":"11-15 YANDAN ÇANTALI KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":115,"beden_id":"439"},{"kod":"3891","ad":"11-15 YANDAN ÇITÇITLI KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":108,"beden_id":"636"},{"kod":"3866","ad":"11-15 YANDAN DÜĞMELİ KIZ KETEN PANTOLON","pkadet":5,"birim":"AD.","fiyat":82,"beden_id":"530"},{"kod":"3864","ad":"11-15 YANDAN DÜĞMELİ KIZ KOT KAPRİ","pkadet":5,"birim":"AD.","fiyat":94.5,"beden_id":"515"},{"kod":"2012-3","ad":"11-15 YANDAN DÜĞMELİ KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":123.5,"beden_id":"655"},{"kod":"3820","ad":"11-15 YANDAN DÜĞMELİ KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":115,"beden_id":"438"},{"kod":"2032-3","ad":"11-15 YANDAN YIRTMAÇLI İSPANYOL PAÇA KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":0,"beden_id":"707"},{"kod":"3840","ad":"11-15 YANDAN YIRTMAÇLI KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":115,"beden_id":"580"},{"kod":"9013","ad":"2-6 FOSFOR GARNİLİ YANDAN FERMUARLI ERKEK KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":54.5,"beden_id":"152"},{"kod":"3015-1","ad":"2-6 YANDAN BAĞCIKLI KIZ GÖMLEK","pkadet":5,"birim":"AD.","fiyat":0,"beden_id":"700"},{"kod":"4082","ad":"2-6 YANDAN BAĞCIKLI KIZ T-SHİRT","pkadet":5,"birim":"AD.","fiyat":33.5,"beden_id":"527"},{"kod":"4052","ad":"2-6 YANDAN BAĞLAMALI KIZ T-SHİRT","pkadet":5,"birim":"AD.","fiyat":38,"beden_id":"472"},{"kod":"3009-1","ad":"2-6 YANDAN BÜZGÜLÜ KIZ GÖMLEK","pkadet":5,"birim":"AD.","fiyat":77.5,"beden_id":"668"},{"kod":"4083","ad":"2-6 YANDAN BÜZGÜLÜ KIZ T-SHİRT","pkadet":5,"birim":"AD.","fiyat":33.5,"beden_id":"528"},{"kod":"3014","ad":"2-6 YANDAN BÜZGÜLÜ T-SHİRT ŞORT KIZ TAKIM","pkadet":5,"birim":"AD.","fiyat":42.5,"beden_id":"238"},{"kod":"9010","ad":"2-6 YANDAN CEPLİ ERKEK KETEN KAPRİ","pkadet":5,"birim":"AD.","fiyat":35,"beden_id":"155"},{"kod":"9005","ad":"2-6 YANDAN CEPLİ KARGO ERKEK KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":44.5,"beden_id":"147"},{"kod":"3065","ad":"2-6 YANDAN CEPLİ KIZ KETEN ETEK","pkadet":5,"birim":"AD.","fiyat":76,"beden_id":"470"},{"kod":"3609","ad":"2-6 YANDAN CEPLİ KIZ KETEN PANTOLON","pkadet":5,"birim":"AD.","fiyat":72,"beden_id":"498"},{"kod":"2001-1","ad":"2-6 YANDAN CEPLİ KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":84,"beden_id":"639"},{"kod":"2008-1","ad":"2-6 YANDAN CEPLİ KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":99,"beden_id":"661"},{"kod":"9014","ad":"2-6 YANDAN CEPLİ PENSLİ ERKEK KETEN PANTOLON","pkadet":5,"birim":"AD.","fiyat":31.5,"beden_id":"149"},{"kod":"3326","ad":"2-6 YANDAN CEPLİ PENSLİ KIZ KETEN PANTOLON","pkadet":5,"birim":"AD.","fiyat":25.5,"beden_id":"186"},{"kod":"3613","ad":"2-6 YANDAN ÇANTALI KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":93,"beden_id":"439"},{"kod":"3691","ad":"2-6 YANDAN ÇITÇITLI KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":81,"beden_id":"636"},{"kod":"3659","ad":"2-6 YANDAN DÜĞMELİ KIZ ELBİSE","pkadet":5,"birim":"AD.","fiyat":49,"beden_id":"136"},{"kod":"3666","ad":"2-6 YANDAN DÜĞMELİ KIZ KETEN PANTOLON","pkadet":5,"birim":"AD.","fiyat":64.5,"beden_id":"530"},{"kod":"3664","ad":"2-6 YANDAN DÜĞMELİ KIZ KOT KAPRİ","pkadet":5,"birim":"AD.","fiyat":72.5,"beden_id":"515"},{"kod":"2012-1","ad":"2-6 YANDAN DÜĞMELİ KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":99,"beden_id":"670"},{"kod":"2032-1","ad":"2-6 YANDAN YIRTMAÇLI İSPANYOL PAÇA KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":0,"beden_id":"707"},{"kod":"3640","ad":"2-6 YANDAN YIRTMAÇLI KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":93,"beden_id":"580"},{"kod":"9113","ad":"7-11 FOSFOR GARNİLİ YANDAN FERMUARLI ERKEK KOT PANTOLON ","pkadet":5,"birim":"AD.","fiyat":58,"beden_id":"152"},{"kod":"3015-2","ad":"7-11 YANDAN BAĞCIKLI KIZ GÖMLEK","pkadet":5,"birim":"AD.","fiyat":0,"beden_id":"700"},{"kod":"4182","ad":"7-11 YANDAN BAĞCIKLI KIZ T-SHİRT","pkadet":5,"birim":"AD.","fiyat":38,"beden_id":"527"},{"kod":"4152","ad":"7-11 YANDAN BAĞLAMALI KIZ T-SHİRT","pkadet":5,"birim":"AD.","fiyat":38,"beden_id":"472"},{"kod":"3009-2","ad":"7-11 YANDAN BÜZGÜLÜ KIZ GÖMLEK","pkadet":5,"birim":"AD.","fiyat":85.5,"beden_id":"668"},{"kod":"4183","ad":"7-11 YANDAN BÜZGÜLÜ KIZ T-SHİRT","pkadet":5,"birim":"AD.","fiyat":38,"beden_id":"528"},{"kod":"3114","ad":"7-11 YANDAN BÜZGÜLÜ T-SHİRT ŞORT KIZ TAKIM","pkadet":5,"birim":"AD.","fiyat":46.5,"beden_id":"238"},{"kod":"9110","ad":"7-11 YANDAN CEPLİ ERKEK KETEN KAPRİ","pkadet":5,"birim":"AD.","fiyat":43,"beden_id":"155"},{"kod":"9105","ad":"7-11 YANDAN CEPLİ KARGO ERKEK KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":47.5,"beden_id":"147"},{"kod":"3165","ad":"7-11 YANDAN CEPLİ KIZ KETEN ETEK","pkadet":5,"birim":"AD.","fiyat":83.5,"beden_id":"470"},{"kod":"3709","ad":"7-11 YANDAN CEPLİ KIZ KETEN PANTOLON","pkadet":5,"birim":"AD.","fiyat":83.5,"beden_id":"498"},{"kod":"2001-2","ad":"7-11 YANDAN CEPLİ KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":95.5,"beden_id":"639"},{"kod":"2008-2","ad":"7-11 YANDAN CEPLİ KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":108.5,"beden_id":"661"},{"kod":"3134","ad":"7-11 YANDAN CEPLİ KIZ TAKIM","pkadet":5,"birim":"AD.","fiyat":167,"beden_id":"443"},{"kod":"9114","ad":"7-11 YANDAN CEPLİ PENSLİ ERKEK KETEN PANTOLON","pkadet":5,"birim":"AD.","fiyat":34.5,"beden_id":"149"},{"kod":"3426","ad":"7-11 YANDAN CEPLİ PENSLİ KIZ KETEN PANTOLON","pkadet":5,"birim":"AD.","fiyat":28.5,"beden_id":"186"},{"kod":"3713","ad":"7-11 YANDAN ÇANTALI KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":107.5,"beden_id":"439"},{"kod":"3791","ad":"7-11 YANDAN ÇITÇITLI KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":94.5,"beden_id":"636"},{"kod":"3759","ad":"7-11 YANDAN DÜĞMELİ KIZ ELBİSE","pkadet":5,"birim":"AD.","fiyat":53,"beden_id":"136"},{"kod":"3766","ad":"7-11 YANDAN DÜĞMELİ KIZ KETEN PANTOLON","pkadet":5,"birim":"AD.","fiyat":72,"beden_id":"530"},{"kod":"3764","ad":"7-11 YANDAN DÜĞMELİ KIZ KOT KAPRİ","pkadet":5,"birim":"AD.","fiyat":83.5,"beden_id":"515"},{"kod":"2012-2","ad":"7-11 YANDAN DÜĞMELİ KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":111,"beden_id":"655"},{"kod":"3720","ad":"7-11 YANDAN DÜĞMELİ KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":103.5,"beden_id":"438"},{"kod":"2032-2","ad":"7-11 YANDAN YIRTMAÇLI İSPANYOL PAÇA KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":0,"beden_id":"707"},{"kod":"3740","ad":"7-11 YANDAN YIRTMAÇLI KIZ KOT PANTOLON","pkadet":5,"birim":"AD.","fiyat":103.5,"beden_id":"580"}]
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
         * kod : 9213
         * ad : 11-15 FOSFOR GARNİLİ YANDAN FERMUARLI ERKEK KOT PANTOLON
         * pkadet : 5
         * birim : AD.
         * fiyat : 61.5
         * beden_id : 152
         */

        private String kod;
        private String ad;
        private int pkadet;
        private String birim;
        private double fiyat;
        private String beden_id;

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

        public int getPkadet() {
            return pkadet;
        }

        public void setPkadet(int pkadet) {
            this.pkadet = pkadet;
        }

        public String getBirim() {
            return birim;
        }

        public void setBirim(String birim) {
            this.birim = birim;
        }

        public double getFiyat() {
            return fiyat;
        }

        public void setFiyat(double fiyat) {
            this.fiyat = fiyat;
        }

        public String getBeden_id() {
            return beden_id;
        }

        public void setBeden_id(String beden_id) {
            this.beden_id = beden_id;
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
