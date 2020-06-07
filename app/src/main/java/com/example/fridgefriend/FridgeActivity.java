package com.example.fridgefriend;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridgefriend.Product.FridgeProductAdapter;
import com.example.fridgefriend.Product.FridgeProductResponse;
import com.example.fridgefriend.Product.IFridgeProduct;
import com.example.fridgefriend.Model.Product;
import com.example.fridgefriend.Product.AddProductActivity;
import com.example.fridgefriend.Product.CreateProductActivity;
import com.example.fridgefriend.Product.IProduct;
import com.example.fridgefriend.Product.OnProductClikListener;
import com.example.fridgefriend.Product.PostProduct;
import com.example.fridgefriend.Product.ProductAdapter;

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
    String TOKEN = "";

    private Button _addProductButton;
    private Button _createProductButton;
    private Button _returnButton;

    List<PostProduct> responseList;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    IProduct iFridgeProduct  = retrofit.create(IProduct.class);

    //components
    RecyclerView _recyclerView;
    FridgeProductAdapter _recycleViewAdapter;
    RecyclerView.LayoutManager _recycleViewLayoutManager;

    ArrayList<Product> productsArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            TOKEN = bundle.getString(TAG_TOKEN);

        productsArrayList = new ArrayList<Product>();

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

        _returnButton =  (Button) findViewById(R.id.fridgeReturnButton);
        _returnButton.setEnabled(true);
        _returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnButton();
            }
        });

        _recyclerView =  (RecyclerView) findViewById(R.id.productRecyclerView);
        _recyclerView.setHasFixedSize(true);

        _recycleViewLayoutManager = new LinearLayoutManager(this);
        _recyclerView.setEnabled(true);
        _recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        loadFridgeProducts();

        _recycleViewAdapter = new FridgeProductAdapter(productsArrayList, new OnProductClikListener() {
            @Override
            public void onItemClick(Product item) {
                chooseProduct(item.getId());
            }
        });


        _recycleViewAdapter.notifyDataSetChanged();
        _recyclerView.setLayoutManager(_recycleViewLayoutManager);
        _recyclerView.setAdapter(_recycleViewAdapter);
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

    private void removeProduct(String id){

        Call<String> call = iFridgeProduct.removeProductFromFridge(TOKEN, id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                    // _tmpTextView.setText("Code: " + response.code());
                    return;
                }

                System.out.println("Code: " + response.code());
                //List<Product> tmp = response.body();
                //productsArrayList = (ArrayList<Product>) tmp;
                //_recycleViewAdapter.addAllItems(productsArrayList);

                //responseList = response.body();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //_tmpTextView.setText(t.getMessage());
            }
        });

    }

    private void checkExpireDate(){

    }

    private void chooseProduct(int id){

    }

    private void loadFridgeProducts(){

        String contentType = "application/json";

        System.out.println("In Fridge Token " + TOKEN);
        Call<List<FridgeProductResponse>> call = iFridgeProduct.getProductsInFridge(contentType, TOKEN);
        call.enqueue(new Callback<List<FridgeProductResponse>>() {
            @Override
            public void onResponse(Call<List<FridgeProductResponse>> call, Response<List<FridgeProductResponse>> response) {
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                    // _tmpTextView.setText("Code: " + response.code());
                    return;
                }

                Log.d("Respone: ", "" +response.isSuccessful());
                Log.d("Respone: ", "" +response.body());
                Log.d("Respone: ", "" +response.headers().toString());
                List<FridgeProductResponse> tmp = response.body();
                System.out.println("Code: " + tmp.get(0).toString());
                //productsArrayList = (ArrayList<Product>) tmp;
                //_recycleViewAdapter.addAllItems(productsArrayList);
            }

            @Override
            public void onFailure(Call<List<FridgeProductResponse>> call, Throwable t) {
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
