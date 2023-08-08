package com.example.asm_android_networking.Fragment.Comic;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Data {
    @SerializedName("_id")
    private String id;
    private String title;
    private String author;
    private String avatar;
    private String content;
    private String date;
    @SerializedName("chapters")
    private String[] chapters;

    private int __v;

    public Data() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", avatar='" + avatar + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", chapters=" + Arrays.toString(chapters) +
                '}';
    }
}
