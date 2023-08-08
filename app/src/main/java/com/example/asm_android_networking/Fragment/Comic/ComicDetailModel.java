package com.example.asm_android_networking.Fragment.Comic;

import com.google.gson.annotations.SerializedName;

public class ComicDetailModel {
    @SerializedName("status")
    private int status;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private Data data;

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public Data getData() {
        return data;
    }

    public class Data {
        @SerializedName("_id")
        private String _id;
        private String title;
        private String author;
        private String avatar;
        private String content;
        private String date;
        @SerializedName("chapters")
        private String[] chapters;

        public String get_id() {
            return _id;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getContent() {
            return content;
        }

        public String getDate() {
            return date;
        }

        public String[] getChapters() {
            return chapters;
        }
    }
}

