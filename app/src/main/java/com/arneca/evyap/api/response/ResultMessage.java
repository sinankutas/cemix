package com.arneca.evyap.api.response;/*
 * Created by Sinan KUTAS on 22.01.2021.
 */

public class ResultMessage {
    private ResultMessageBean result_message;

    public ResultMessageBean getResult_message() {
        return result_message;
    }

    public void setResult_message(ResultMessageBean result_message) {
        this.result_message = result_message;
    }

    public static class ResultMessageBean {

        private String title;
        private String message;
        private String type;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
