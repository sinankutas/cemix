package com.arneca.evyap.api.response;/*
 * Created by Sinan KUTAS on 25.01.2021.
 */

import java.util.List;

import androidx.databinding.Bindable;

public class GetFactories {

    /**
     * status : Respose with data
     * data : [{"FactoryName":"AEROSOL","FactoryCode":"EV32"},{"FactoryName":"ARGE","FactoryCode":"EVA1"},{"FactoryName":"HIJYEN","FactoryCode":"EV31"},{"FactoryName":"ISG","FactoryCode":"EVISG1"},{"FactoryName":"KOZMETIK","FactoryCode":"EV71"},{"FactoryName":"LOJISTIK","FactoryCode":"EVL1"},{"FactoryName":"OFIS","FactoryCode":"EVO1"},{"FactoryName":"SABUN","FactoryCode":"EV01"},{"FactoryName":"TEKNIK MUDURLUK","FactoryCode":"EVTM1"}]
     */

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
        /**
         * FactoryName : AEROSOL
         * FactoryCode : EV32
         */

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
}
