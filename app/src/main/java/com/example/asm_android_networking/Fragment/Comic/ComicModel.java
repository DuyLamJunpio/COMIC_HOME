package com.example.asm_android_networking.Fragment.Comic;

import com.example.asm_android_networking.Login.LoginModel;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class ComicModel {

    @SerializedName("status")
    private int status;

    @SerializedName("msg")
    private String message;

    @SerializedName("data")
    private Data[] data;

    public ComicModel() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ComicModel{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
