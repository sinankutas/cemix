package com.arneca.evyap.api.response;/*
 * Created by Sinan KUTAS on 8.03.2021.
 */

import java.util.List;


public class GetKVKConfirm {


    /**
     * status : Kullanıcı KVKK Sözleşmesini kabul etmiştir.
     * response : true
     * data : {"myArrayList":[{"map":{"user":"TestApiMobile"}}]}
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
             * map : {"user":"TestApiMobile"}
             */

            private MapBean map;

            public MapBean getMap() {
                return map;
            }

            public void setMap(MapBean map) {
                this.map = map;
            }

            public static class MapBean {
                /**
                 * user : TestApiMobile
                 */

                private String user;

                public String getUser() {
                    return user;
                }

                public void setUser(String user) {
                    this.user = user;
                }
            }
        }
    }
}
