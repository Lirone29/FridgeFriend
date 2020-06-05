package com.example.fridgefriend.Product;

import com.example.fridgefriend.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IFridgeProduct {
    @GET("api/fridgeProduct")
    Call<List<Product>> getFridgeProducts();
}
