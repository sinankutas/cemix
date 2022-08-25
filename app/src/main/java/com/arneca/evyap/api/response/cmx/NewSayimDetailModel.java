package com.arneca.evyap.api.response.cmx;/*
 * Created by Sinan KUTAS on 8/25/22.
 */

public class NewSayimDetailModel {

    private String sayimId;
    private String stokKod;
    private String renkId;
    private String aramaMetni;
    private String subeId;
    private String kullanıcıId;
    private String miktar;
    private String cihaz;
    private String stokAd;

    public String getSayimId() {
        return sayimId;
    }

    public void setSayimId(String sayimId) {
        this.sayimId = sayimId;
    }

    public String getStokKod() {
        return stokKod;
    }

    public void setStokKod(String stokKod) {
        this.stokKod = stokKod;
    }

    public String getRenkId() {
        return renkId;
    }

    public void setRenkId(String renkId) {
        this.renkId = renkId;
    }

    public String getAramaMetni() {
        return aramaMetni;
    }

    public void setAramaMetni(String aramaMetni) {
        this.aramaMetni = aramaMetni;
    }

    public String getSubeId() {
        return subeId;
    }

    public void setSubeId(String subeId) {
        this.subeId = subeId;
    }

    public String getKullanıcıId() {
        return kullanıcıId;
    }

    public void setKullanıcıId(String kullanıcıId) {
        this.kullanıcıId = kullanıcıId;
    }

    public String getMiktar() {
        return miktar;
    }

    public void setMiktar(String miktar) {
        this.miktar = miktar;
    }

    public String getCihaz() {
        return cihaz;
    }

    public void setCihaz(String cihaz) {
        this.cihaz = cihaz;
    }

    public String getStokAd() {
        return stokAd;
    }

    public void setStokAd(String stokAd) {
        this.stokAd = stokAd;
    }
}
