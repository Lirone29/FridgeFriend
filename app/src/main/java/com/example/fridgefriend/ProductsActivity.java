package com.example.fridgefriend;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fridgefriend.Product.IProduct;
import com.example.fridgefriend.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class ProductsActivity extends AppCompatActivity {
    private String urlString = "http://mtx.pmlabs.net:8888/";
    private static final String TAG_TOKEN = "TOKEN";
    String TOKEN;
    Retrofit retrofit;
    IProduct iProduct;
    Call<List<Product>> call;
    List<Product> responseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            TOKEN = bundle.getString(TAG_TOKEN);
        initView();

    }

    private void initView() {}

}
