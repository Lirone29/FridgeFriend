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

public interface IProduct {

    //good
    @GET("api/products")
    Call<List<Product>> getProductsList();

    //good
    @GET("api/products/{id}")
    Call <Product> getProductById(@Path("id") int postId );

    //good
    @POST("api/products/")
    public Call<PostProduct> createPost(@Header("Token") String token, @Body PostProduct post);

    @POST("api/products-in-fridge/")
    Call<PostProduct> addProductToFridge(@Header("Token") String token, @Body String postId);

    @GET("api/products-in-fridge/")
    Call<List<Product>> getProductsInFridge(@Header("Token") String token);

    @POST("api/products-in-fridge/")
    Call<String> removeProductFromFridge(@Header("Token") String token, @Path("product_in_fridge_id") String postId);

    //@GET("api/fridgeProduct/")
    //Call<List<Product>> getFridgeProducts();

}
