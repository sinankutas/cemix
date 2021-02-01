package com.arneca.evyap.api.response;/*
 * Created by Sinan KUTAS on 25.01.2021.
 */

import java.util.List;

public class GetFactories {
    /**
     * status : Respose with data
     * response : true
     * data : {"myArrayList":[{"map":{"FactoryName":"AEROSOL","FactoryCode":"EV32"}},{"map":{"FactoryName":"HIJYEN","FactoryCode":"EV31"}}]}
     */

    private String status;
    private boolean response;
    private DataBean data;
    private boolean expanded;


    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MyArrayListBean> myArrayList;


        public List<MyArrayListBean> getMyArrayList() {
            return myArrayList;
        }

        public void setMyArrayList(List<MyArrayListBean> myArrayList) {
            this.myArrayList = myArrayList;
        }

        public static class MyArrayListBean {
            public MapBean getMap() {
                return map;
            }

            public void setMap(MapBean map) {
                this.map = map;
            }

            /**
             * map : {"FactoryName":"AEROSOL","FactoryCode":"EV32"}
             */

            private MapBean map;


            public static class MapBean {
                /**
                 * FactoryName : AEROSOL
                 * FactoryCode : EV32
                 */

                private String FactoryName;
                private String FactoryCode;

                public String getFactoryName() {
                    return FactoryName;
                }

                public void setFactoryName(String factoryName) {
                    FactoryName = factoryName;
                }

                public String getFactoryCode() {
                    return FactoryCode;
                }

                public void setFactoryCode(String factoryCode) {
                    FactoryCode = factoryCode;
                }
            }
        }


    }



    /**
     * status : Respose with data
     * data : [{"FactoryName":"AEROSOL","FactoryCode":"EV32"},{"FactoryName":"ARGE","FactoryCode":"EVA1"},{"FactoryName":"HIJYEN","FactoryCode":"EV31"},{"FactoryName":"ISG","FactoryCode":"EVISG1"},{"FactoryName":"KOZMETIK","FactoryCode":"EV71"},{"FactoryName":"LOJISTIK","FactoryCode":"EVL1"},{"FactoryName":"OFIS","FactoryCode":"EVO1"},{"FactoryName":"SABUN","FactoryCode":"EV01"},{"FactoryName":"TEKNIK MUDURLUK","FactoryCode":"EVTM1"}]





    private String status;
    private List<data> data;
    private boolean expanded;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
       // notifyPropertyChanged(BR.status);
    }

    public List<data> getData() {
        return data;
    }

    public void setData(List<data> data) {
        this.data = data;
     //   notifyPropertyChanged(BR.data);
    }

    public static class data  {


        private String FactoryName;
        private String FactoryCode;

        public String getFactoryName() {
            return FactoryName;
        }

        public void setFactoryName(String FactoryName) {
            this.FactoryName = FactoryName;
          //  notifyPropertyChanged(BR.FactoryName);
        }

        public String getFactoryCode() {
            return FactoryCode;
        }

        public void setFactoryCode(String FactoryCode) {
            this.FactoryCode = FactoryCode;
          //  notifyPropertyChanged(BR.FactoryCode);
        }
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
     */


}
