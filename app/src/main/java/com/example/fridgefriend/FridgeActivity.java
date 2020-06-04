package com.example.fridgefriend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fridgefriend.Data.IFridgeProduct;
import com.example.fridgefriend.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FridgeActivity extends AppCompatActivity {
    private static final String TAG = "FridgeActivity";
    String baseUrl ="http://127.0.0.1:8000/api/";

    TextView _tmpTextView;
    private Button _getProductButton;

    IFridgeProduct iFridgeProduct;
    Call<List<Product>> call;
    List<Product> responseList;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);
        initView();

    }

    private void getProducts(){

    }

    private void initView() {

        _getProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProducts();
            }
        });

        /*
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    */
       iFridgeProduct = retrofit.create(IFridgeProduct.class);

        call = iFridgeProduct.getFridgeProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(!response.isSuccessful()){
                   // _tmpTextView.setText("Code: " + response.code());
                    return;
                }

                responseList = response.body();

                for( Product post:  responseList){
                    String content = "";
                    content +="productID " + post.getId() + " \n ";
                    content += "name: " + post.getName() + "\n";
                    //_tmpTextView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                //_tmpTextView.setText(t.getMessage());
            }
        });
    }

    private void addProduct(){
        Intent intent = new Intent(getApplicationContext(), ProductsActivity.class);
        startActivityForResult(intent, 1);
        finish();
    }

    private void removeProduct(){

    }

    private void checkExpireDate(){

    }
}
