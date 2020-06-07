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
import retrofit2.http.Query;

public interface IProduct {

   // @Headers({
   //         "Content-Type: application/json",
   //         "Authorization: Token 7c1a4c733efb18d3a3dc8bbc4fef0f30274bb52a"
   //s })

    //good
    @GET("api/products")
    Call<List<Product>> getProductsList();

    //good
    @GET("api/products/{id}")
    Call <Product> getProductById(@Path("id") int postId );

    //good
    @POST("api/products/")
    Call<PostProduct> createPost(@Header("Token") String token, @Body PostProduct post);

    @GET("api/products-in-fridge/")
    Call<List<FridgeProductResponse>> getProductsInFridge(@Header("Content-Type")String contentType, @Header("Token") String token);

    @GET("api/products-in-fridge/")
    Call<List<Object>> getProductsInFridgeObject(@Header("Content-Type")String contentType, @Header("Token") String token);

    @GET("api/products-in-fridge/")
    Call<String> getProductsInFridgeString(@Header("Content-Type")String contentType, @Header("Token") String token);

    //good
    @POST("api/products-in-fridge/")
    Call<PostProduct> addProductToFridge(@Header("Content-Type")String contentType, @Header("Token") String token, @Body String postId);

    //@FormUrlEncoded
    //@POST("api/products-in-fridge/")
    //Call<String> addProductToFridgeString(@Header("Content-Type")String contentType, @Header("Token") String token, @Field("product_id") String postId);

    @FormUrlEncoded
    @POST("api/products-in-fridge/")
    Call<String> removeProductFromFridge(@Header("Token") String token, @Path("product_in_fridge_id") String postId);

}
