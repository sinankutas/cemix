package com.arneca.evyap.api.response;/*
 * Created by Sinan KUTAS on 26.01.2021.
 */


public class GetLineInfo {


    /**
     * status : Respose with data
     * data : {"FactoryName":"HIJYEN","FactoryCode":"EV31","LineName":"D06","CurrentShiftOEEStr":"959 ABC","ProductName":"Evy Baby Bebek Bazi","ProductNumber":"1","TimeStamp":"Jan 25, 2021 12:00:00 AM","PreviousShiftScrapAmount":5555,"CurrentShiftScrapAmount":5559,"PreviousShiftTotalProduction":5959,"CurrentShiftTotalProduction":5959,"CurrentShiftOEE":959,"Customer":"EVYAP","Region":"TUZLA","CurrentStopReason":"-","CurrentStopDurationStr":"0"}
     */

    private String status;
    private DataBean data;

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
}
