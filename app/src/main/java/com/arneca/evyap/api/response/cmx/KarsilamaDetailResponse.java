package com.arneca.evyap.api.response.cmx;/*
 * Created by Sinan Kutas on 9/20/22.
 */

import java.util.List;

public class KarsilamaDetailResponse {


    /**
     * result : [{"sth_uuid":"2A48E6D6-7E3C-46D4-86CA-81AFEC076339","har_uuid":"8F37D604-7957-4DFE-B295-1D33934BAE37","stok_kodu":"1068-2","stok_adi":"7-11 ÜÇ İP GARNİLİ KIZ MONT","renk":"KAHVE","istenen_miktar":5},{"sth_uuid":"B311BA81-4A61-46C2-95A5-3E531CA70158","har_uuid":"0AC7F9E3-FD53-4EED-9D2C-DFFE355B95A5","stok_kodu":"1068-2","stok_adi":"7-11 ÜÇ İP GARNİLİ KIZ MONT","renk":"KAHVE","istenen_miktar":5},{"sth_uuid":"CD88CE44-A476-4969-BC33-01949FC39C18","har_uuid":"B4A9A653-A022-49A7-9E08-DC125B99E553","stok_kodu":"1068-3","stok_adi":"11-15 ÜÇ İP GARNİLİ KIZ MONT","renk":"KAHVE","istenen_miktar":5},{"sth_uuid":"647A2BE5-46DA-48D4-ACFD-CAB28F28E937","har_uuid":"E00FE881-4277-4D1E-B3DD-F1C477A46E58","stok_kodu":"1068-3","stok_adi":"11-15 ÜÇ İP GARNİLİ KIZ MONT","renk":"KAHVE","istenen_miktar":5},{"sth_uuid":"647A2BE5-46DA-48D4-ACFD-CAB28F28E937","har_uuid":"C471B45B-0686-46FD-A27A-FE9FB616C84D","stok_kodu":"1068-3","stok_adi":"11-15 ÜÇ İP GARNİLİ KIZ MONT","renk":"KAHVE","istenen_miktar":5},{"sth_uuid":"CD88CE44-A476-4969-BC33-01949FC39C18","har_uuid":"914F3930-967D-4207-A38E-6654BA249FE4","stok_kodu":"1068-3","stok_adi":"11-15 ÜÇ İP GARNİLİ KIZ MONT","renk":"KAHVE","istenen_miktar":5},{"sth_uuid":"57DD7FEC-C9B5-4601-B3C9-E22847E8CD45","har_uuid":"222FFFD5-7097-42A8-B5BB-8197E014D3B2","stok_kodu":"2017-2","stok_adi":"7-11 KAPAKLI İSP.PAÇA KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"115A2A56-E3D4-44E4-A584-B9C4B9090FC1","har_uuid":"78906BC5-A77E-4BF4-8721-56B55D770B99","stok_kodu":"2017-2","stok_adi":"7-11 KAPAKLI İSP.PAÇA KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"57DD7FEC-C9B5-4601-B3C9-E22847E8CD45","har_uuid":"03452A06-68E4-4007-8490-F0F296FF63ED","stok_kodu":"2017-2","stok_adi":"7-11 KAPAKLI İSP.PAÇA KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"115A2A56-E3D4-44E4-A584-B9C4B9090FC1","har_uuid":"95B20038-B0B3-4312-B836-E4AF5D1F34F0","stok_kodu":"2017-2","stok_adi":"7-11 KAPAKLI İSP.PAÇA KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"822F584D-842D-425C-8580-D60C84BD60F7","har_uuid":"40062A3D-E392-434B-A8D5-D01D370D0E8A","stok_kodu":"2017-3","stok_adi":"11-15 KAPAKLI İSP.PAÇA KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"60A685B4-EAAC-466F-B5DC-3816E62C5E91","har_uuid":"9E6EAC6B-C690-4958-AC5D-F5407C3AA698","stok_kodu":"2017-3","stok_adi":"11-15 KAPAKLI İSP.PAÇA KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"60A685B4-EAAC-466F-B5DC-3816E62C5E91","har_uuid":"8EA85116-40F2-4DFD-BBA4-FCDC0FA629DD","stok_kodu":"2017-3","stok_adi":"11-15 KAPAKLI İSP.PAÇA KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"822F584D-842D-425C-8580-D60C84BD60F7","har_uuid":"83BB1E40-0D2E-4797-A0ED-77485D5ADACF","stok_kodu":"2017-3","stok_adi":"11-15 KAPAKLI İSP.PAÇA KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"012F343E-0621-41D3-A9C5-8418B88A18DC","har_uuid":"BDB65470-5E3D-4840-9077-8401E5EB084A","stok_kodu":"2018-3","stok_adi":"11-15 ÖNDEN PATLI KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"538D00AB-45C6-4D04-A799-3AAC150CB949","har_uuid":"FD93F259-A54E-4E26-8F44-9F7625AA7669","stok_kodu":"2018-3","stok_adi":"11-15 ÖNDEN PATLI KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"538D00AB-45C6-4D04-A799-3AAC150CB949","har_uuid":"BA5C55F8-E5F9-4F59-99BB-F523A684188A","stok_kodu":"2018-3","stok_adi":"11-15 ÖNDEN PATLI KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"012F343E-0621-41D3-A9C5-8418B88A18DC","har_uuid":"ECA250D9-6223-4EC5-B57D-E115B4DDA3E3","stok_kodu":"2018-3","stok_adi":"11-15 ÖNDEN PATLI KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"F33EFD17-97C9-450C-98A7-C609CF4C6795","har_uuid":"2DEF04CE-3C4D-4B03-BE85-D804086103B0","stok_kodu":"2020-2","stok_adi":"7-11 ÖN ÜST DİKİŞLİ İSPANYOL PAÇA KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"7FBE1893-B83A-4917-A58D-9D9A54EEECB6","har_uuid":"F383FBCF-5B5E-4D61-9D12-8300481AD5BB","stok_kodu":"2020-2","stok_adi":"7-11 ÖN ÜST DİKİŞLİ İSPANYOL PAÇA KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"F33EFD17-97C9-450C-98A7-C609CF4C6795","har_uuid":"B72D7A5B-24A4-4FFE-9889-3D94ADB5B573","stok_kodu":"2020-2","stok_adi":"7-11 ÖN ÜST DİKİŞLİ İSPANYOL PAÇA KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"7FBE1893-B83A-4917-A58D-9D9A54EEECB6","har_uuid":"801AFA1F-E83D-4DEB-B4D4-059E4790DEE0","stok_kodu":"2020-2","stok_adi":"7-11 ÖN ÜST DİKİŞLİ İSPANYOL PAÇA KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"A4A0461A-E9B0-4FA1-9056-A5E53AEAA4D6","har_uuid":"B32E3066-1924-4446-A420-1B8761E7320A","stok_kodu":"2020-3","stok_adi":"11-15 ÖN ÜST DİKİŞLİ İSPANYOL PAÇA KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"FA78630A-7404-491E-B42A-DE7B8E015641","har_uuid":"B9D231FF-6F33-415E-AAB9-7B1ACCEE7BF3","stok_kodu":"2020-3","stok_adi":"11-15 ÖN ÜST DİKİŞLİ İSPANYOL PAÇA KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"B0A3FB33-631F-46EA-8459-E87978F86C34","har_uuid":"DF8AF1CA-496E-413F-B1B1-8FF43F440FF4","stok_kodu":"3712","stok_adi":"7-11 KUŞAKLI KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"B0A3FB33-631F-46EA-8459-E87978F86C34","har_uuid":"6734F59B-5ABD-4902-AAF5-5B4F103B84EF","stok_kodu":"3712","stok_adi":"7-11 KUŞAKLI KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"51750497-8030-404F-BABE-129B2DB7382A","har_uuid":"75AD1783-21D9-4749-9631-6469AC687F1B","stok_kodu":"3712","stok_adi":"7-11 KUŞAKLI KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"51750497-8030-404F-BABE-129B2DB7382A","har_uuid":"303999E4-40AA-47A4-9488-EF62603E9236","stok_kodu":"3712","stok_adi":"7-11 KUŞAKLI KIZ KOT PANTOLON","renk":"AÇIK MAVİ","istenen_miktar":10},{"sth_uuid":"8DDEB28B-40C2-4687-ACF3-1C3CF16213BB","har_uuid":"7A830A70-2EBC-4DB9-8358-C0D540B47789","stok_kodu":"3777","stok_adi":"7-11 ÇIÇITLI KIZ KOT PANTOLON","renk":"MAVİ","istenen_miktar":10},{"sth_uuid":"48085AD1-6953-44B0-9FDD-521C230DFBF9","har_uuid":"594B241B-25EE-46E9-851C-9DD962C6B02E","stok_kodu":"3777","stok_adi":"7-11 ÇIÇITLI KIZ KOT PANTOLON","renk":"MAVİ","istenen_miktar":10},{"sth_uuid":"FFCD4AE1-42DB-474C-A2B1-F0E61CCBF02B","har_uuid":"94DB512A-5757-40F0-9870-73E6F7F45067","stok_kodu":"3793","stok_adi":"7-11 BEL ALTI KORDONLU KIZ KOT PANTOLON","renk":"MAVİ","istenen_miktar":10},{"sth_uuid":"C356B3AD-A241-4A6D-BCF8-9B5C936551F7","har_uuid":"9F4E5BE9-EAFE-47EE-BEE0-3A921F1024EB","stok_kodu":"3793","stok_adi":"7-11 BEL ALTI KORDONLU KIZ KOT PANTOLON","renk":"MAVİ","istenen_miktar":10},{"sth_uuid":"FFCD4AE1-42DB-474C-A2B1-F0E61CCBF02B","har_uuid":"7E8F9478-1258-4094-A4E0-256764FF3621","stok_kodu":"3793","stok_adi":"7-11 BEL ALTI KORDONLU KIZ KOT PANTOLON","renk":"MAVİ","istenen_miktar":10},{"sth_uuid":"C356B3AD-A241-4A6D-BCF8-9B5C936551F7","har_uuid":"6A8D004E-CA00-406A-9BC9-D829B03E5479","stok_kodu":"3793","stok_adi":"7-11 BEL ALTI KORDONLU KIZ KOT PANTOLON","renk":"MAVİ","istenen_miktar":10},{"sth_uuid":"F14298DA-F11B-40F0-A761-B51C6E671422","har_uuid":"097AA777-D013-4030-8D3A-3F5F38F85665","stok_kodu":"3877","stok_adi":"11-15 ÇITÇITLI KIZ KOT PANTOLON","renk":"MAVİ","istenen_miktar":10},{"sth_uuid":"E507DD0B-05B7-45AD-B739-80E81BF33180","har_uuid":"6248D828-00E7-461F-A2AF-71281D713854","stok_kodu":"3877","stok_adi":"11-15 ÇITÇITLI KIZ KOT PANTOLON","renk":"MAVİ","istenen_miktar":10},{"sth_uuid":"0EE4DC49-33C9-4F79-BB09-17D69B31D6DD","har_uuid":"E693FD50-5AB8-4945-9EB7-9919B37DF1A4","stok_kodu":"4004-2","stok_adi":"7-11 ARTISTIC BASKILI KIZ ÜÇ İPLİK TAKIM","renk":"SİYAH","istenen_miktar":5},{"sth_uuid":"A354DD6B-3215-46B4-ABF0-77613BA26574","har_uuid":"3481011E-8A87-43E5-B619-ECE5345FAA9A","stok_kodu":"4004-2","stok_adi":"7-11 ARTISTIC BASKILI KIZ ÜÇ İPLİK TAKIM","renk":"SİYAH","istenen_miktar":5},{"sth_uuid":"6F3CDB45-B1D0-4142-AB35-D52F85D58B70","har_uuid":"5B0E9C9C-61D9-4238-B9F6-C7C6A004BEA7","stok_kodu":"4004-3","stok_adi":"11-15 ARTISTIC BASKILI KIZ ÜÇ İPLİK TAKIM","renk":"SİYAH","istenen_miktar":5},{"sth_uuid":"9C83CF40-AF43-4277-9C9F-97A5ADE291BC","har_uuid":"71CDC91A-21C4-4825-B54B-0E762908FFD9","stok_kodu":"4004-3","stok_adi":"11-15 ARTISTIC BASKILI KIZ ÜÇ İPLİK TAKIM","renk":"SİYAH","istenen_miktar":5},{"sth_uuid":"7AF055D2-CBC8-4CA3-BEFF-404BE8580237","har_uuid":"4F995A3C-88A7-426E-8EB2-2D130EA74FFC","stok_kodu":"4008-3","stok_adi":"11-15 KAPİTONE DETAYLI KIZ ÜÇ İPLİK TAKIM","renk":"BEJ","istenen_miktar":5},{"sth_uuid":"78E24596-1A4B-475C-A549-192D2AC838E9","har_uuid":"628A5F0A-4DD5-41B4-B92D-C3734D0CFE1B","stok_kodu":"4008-3","stok_adi":"11-15 KAPİTONE DETAYLI KIZ ÜÇ İPLİK TAKIM","renk":"BEJ","istenen_miktar":5},{"sth_uuid":"0384E965-CF20-4F06-8FB5-0DFA8C537C8A","har_uuid":"F92B74CE-F43C-4DBE-8EC3-6CD9938B35E7","stok_kodu":"4512-3","stok_adi":"11-15 KAPİTONE DETAYLI KIZ MONT","renk":"MOR","istenen_miktar":5},{"sth_uuid":"5AB84FAA-CDA9-47BA-8990-E444E5BF18D6","har_uuid":"513B5918-2633-4606-975B-1AE296562F6B","stok_kodu":"4512-3","stok_adi":"11-15 KAPİTONE DETAYLI KIZ MONT","renk":"MOR","istenen_miktar":5},{"sth_uuid":"0384E965-CF20-4F06-8FB5-0DFA8C537C8A","har_uuid":"99BD69F9-1844-438D-A623-6643ACF0E945","stok_kodu":"4512-3","stok_adi":"11-15 KAPİTONE DETAYLI KIZ MONT","renk":"MOR","istenen_miktar":5},{"sth_uuid":"5AB84FAA-CDA9-47BA-8990-E444E5BF18D6","har_uuid":"3DC1C469-F274-4F1C-9FD2-62C40137B59F","stok_kodu":"4512-3","stok_adi":"11-15 KAPİTONE DETAYLI KIZ MONT","renk":"MOR","istenen_miktar":5},{"sth_uuid":"B97F6B91-A9FE-44A0-819C-13FB74E9236F","har_uuid":"EEB2A299-FE74-4B9D-BB13-081AE266A993","stok_kodu":"4513-1","stok_adi":"2-6 YANLARI KAPİTONELİ ÜÇ İPLİK KIZ SWEATSHİRT","renk":"SİYAH","istenen_miktar":5},{"sth_uuid":"B97F6B91-A9FE-44A0-819C-13FB74E9236F","har_uuid":"E7C808F7-0864-433E-A2C9-2221E4D8BAD1","stok_kodu":"4513-1","stok_adi":"2-6 YANLARI KAPİTONELİ ÜÇ İPLİK KIZ SWEATSHİRT","renk":"SİYAH","istenen_miktar":5},{"sth_uuid":"142E8D8C-CD1D-4336-B39C-6F3788671B45","har_uuid":"8415F02B-8533-4970-AB36-8C8B00B4D167","stok_kodu":"4513-1","stok_adi":"2-6 YANLARI KAPİTONELİ ÜÇ İPLİK KIZ SWEATSHİRT","renk":"SİYAH","istenen_miktar":5},{"sth_uuid":"142E8D8C-CD1D-4336-B39C-6F3788671B45","har_uuid":"C9FC9C8D-E6E6-4901-A24C-F41F6D15C4CD","stok_kodu":"4513-1","stok_adi":"2-6 YANLARI KAPİTONELİ ÜÇ İPLİK KIZ SWEATSHİRT","renk":"SİYAH","istenen_miktar":5}]
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
         * sth_uuid : 2A48E6D6-7E3C-46D4-86CA-81AFEC076339
         * har_uuid : 8F37D604-7957-4DFE-B295-1D33934BAE37
         * stok_kodu : 1068-2
         * stok_adi : 7-11 ÜÇ İP GARNİLİ KIZ MONT
         * renk : KAHVE
         * istenen_miktar : 5
         */

        private String sth_uuid;
        private String har_uuid;
        private String stok_kodu;
        private String stok_adi;
        private String renk;
        private int istenen_miktar;
        private int stock;

        public String getSth_uuid() {
            return sth_uuid;
        }

        public void setSth_uuid(String sth_uuid) {
            this.sth_uuid = sth_uuid;
        }

        public String getHar_uuid() {
            return har_uuid;
        }

        public void setHar_uuid(String har_uuid) {
            this.har_uuid = har_uuid;
        }

        public String getStok_kodu() {
            return stok_kodu;
        }

        public void setStok_kodu(String stok_kodu) {
            this.stok_kodu = stok_kodu;
        }

        public String getStok_adi() {
            return stok_adi;
        }

        public void setStok_adi(String stok_adi) {
            this.stok_adi = stok_adi;
        }

        public String getRenk() {
            return renk;
        }

        public void setRenk(String renk) {
            this.renk = renk;
        }

        public int getIstenen_miktar() {
            return istenen_miktar;
        }

        public void setIstenen_miktar(int istenen_miktar) {
            this.istenen_miktar = istenen_miktar;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
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
