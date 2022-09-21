package com.arneca.evyap.api.response.cmx;/*
 * Created by Sinan Kutas on 9/21/22.
 */

public class KarsilamaModel {
   private String sth_uuid;
    private String har_uuid;
    private int verilen_miktar;

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

    public int getVerilen_miktar() {
        return verilen_miktar;
    }

    public void setVerilen_miktar(int verilen_miktar) {
        this.verilen_miktar = verilen_miktar;
    }
}
