package com.example.fridgefriend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fridgefriend.Product.IFridgeProduct;
import com.example.fridgefriend.Model.Product;
import com.example.fridgefriend.Product.AddProductActivity;
import com.example.fridgefriend.Product.CreateProductActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FridgeActivity extends AppCompatActivity {
    private static final String TAG = "FridgeActivity";
    String baseUrl ="http://mtx.pmlabs.net:8888/";
    private static final String TAG_TOKEN = "TOKEN";
    String TOKEN;

    TextView _tmpTextView;
    private Button _addProductButton;
    private Button _createProductButton;
    private Button _returnButton;

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
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            TOKEN = bundle.getString(TAG_TOKEN);
        initView();

    }

    private void initView() {

        _addProductButton = (Button) findViewById(R.id.addProductButton);
        _addProductButton.setEnabled(true);
        _addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });

        _createProductButton = (Button) findViewById(R.id.createProductButton);
        _createProductButton.setEnabled(true);
        _createProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProduct();
            }
        });

        _returnButton =  (Button) findViewById(R.id.addProductReturnButton);
        _returnButton.setEnabled(true);
        _returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnButton();
            }
        });

       iFridgeProduct = retrofit.create(IFridgeProduct.class);

    }

    private void addProduct(){
        Intent intent = new Intent(getApplicationContext(), AddProductActivity.class);
        intent.putExtra(TAG_TOKEN, TOKEN);
        startActivityForResult(intent, 1);
        //finish();
    }

    private void createProduct(){
        Intent intent = new Intent(getApplicationContext(), CreateProductActivity.class);
        intent.putExtra(TAG_TOKEN, TOKEN);
        startActivityForResult(intent, 1);
        //finish();
    }

    private void removeProduct(){

    }

    private void checkExpireDate(){

    }

    private void loadFridgeProducts(){
        Call<List<Product>> call = iFridgeProduct.getFridgeProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(!response.isSuccessful()){
                    // _tmpTextView.setText("Code: " + response.code());
                    return;
                }

                List<Product> tmp = response.body();
               // productsArrayList = (ArrayList<Product>) tmp;
                //_recycleViewAdapter.addAllItems(productsArrayList);

                responseList = response.body();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                //_tmpTextView.setText(t.getMessage());
            }
        });
    }


    public void returnButton(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra(TAG_TOKEN, TOKEN);
        startActivityForResult(intent, 1);
        finish();
    }
}
