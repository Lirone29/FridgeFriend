package com.example.fridgefriend.Product;

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

import com.example.fridgefriend.Data.Dialog;
import com.example.fridgefriend.FridgeActivity;
import com.example.fridgefriend.Model.Product;
import com.example.fridgefriend.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddProductActivity extends AppCompatActivity {

    private static final String TAG_TOKEN = "TOKEN";
    private static final String contentType = "application/json";
    String TOKEN = "";
    private String urlString = "http://mtx.pmlabs.net:8888/";


    //components
    private TextView _searchProductTextView;
    private Button _searchButton;
    private Button _returnButton;

    //components
    RecyclerView _recyclerView;
    ProductAdapter _recycleViewAdapter;
    RecyclerView.LayoutManager _recycleViewLayoutManager;

    ArrayList<Product> productsArrayList;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(urlString)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    IProduct productApi = retrofit.create(IProduct.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            TOKEN = bundle.getString(TAG_TOKEN);
            System.out.println("TOKEN IN ADD = " + TOKEN);

        productsArrayList = new ArrayList<Product>();

        initView();
    }

    public void initView() {

        _searchProductTextView = (TextView) findViewById(R.id.searchProductTextID);

        _searchButton = (Button) findViewById(R.id.searchButton);
        _searchButton.setEnabled(true);
        _searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchProduct();
            }
        });

        _returnButton = (Button) findViewById(R.id.addProductReturnButton);
        _returnButton.setEnabled(true);
        _returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnButton();
            }
        });

        _recyclerView =  (RecyclerView) findViewById(R.id.addProductRecycleView);
        _recyclerView.setHasFixedSize(true);
        _recycleViewLayoutManager = new LinearLayoutManager(this);
        _recyclerView.setEnabled(true);
        _recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        loadAllProducts();

        _recycleViewAdapter = new ProductAdapter(productsArrayList, new OnProductClikListener() {
            @Override
            public void onItemClick(Product item) {
                chooseProduct(item.getId());
            }
        });


        _recycleViewAdapter.notifyDataSetChanged();
        _recyclerView.setLayoutManager(_recycleViewLayoutManager);
        _recyclerView.setAdapter(_recycleViewAdapter);

    }


    public void loadAllProducts(){

        Call<List<Product>> call = productApi.getProductsList();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (!response.isSuccessful()) {
                    Log.d("Respone: ", "" +response.isSuccessful());
                    Log.d("Respone: ", "" +response.headers().toString());
                    return;
                }

                Log.d("Respone: ", "" +response.isSuccessful());
                Log.d("Respone: ", "" +response.headers().toString());

                List<Product> tmp = response.body();
                productsArrayList = (ArrayList<Product>) tmp;
                _recycleViewAdapter.addAllItems(productsArrayList);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("Failure: ", "" +t.getMessage());
            }
        });
    }


    public void getProductsById(int _id){

        Call<Product> call = productApi.getProductById(_id);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

                if (!response.isSuccessful()) {
                    _searchProductTextView.setText("Code: " + response.code());
                    return;
                }

                Product product = response.body();

                productsArrayList.clear();
                productsArrayList.add(product);
                _recycleViewAdapter.removeItems();
                _recycleViewAdapter.addAllItems( productsArrayList);

                Log.d("All Products: ", productsArrayList.get(0).getName());

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                _searchProductTextView.setText(t.getMessage());
            }
        });
    }

    public void chooseProduct(int _id){
        addProductToFridge(String.valueOf(_id));
    }

    public void addProductToFridge(String id){

        AddProduct addProduct = new AddProduct(Integer.valueOf(id));
        Call<AddProduct> call = productApi.addProductToFridge(contentType, "TOKEN " + TOKEN, addProduct);
        call.enqueue(new Callback<AddProduct>() {
            @Override
            public void onResponse(Call<AddProduct> call, Response<AddProduct> response) {

                if (!response.isSuccessful()) {
                    _searchProductTextView.setText("Code: " + response.code());
                    return;
                }

                System.out.println("Code: " + String.valueOf(response.body()));
                Log.d("Respone: ", "" +response.isSuccessful());
                Log.d("Respone: ", "" +response.headers().toString());
                openDialog();

            }

            @Override
            public void onFailure(Call<AddProduct> call, Throwable t) {
                _searchProductTextView.setText(t.getMessage());
            }
        });
    }

    public void searchProduct(){
        String productText = _searchProductTextView.getText().toString();
        for(int i = 0 ; i < productsArrayList.size(); i++){
            if(productsArrayList.get(i).getName().equals(productText)) {
                getProductsById(i+1);
            }
        }
    }

    public void returnButton(){
        Intent intent = new Intent(getApplicationContext(), FridgeActivity.class);
        intent.putExtra(TAG_TOKEN,TOKEN);
        startActivity(intent);
        finish();

    }

    private void openDialog() {
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "dialog");
    }
}
