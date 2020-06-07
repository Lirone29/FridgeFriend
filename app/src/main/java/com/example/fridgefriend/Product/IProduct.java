package com.example.fridgefriend.Product;

import com.example.fridgefriend.Model.Product;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IProduct {

    //good
    @GET("api/products")
    Call<List<Product>> getProductsList();

    //good
    @GET("api/products/{id}")
    Call <Product> getProductById(@Path("id") int postId );

    //good
    @POST("api/products/")
    Call<PostProduct> createPost(@Header("Token") String token, @Body PostProduct post);

    //good
    @GET("api/products-in-fridge/")
    @Headers({"Content-Type: application/json"})
    Call<List<FridgeProductResponse>> getProductsInFridge(@Header("Content-Type")String contentType, @Header("Authorization") String token);

    //good
    @POST("api/products-in-fridge/")
    Call<PostProduct> addProductToFridge(@Header("Content-Type")String contentType, @Header("Token") String token, @Body String postId);

    //@FormUrlEncoded
    //@POST("api/products-in-fridge/")
    //Call<String> addProductToFridgeString(@Header("Content-Type")String contentType, @Header("Token") String token, @Field("product_id") String postId);

    @FormUrlEncoded
    @Headers({"Content-Type: application/json"})
    @POST("api/products-in-fridge/")
    Call<Object> removeProductFromFridge(@Header("Content-Type")String contentType, @Header("Token") String token, @Field("product_in_fridge_id") String postId);

}
