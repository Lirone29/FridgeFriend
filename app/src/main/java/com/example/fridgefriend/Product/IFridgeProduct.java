package com.example.fridgefriend.Product;

import com.example.fridgefriend.Model.Product;

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

public interface IFridgeProduct {

    @GET("api/products")
    Call<List<Product>> getFridgeProducts(@Header("Content-Type")String contentType, @Header("Token") String token);

    @GET("api/products-in-fridge/")
    Call<List<FridgeProductResponse>> getProductsInFridge(@Header("Content-Type")String contentType, @Header("Token") String token);

    @GET("api/products-in-fridge/")
    Call<List<FridgeProductResponse>> getProductsInFridge(@Header("Token") String token);

    @POST("api/products-in-fridge/")
    Call<PostProduct> addProductToFridge(@Header("Content-Type")String contentType, @Header("Token") String token, @Body String postId);

    @POST("api/products-in-fridge/")
    Call<String> removeProductFromFridge(@Header("Token") String token, @Path("product_in_fridge_id") String postId);

    //@FormUrlEncoded
    //@POST("api/products-in-fridge/")
   // Call<String> removeProductFromFridge(@Header("Token") String token, @Path("product_in_fridge_id") String postId);
}
