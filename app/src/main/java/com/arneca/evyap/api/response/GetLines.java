package com.arneca.evyap.api.response;/*
 * Created by Sinan KUTAS on 25.01.2021.
 */

import java.util.List;

public class GetLines {
    /**
     * status : Respose with data
     * data : [{"FactoryName":"HIJYEN","LineName":"D06"},{"FactoryName":"HIJYEN","LineName":"D07"},{"FactoryName":"HIJYEN","LineName":"D08"},{"FactoryName":"HIJYEN","LineName":"D09"},{"FactoryName":"HIJYEN","LineName":"D10"},{"FactoryName":"HIJYEN","LineName":"D11"},{"FactoryName":"HIJYEN","LineName":"D12"}]
     */

    private String status;
    private List<data> data;

    private boolean expanded;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<data> getData() {
        return data;
    }

    public void setData(List<data> data) {
        this.data = data;
    }

    public static class data {
        /**
         * FactoryName : HIJYEN
         * LineName : D06
         */

        private String FactoryName;
        private String LineName;

        public String getFactoryName() {
            return FactoryName;
        }

        public void setFactoryName(String FactoryName) {
            this.FactoryName = FactoryName;
        }

        public String getLineName() {
            return LineName;
        }

        public void setLineName(String LineName) {
            this.LineName = LineName;
        }
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }
}
