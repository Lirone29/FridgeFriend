package com.example.fridgefriend.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IFridgeProduct {
    @GET("fridgeProduct")
    Call<List<Product>> getFridgeProducts();
}
