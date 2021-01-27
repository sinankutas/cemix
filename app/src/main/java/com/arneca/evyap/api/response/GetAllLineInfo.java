package com.arneca.evyap.api.response;/*
 * Created by Sinan KUTAS on 26.01.2021.
 */

import java.util.List;

public class GetAllLineInfo {


    /**
     * status : Respose with data
     * data : [{"FactoryName":"HIJYEN","FactoryCode":"EV31","LineName":"D06","CurrentShiftOEEStr":"959 ABC","ProductName":"Evy Baby Bebek Bazi","ProductNumber":"1","TimeStamp":"Jan 25, 2021 12:00:00 AM","PreviousShiftScrapAmount":5555,"CurrentShiftScrapAmount":5559,"PreviousShiftTotalProduction":5959,"CurrentShiftTotalProduction":5959,"CurrentShiftOEE":959,"Customer":"EVYAP","Region":"TUZLA","CurrentStopReason":"-","CurrentStopDurationStr":"0"},{"FactoryName":"HIJYEN","FactoryCode":"EV31","LineName":"D07","CurrentShiftOEEStr":"777 ABC","ProductName":"Test Ürün","ProductNumber":"TU-0001","TimeStamp":"Jan 26, 2021 12:00:00 AM","PreviousShiftScrapAmount":333,"CurrentShiftScrapAmount":444,"PreviousShiftTotalProduction":555,"CurrentShiftTotalProduction":666,"CurrentShiftOEE":777,"Customer":"EVYAP","Region":"TUZLA","CurrentStopReason":"","CurrentStopDurationStr":""},{"FactoryName":"HIJYEN","FactoryCode":"EV31","LineName":"D10","CurrentShiftOEEStr":"2589 STR","ProductName":"TEST ÜRÜN -2 ","ProductNumber":"TU-0222","TimeStamp":"Jan 25, 2021 12:00:00 AM","PreviousShiftScrapAmount":4555,"CurrentShiftScrapAmount":3888,"PreviousShiftTotalProduction":5222,"CurrentShiftTotalProduction":2115,"CurrentShiftOEE":2589,"Customer":"EVYAP","Region":"TUZLA","CurrentStopReason":"Normal Stop","CurrentStopDurationStr":"00:22:52"}]
     */

    private String status;
    private List<DataBean> data;
    private boolean expanded;


    public static class DataBean {
        /**
         * FactoryName : HIJYEN
         * FactoryCode : EV31
         * LineName : D06
         * CurrentShiftOEEStr : 959 ABC
         * ProductName : Evy Baby Bebek Bazi
         * ProductNumber : 1
         * TimeStamp : Jan 25, 2021 12:00:00 AM
         * PreviousShiftScrapAmount : 5555
         * CurrentShiftScrapAmount : 5559
         * PreviousShiftTotalProduction : 5959
         * CurrentShiftTotalProduction : 5959
         * CurrentShiftOEE : 959
         * Customer : EVYAP
         * Region : TUZLA
         * CurrentStopReason : -
         * CurrentStopDurationStr : 0
         */

        private String FactoryName;
        private String FactoryCode;
        private String LineName;
        private String CurrentShiftOEEStr;
        private String ProductName;
        private String ProductNumber;
        private String TimeStamp;
        private int PreviousShiftScrapAmount;
        private int CurrentShiftScrapAmount;
        private int PreviousShiftTotalProduction;
        private int CurrentShiftTotalProduction;
        private int CurrentShiftOEE;
        private String Customer;
        private String Region;
        private String CurrentStopReason;
        private String CurrentStopDurationStr;

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

        public String getLineName() {
            return LineName;
        }

        public void setLineName(String lineName) {
            LineName = lineName;
        }

        public String getCurrentShiftOEEStr() {
            return CurrentShiftOEEStr;
        }

        public void setCurrentShiftOEEStr(String currentShiftOEEStr) {
            CurrentShiftOEEStr = currentShiftOEEStr;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String productName) {
            ProductName = productName;
        }

        public String getProductNumber() {
            return ProductNumber;
        }

        public void setProductNumber(String productNumber) {
            ProductNumber = productNumber;
        }

        public String getTimeStamp() {
            return TimeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            TimeStamp = timeStamp;
        }

        public int getPreviousShiftScrapAmount() {
            return PreviousShiftScrapAmount;
        }

        public void setPreviousShiftScrapAmount(int previousShiftScrapAmount) {
            PreviousShiftScrapAmount = previousShiftScrapAmount;
        }

        public int getCurrentShiftScrapAmount() {
            return CurrentShiftScrapAmount;
        }

        public void setCurrentShiftScrapAmount(int currentShiftScrapAmount) {
            CurrentShiftScrapAmount = currentShiftScrapAmount;
        }

        public int getPreviousShiftTotalProduction() {
            return PreviousShiftTotalProduction;
        }

        public void setPreviousShiftTotalProduction(int previousShiftTotalProduction) {
            PreviousShiftTotalProduction = previousShiftTotalProduction;
        }

        public int getCurrentShiftTotalProduction() {
            return CurrentShiftTotalProduction;
        }

        public void setCurrentShiftTotalProduction(int currentShiftTotalProduction) {
            CurrentShiftTotalProduction = currentShiftTotalProduction;
        }

        public int getCurrentShiftOEE() {
            return CurrentShiftOEE;
        }

        public void setCurrentShiftOEE(int currentShiftOEE) {
            CurrentShiftOEE = currentShiftOEE;
        }

        public String getCustomer() {
            return Customer;
        }

        public void setCustomer(String customer) {
            Customer = customer;
        }

        public String getRegion() {
            return Region;
        }

        public void setRegion(String region) {
            Region = region;
        }

        public String getCurrentStopReason() {
            return CurrentStopReason;
        }

        public void setCurrentStopReason(String currentStopReason) {
            CurrentStopReason = currentStopReason;
        }

        public String getCurrentStopDurationStr() {
            return CurrentStopDurationStr;
        }

        public void setCurrentStopDurationStr(String currentStopDurationStr) {
            CurrentStopDurationStr = currentStopDurationStr;
        }
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

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
