package com.example.asm_android_networking;

import static com.example.asm_android_networking.ApiClass.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {
    public static Retrofit retrofit;
    public static Retrofit getRetrofit(){
       return retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
