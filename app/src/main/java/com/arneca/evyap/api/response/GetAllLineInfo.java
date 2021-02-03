package com.arneca.evyap.api.response;/*
 * Created by Sinan KUTAS on 26.01.2021.
 */

import java.util.List;

public class GetAllLineInfo {



    /**
     * status : Respose with data
     * response : true
     * data : {"myArrayList":[{"map":{"PreviousShiftScrapAmount":5555,"ProductName":"Evy Baby Bebek Bazi","Customer":"EVYAP","CurrentStopReason":"-","PreviousShiftTotalProduction":5959,"ProductNumber":"1","TimeStamp":"Jan 25, 2021 12:00:00 AM","LineName":"D06","CurrentShiftOEEStr":"959 ABC","CurrentShiftTotalProduction":5959,"CurrentStopDurationStr":"0","FactoryName":"HIJYEN","CurrentShiftScrapAmount":5559,"CurrentShiftOEE":959,"FactoryCode":"EV31","Region":"TUZLA"}},{"map":{"PreviousShiftScrapAmount":333,"ProductName":"Test Ürün","Customer":"EVYAP","CurrentStopReason":"","PreviousShiftTotalProduction":555,"ProductNumber":"TU-0001","TimeStamp":"Jan 26, 2021 12:00:00 AM","LineName":"D07","CurrentShiftOEEStr":"777 ABC","CurrentShiftTotalProduction":666,"CurrentStopDurationStr":"","FactoryName":"HIJYEN","CurrentShiftScrapAmount":444,"CurrentShiftOEE":777,"FactoryCode":"EV31","Region":"TUZLA"}},{"map":{"PreviousShiftScrapAmount":4555,"ProductName":"TEST ÜRÜN -2 ","Customer":"EVYAP","CurrentStopReason":"Normal Stop","PreviousShiftTotalProduction":5222,"ProductNumber":"TU-0222","TimeStamp":"Jan 25, 2021 12:00:00 AM","LineName":"D10","CurrentShiftOEEStr":"2589 STR","CurrentShiftTotalProduction":2115,"CurrentStopDurationStr":"00:22:52","FactoryName":"HIJYEN","CurrentShiftScrapAmount":3888,"CurrentShiftOEE":2589,"FactoryCode":"EV31","Region":"TUZLA"}}]}
     */

    private String status;
    private boolean response;
    private DataBean data;


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
            /**
             * map : {"PreviousShiftScrapAmount":5555,"ProductName":"Evy Baby Bebek Bazi","Customer":"EVYAP","CurrentStopReason":"-","PreviousShiftTotalProduction":5959,"ProductNumber":"1","TimeStamp":"Jan 25, 2021 12:00:00 AM","LineName":"D06","CurrentShiftOEEStr":"959 ABC","CurrentShiftTotalProduction":5959,"CurrentStopDurationStr":"0","FactoryName":"HIJYEN","CurrentShiftScrapAmount":5559,"CurrentShiftOEE":959,"FactoryCode":"EV31","Region":"TUZLA"}
             */

            private MapBean map;

            public MapBean getMap() {
                return map;
            }

            public void setMap(MapBean map) {
                this.map = map;
            }

            public static class MapBean {


                private int PreviousShiftScrapAmount;
                private String ProductName;
                private String Customer;
                private String CurrentStopReason;
                private int PreviousShiftTotalProduction;
                private String ProductNumber;
                private String TimeStamp;
                private String LineName;
                private String CurrentShiftOEEStr;
                private int CurrentShiftTotalProduction;
                private String CurrentStopDurationStr;
                private String FactoryName;
                private int CurrentShiftScrapAmount;
                private int CurrentShiftOEE;
                private String FactoryCode;
                private String Region;
                private boolean expanded;

                public int getPreviousShiftScrapAmount() {
                    return PreviousShiftScrapAmount;
                }

                public void setPreviousShiftScrapAmount(int previousShiftScrapAmount) {
                    PreviousShiftScrapAmount = previousShiftScrapAmount;
                }

                public String getProductName() {
                    return ProductName;
                }

                public void setProductName(String productName) {
                    ProductName = productName;
                }

                public String getCustomer() {
                    return Customer;
                }

                public void setCustomer(String customer) {
                    Customer = customer;
                }

                public String getCurrentStopReason() {
                    return CurrentStopReason;
                }

                public void setCurrentStopReason(String currentStopReason) {
                    CurrentStopReason = currentStopReason;
                }

                public int getPreviousShiftTotalProduction() {
                    return PreviousShiftTotalProduction;
                }

                public void setPreviousShiftTotalProduction(int previousShiftTotalProduction) {
                    PreviousShiftTotalProduction = previousShiftTotalProduction;
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

                public int getCurrentShiftTotalProduction() {
                    return CurrentShiftTotalProduction;
                }

                public void setCurrentShiftTotalProduction(int currentShiftTotalProduction) {
                    CurrentShiftTotalProduction = currentShiftTotalProduction;
                }

                public String getCurrentStopDurationStr() {
                    return CurrentStopDurationStr;
                }

                public void setCurrentStopDurationStr(String currentStopDurationStr) {
                    CurrentStopDurationStr = currentStopDurationStr;
                }

                public String getFactoryName() {
                    return FactoryName;
                }

                public void setFactoryName(String factoryName) {
                    FactoryName = factoryName;
                }

                public int getCurrentShiftScrapAmount() {
                    return CurrentShiftScrapAmount;
                }

                public void setCurrentShiftScrapAmount(int currentShiftScrapAmount) {
                    CurrentShiftScrapAmount = currentShiftScrapAmount;
                }

                public int getCurrentShiftOEE() {
                    return CurrentShiftOEE;
                }

                public void setCurrentShiftOEE(int currentShiftOEE) {
                    CurrentShiftOEE = currentShiftOEE;
                }

                public String getFactoryCode() {
                    return FactoryCode;
                }

                public void setFactoryCode(String factoryCode) {
                    FactoryCode = factoryCode;
                }

                public String getRegion() {
                    return Region;
                }

                public void setRegion(String region) {
                    Region = region;
                }

                public boolean isExpanded() {
                    return expanded;
                }

                public void setExpanded(boolean expanded) {
                    this.expanded = expanded;
                }
            }
        }
    }

    /**
     * status : Respose with data
     * data : [{"FactoryName":"HIJYEN","FactoryCode":"EV31","LineName":"D06","CurrentShiftOEEStr":"959 ABC","ProductName":"Evy Baby Bebek Bazi","ProductNumber":"1","TimeStamp":"Jan 25, 2021 12:00:00 AM","PreviousShiftScrapAmount":5555,"CurrentShiftScrapAmount":5559,"PreviousShiftTotalProduction":5959,"CurrentShiftTotalProduction":5959,"CurrentShiftOEE":959,"Customer":"EVYAP","Region":"TUZLA","CurrentStopReason":"-","CurrentStopDurationStr":"0"},{"FactoryName":"HIJYEN","FactoryCode":"EV31","LineName":"D07","CurrentShiftOEEStr":"777 ABC","ProductName":"Test Ürün","ProductNumber":"TU-0001","TimeStamp":"Jan 26, 2021 12:00:00 AM","PreviousShiftScrapAmount":333,"CurrentShiftScrapAmount":444,"PreviousShiftTotalProduction":555,"CurrentShiftTotalProduction":666,"CurrentShiftOEE":777,"Customer":"EVYAP","Region":"TUZLA","CurrentStopReason":"","CurrentStopDurationStr":""},{"FactoryName":"HIJYEN","FactoryCode":"EV31","LineName":"D10","CurrentShiftOEEStr":"2589 STR","ProductName":"TEST ÜRÜN -2 ","ProductNumber":"TU-0222","TimeStamp":"Jan 25, 2021 12:00:00 AM","PreviousShiftScrapAmount":4555,"CurrentShiftScrapAmount":3888,"PreviousShiftTotalProduction":5222,"CurrentShiftTotalProduction":2115,"CurrentShiftOEE":2589,"Customer":"EVYAP","Region":"TUZLA","CurrentStopReason":"Normal Stop","CurrentStopDurationStr":"00:22:52"}]


    private String status;
    private List<DataBean> data;



    public static class DataBean {

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
 */
}
