package com.example.asm_android_networking.Fragment.Comic;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class ChapterModel {
    private int status;
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

    public static class Data {
        @SerializedName("_id")
        private String _id;
        private int number;
        @SerializedName("images")
        private String[] images;

        public String get_id() {
            return _id;
        }

        public int getNumber() {
            return number;
        }

        public String[] getImages() {
            return images;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "_id='" + _id + '\'' +
                    ", number=" + number +
                    ", images=" + Arrays.toString(images) +
                    '}';
        }
    }
}

