package com.example.asm_android_networking.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginDAO {
    @GET("/api/user/list")
    Call<List<LoginModel>> getData();
    @POST("/api/user/login")
    Call<LoginModel>postData(@Body LoginModel data);
}
