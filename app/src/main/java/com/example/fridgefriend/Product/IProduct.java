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
    @GET("products")
    Call<List<Product>> getProductsList();

    @GET("api/products/{id}")
    Call <Product> getProduct(@Path("id") int postId );

    @GET("api/products")
    Call<String> getProducts() ;

    @Headers({
            "Content-Type: application/json",
            "Authorization: Token 82dae18b776fe80c6d299b59627249f1ef57fcf4"
    })

    @POST("api/product/")
    public Call<PostProduct> createPost(@Header("Token") String token, @Body PostProduct post);



    @POST("api/product/")
    @FormUrlEncoded
    Call<PostProduct> savePost(@Field("name") String name,
                               @Field("calories") Float body,
                               @Field("weight") Float weight,
                               @Field("daysToExpire") int daysToExpire);

    @POST("api/product/")
    @FormUrlEncoded
    Call<PostProduct> savePost2(@Body PostProduct post);
}
