package com.arneca.evyap.api.response;/*
 * Created by Sinan KUTAS on 22.02.2021.
 */

import java.util.List;


public class GetAllLineByKey {


    /**
     * data : [{"ReportName":{"name":"Önceki Fire Miktarı","id":"8"},"ReportData":[{"name":"D06","isRed":false,"value":7232},{"name":"D07","isRed":false,"value":8589},{"name":"D08","isRed":false,"value":4064},{"name":"D09","isRed":false,"value":10021},{"name":"D10","isRed":false,"value":7035},{"name":"D11","isRed":false,"value":0},{"name":"D12","isRed":false,"value":0}]},{"ReportName":{"name":"Çalışılan Ürün","id":"5"},"ReportData":[{"name":"D06","isRed":false,"value":"JENNY&WILLY Ç.BEZİ JNR 4*32  R1"},{"name":"D07","isRed":false,"value":"EVY BABY BBZİ YNIDGN E İKİZ 62*4 EXP20"},{"name":"D08","isRed":false,"value":"JENNY&WILLY Ç.BEZİ JNR 4*32  R1"},{"name":"D09","isRed":false,"value":"EVY BABY BBEZİ MAXI E  JUMBO 64*4 EXP18"},{"name":"D10","isRed":false,"value":"JENNY&WILLY Ç.BEZİ MAXI 4*44  R1"},{"name":"D11","isRed":false,"value":"EVY BABY BBEZİ MIDI E İKİZ 46*4 EXP18"},{"name":"D12","isRed":false,"value":"EVY BABY BBEZİ JNR DEV 40*4 TR2018"}],"count":7},{"ReportName":{"name":"Mevcut Duruş","id":"7"},"ReportData":[{"name":"D06","isRed":false,"value":""},{"name":"D07","isRed":true,"value":"PlanlıDuruş"},{"name":"D08","isRed":true,"value":"UNKNOWN"},{"name":"D09","isRed":true,"value":"UNKNOWN"},{"name":"D10","isRed":false,"value":""},{"name":"D11","isRed":true,"value":"PlanlıDuruş"},{"name":"D12","isRed":true,"value":"PlanlıDuruş"}],"count":7},{"ReportName":{"name":"Önceki Vardiya Üretim","id":"6"},"ReportData":[{"name":"D06","isRed":false,"value":119296},{"name":"D07","isRed":false,"value":122760},{"name":"D08","isRed":false,"value":186368},{"name":"D09","isRed":false,"value":189440},{"name":"D10","isRed":false,"value":209968},{"name":"D11","isRed":false,"value":0},{"name":"D12","isRed":false,"value":0}],"count":7},{"ReportName":{"name":"Vardiya OEE","id":"1"},"ReportData":[{"name":"D06","isRed":false,"value":"0.0"},{"name":"D07","isRed":false,"value":"0.0"},{"name":"D08","isRed":false,"value":"0.0"},{"name":"D09","isRed":false,"value":"0.0"},{"name":"D10","isRed":false,"value":"0.0"},{"name":"D11","isRed":false,"value":"0.0"},{"name":"D12","isRed":false,"value":"0.0"}],"count":7},{"ReportName":{"name":"Anlık Net Üretim","id":"4"},"ReportData":[{"name":"D06","isRed":false,"value":"0.0"},{"name":"D07","isRed":false,"value":"0.0"},{"name":"D08","isRed":false,"value":"0.0"},{"name":"D09","isRed":false,"value":"0.0"},{"name":"D10","isRed":false,"value":"0.0"},{"name":"D11","isRed":false,"value":"0.0"},{"name":"D12","isRed":false,"value":"0.0"}],"count":7},{"ReportName":{"name":"Duruş Süresi","id":"3"},"ReportData":[{"name":"D06","isRed":false,"value":""},{"name":"D07","isRed":true,"value":"000:28:16"},{"name":"D08","isRed":true,"value":"000:00:21"},{"name":"D09","isRed":true,"value":"000:32:07"},{"name":"D10","isRed":false,"value":""},{"name":"D11","isRed":true,"value":"556:22:18"},{"name":"D12","isRed":true,"value":"1335:09:41"}],"count":7},{"ReportName":{"name":"Anlık Makine Firesi","id":"2"},"ReportData":[{"name":"D06","isRed":false,"value":25},{"name":"D07","isRed":false,"value":0},{"name":"D08","isRed":false,"value":4},{"name":"D09","isRed":false,"value":0},{"name":"D10","isRed":false,"value":1572},{"name":"D11","isRed":false,"value":0},{"name":"D12","isRed":false,"value":0}],"count":7}]
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
         * ReportName : {"name":"Önceki Fire Miktarı","id":"8"}
         * ReportData : [{"name":"D06","isRed":false,"value":7232},{"name":"D07","isRed":false,"value":8589},{"name":"D08","isRed":false,"value":4064},{"name":"D09","isRed":false,"value":10021},{"name":"D10","isRed":false,"value":7035},{"name":"D11","isRed":false,"value":0},{"name":"D12","isRed":false,"value":0}]
         * count : 7
         */

        private ReportNameBean ReportName;
        private int count;
        private List<ReportDataBean> ReportData;

        public ReportNameBean getReportName() {
            return ReportName;
        }

        public void setReportName(ReportNameBean reportName) {
            ReportName = reportName;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ReportDataBean> getReportData() {
            return ReportData;
        }

        public void setReportData(List<ReportDataBean> reportData) {
            ReportData = reportData;
        }

        public static class ReportNameBean {
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

        public static class ReportDataBean {
            /**
             * name : D06
             * isRed : false
             * value : 7232
             */

            private String name;
            private boolean isRed;
            private String value;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isRed() {
                return isRed;
            }

            public void setRed(boolean red) {
                isRed = red;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
