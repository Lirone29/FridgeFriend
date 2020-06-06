package com.example.fridgefriend.Product;

import com.example.fridgefriend.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IFridgeProduct {

    @GET("api/fridgeProduct/")
    Call<List<Product>> getFridgeProducts();

   // @Headers({
    //        "Content-Type: application/json",
   //         "Authorization: Token 12fe059e10a0b4abb78291c572df64bad29bc981"
    //})


    @GET("api/products-in-fridge/")
    Call<List<Product>> getProductsInFridge(@Header("Token") String token);

    @POST("api/products-in-fridge/")
    Call<String> addProductToFridge(@Header("Token") String token, @Path("product_id") String postId);

    @POST("api/products-in-fridge/")
    Call<String> removeProductFromFridge(@Header("Token") String token, @Path("product_in_fridge_id") String postId);
}
