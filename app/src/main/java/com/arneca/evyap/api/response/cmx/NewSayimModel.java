package com.arneca.evyap.api.response.cmx;/*
 * Created by Sinan KUTAS on 8/24/22.
 */

public class NewSayimModel {
    Integer id;
    String idx;
    String desc;
    String subeCode;
    String tarih;
    String cihaz;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSubeCode() {
        return subeCode;
    }

    public void setSubeCode(String subeCode) {
        this.subeCode = subeCode;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getCihaz() {
        return cihaz;
    }

    public void setCihaz(String cihaz) {
        this.cihaz = cihaz;
    }
}
