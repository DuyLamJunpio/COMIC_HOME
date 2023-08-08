package com.example.asm_android_networking.Fragment.Comic;

import com.example.asm_android_networking.Login.LoginModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ComicDAO {
    @GET("/api/comic/listComic")
    Call<ComicModel> getData();
    @GET("/api/comic/detailComic/{id}")
    Call<ComicDetailModel> getDataById(@Path("id") String id);

    @GET("/api/comic/getChapter/{id}")
    Call<ChapterModel> getChapterById(@Path("id") String id);

    @GET("/api/cmt/listCmt/{id}")
    Call<CmtModel> getCmtById(@Path("id") String id);

    @GET("/api/user/detailUser/{id}")
    Call<UserModel> getUserById(@Path("id") String id);

    @PUT("api/cmt/editCmt/{id}")
    Call<CmtModel>editById(@Path("id") String id, @Body CmtModel.Data data, @Header("Authorization") String authToken);

    @DELETE("/api/cmt/deleteCmt/{id}")
    Call<CmtModel>deleteById(@Path("id") String id ,@Header("Authorization") String authToken);

    @POST("/api/cmt/addCmt")
    Call<CmtModel>postCmt(@Body CmtModel.Data data);
}
