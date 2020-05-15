package com.example.fridgefriend.Product;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fridgefriend.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddProductActivity extends AppCompatActivity {
    String baseUrl ="http://127.0.0.1:8000/api/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initView();
    }

    public void initView(){
    }
}
