package com.example.fridgefriend.Authorization;

import com.example.fridgefriend.Model.Product;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LoginApi {

    @FormUrlEncoded
    @POST("api/token/")
    Call<LoginResponse> getToken(@Field("username") String username, @Field("password") String password );

    @FormUrlEncoded
    @POST("api/registration/")
    Call<String> createUser(@Field("username") String username, @Field("password") String password );
}
