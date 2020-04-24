package com.example.fridgefriend.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IProduct {
    @GET("products")
    Call<List<Product>> getProducts();
}
