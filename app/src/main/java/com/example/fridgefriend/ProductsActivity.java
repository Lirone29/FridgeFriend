package com.example.fridgefriend;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fridgefriend.Data.IProduct;
import com.example.fridgefriend.Data.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class ProductsActivity extends AppCompatActivity {
    String baseUrl ="http://127.0.0.1:8000/api/";
    Retrofit retrofit;
    IProduct iProduct;
    Call<List<Product>> call;
    List<Product> responseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initView();

    }

    private void initView() {}

}
