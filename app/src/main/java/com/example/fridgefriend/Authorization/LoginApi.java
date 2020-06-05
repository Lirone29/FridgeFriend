package com.example.fridgefriend.Authorization;

import com.example.fridgefriend.Model.Product;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginApi {

    @GET("api/token/")
    Call<String> getToken(@Path("username") String username, @Path("password") String password );

    @POST("api/registration/")
    Call<String> createUser(@Path("username") String username, @Path("password") String password );
}
