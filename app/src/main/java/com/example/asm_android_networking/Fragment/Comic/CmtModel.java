package com.example.asm_android_networking.Fragment.Comic;

import com.google.gson.annotations.SerializedName;

public class CmtModel {
    @SerializedName("status")
    private int status;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private Data[] data;

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public Data[] getData() {
        return data;
    }


    public static class Data {
        @SerializedName("_id")
        private String _id;
        private String text;
        private String time;
        @SerializedName("comic")
        private String comic;
        @SerializedName("user")
        private String user;

        public Data(String text, String time, String comic, String user) {
            this.text = text;
            this.time = time;
            this.comic = comic;
            this.user = user;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getComic() {
            return comic;
        }

        public void setComic(String comic) {
            this.comic = comic;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

    }
}

