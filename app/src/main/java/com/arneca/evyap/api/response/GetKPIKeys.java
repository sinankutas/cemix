package com.arneca.evyap.api.response;/*
 * Created by Sinan KUTAS on 22.02.2021.
 */

import java.util.List;

public class GetKPIKeys {


    /**
     * data : [{"name":"Önceki Fire Miktarı","id":"8"},{"name":"Çalışılan Ürün","id":"5"},{"name":"Mevcut Duruş","id":"7"},{"name":"Önceki Vardiya Üretim","id":"6"},{"name":"Anlık Net Üretim","id":"4"},{"name":"Vardiya OEE","id":"1"},{"name":"Duruş Süresi","id":"3"},{"name":"Anlık Makine Firesi","id":"2"}]
     * response : true
     * status : Respose with data
     */

    private boolean response;
    private String status;
    private List<DataBean> data;

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : Önceki Fire Miktarı
         * id : 8
         */

        private String name;
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
