package com.arneca.evyap.api.response;/*
 * Created by Sinan KUTAS on 22.02.2021.
 */

import java.util.List;


public class GetAllLineInfoByLine {


    /**
     * data : [{"LineData":[{"name":"Önceki Fire Miktarı","value":0},{"name":"Çalışılan Ürün","value":"ACTIVEX HİJYEN SPRY KUMAS&YUZEY 400ML*12"},{"name":"Mevcut Duruş","value":"PlanlıDuruş"},{"name":"Önceki Vardiya Üretim","value":0},{"name":"Hat Adı","value":"A1"},{"name":"Vardiya OEE","value":"0.0"},{"name":"Anlık Net Üretim","value":"0.0"},{"name":"Duruş Süresi","value":"129:30:01"},{"name":"Anlık Makine Firesi","value":0}],"LineName":"A1"},{"LineData":[{"name":"Önceki Fire Miktarı","value":0},{"name":"Çalışılan Ürün","value":"ARKO MEN COFFEE JEL 200 ML*24"},{"name":"Mevcut Duruş","value":""},{"name":"Önceki Vardiya Üretim","value":0},{"name":"Hat Adı","value":"A2"},{"name":"Vardiya OEE","value":"0.0"},{"name":"Anlık Net Üretim","value":"0.0"},{"name":"Duruş Süresi","value":""},{"name":"Anlık Makine Firesi","value":3955}],"count":9,"LineName":"A2"}]
     * response : true
     * status : Respose with data
     */

    private boolean response;
    private String status;
    private List<DataBean> data;


    public static class DataBean {
        /**
         * LineData : [{"name":"Önceki Fire Miktarı","value":0},{"name":"Çalışılan Ürün","value":"ACTIVEX HİJYEN SPRY KUMAS&YUZEY 400ML*12"},{"name":"Mevcut Duruş","value":"PlanlıDuruş"},{"name":"Önceki Vardiya Üretim","value":0},{"name":"Hat Adı","value":"A1"},{"name":"Vardiya OEE","value":"0.0"},{"name":"Anlık Net Üretim","value":"0.0"},{"name":"Duruş Süresi","value":"129:30:01"},{"name":"Anlık Makine Firesi","value":0}]
         * LineName : A1
         * count : 9
         */

        private String LineName;
        private int count;
        private List<LineDataBean> LineData;
        private boolean isRed;
        private boolean expanded;
        public static class LineDataBean {
            /**
             * name : Önceki Fire Miktarı
             * value : 0
             */

            private String name;
            private String value;
            private boolean isRed;
            private int id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public boolean isRed() {
                return isRed;
            }

            public void setRed(boolean red) {
                isRed = red;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

        public boolean isExpanded() {
            return expanded;
        }

        public void setExpanded(boolean expanded) {
            this.expanded = expanded;
        }

        public String getLineName() {
            return LineName;
        }

        public void setLineName(String lineName) {
            LineName = lineName;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<LineDataBean> getLineData() {
            return LineData;
        }

        public void setLineData(List<LineDataBean> lineData) {
            LineData = lineData;
        }

        public boolean isRed() {
            return isRed;
        }

        public void setRed(boolean red) {
            isRed = red;
        }
    }

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
}
