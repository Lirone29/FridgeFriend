package com.example.fridgefriend;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridgefriend.Model.FridgeProduct;
import com.example.fridgefriend.Product.FridgeProductAdapter;
import com.example.fridgefriend.Product.FridgeProductResponse;
import com.example.fridgefriend.Model.Product;
import com.example.fridgefriend.Product.AddProductActivity;
import com.example.fridgefriend.Product.CreateProductActivity;
import com.example.fridgefriend.Product.IProduct;
import com.example.fridgefriend.Product.OnFridgeProductClickListener;
import com.example.fridgefriend.Product.OnProductClikListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
    private static final String contentType = "application/json";
    String TOKEN = "";
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Button _addProductButton;
    private Button _createProductButton;
    private Button _returnButton;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    IProduct productApi  = retrofit.create(IProduct.class);

    //components
    RecyclerView _recyclerView;
    FridgeProductAdapter _recycleViewAdapter;
    RecyclerView.LayoutManager _recycleViewLayoutManager;

    ArrayList<Product> productsArrayList;
    ArrayList<FridgeProduct> viewList;
    ArrayList<FridgeProductResponse> productsFridgeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            TOKEN = bundle.getString(TAG_TOKEN);

        productsArrayList = new ArrayList<Product>();
        viewList = new ArrayList<FridgeProduct>();
        productsFridgeArrayList = new  ArrayList<FridgeProductResponse>();
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


        _recycleViewAdapter = new FridgeProductAdapter(viewList, new OnFridgeProductClickListener() {
            @Override
            public void onItemClick(FridgeProduct item) {

            }
        });


        _recycleViewAdapter.notifyDataSetChanged();
        _recyclerView.setLayoutManager(_recycleViewLayoutManager);
        _recyclerView.setAdapter(_recycleViewAdapter);

        loadAllProducts();
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

    private void removeProduct(String product_id, int position){

        Call<String> call = productApi.removeProductFromFridge(TOKEN, product_id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    Log.d("Code: ", "" +String.valueOf(response.code()));
                    return;
                }

                System.out.println("Code: " + response.code());

                //responseList.remove()
                //List<Product> tmp = response.body();
                //productsArrayList = (ArrayList<Product>) tmp;
                //_recycleViewAdapter.addAllItems(productsArrayList);

                //responseList = response.body();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Failure: ", "" +t.getMessage());
            }
        });

    }

    private void checkExpireDate(){

    }

    private void chooseProduct(int id){

    }

    private void addItemsToView(){
        String name;
        long daysToExpire;

        int fridgeId;
        String userId;
        String productId;

        String dateAdded;
        String removed;

        for(int j = 0 ; j < productsFridgeArrayList.size(); j++) {
            for (int i = 0; i < productsArrayList.size(); i++) {
                //System.out.println("Fridge Product ID " + productsFridgeArrayList.get(j).getProduct());
                //System.out.println("Product ID " + productsArrayList.get(i).getId());
                if (Integer.valueOf(productsFridgeArrayList.get(j).getProduct()) == productsArrayList.get(i).getId()) {
                    System.out.println(productsArrayList.get(i).name);
                    name = productsArrayList.get(i).name;
                    fridgeId = Integer.valueOf(productsFridgeArrayList.get(j).getId());
                    userId = productsFridgeArrayList.get(j).getUser();
                    productId = productsFridgeArrayList.get(j).getProduct();
                    removed = productsFridgeArrayList.get(j).getRemoved();
                    dateAdded = productsFridgeArrayList.get(j).getDateAdded();
                    //String today = dtf.format(LocalDateTime.now());
                    LocalDate date1  = LocalDate.now();
                    System.out.println(dateAdded);
                    LocalDate date2 = LocalDate.parse(dateAdded);
                    daysToExpire = ChronoUnit.DAYS.between(date1, date2);
                    System.out.println("Days to expire " + daysToExpire);
                    FridgeProduct fridgeProduct = new FridgeProduct(name, (int) daysToExpire, fridgeId,userId,productId,dateAdded, removed);
                    viewList.add(fridgeProduct);
                }
            }
        }
        _recycleViewAdapter.addAllItems(viewList);
    }

    private void loadFridgeProducts(){

        System.out.println(contentType + "\nTOKEN " +TOKEN);
        Call<List<FridgeProductResponse>> call = productApi.getProductsInFridge(contentType, "TOKEN " +TOKEN);
        call.enqueue(new Callback<List<FridgeProductResponse>>() {
            @Override
            public void onResponse(Call<List<FridgeProductResponse>> call, Response<List<FridgeProductResponse>> response) {
                if(!response.isSuccessful()){
                    System.out.println("Code: " +String.valueOf(response.code()));
                    System.out.println("Code: "  +String.valueOf(response.headers()));
                    return;
                }

                Log.d("Respone: ", "" +response.isSuccessful());
                Log.d("Respone: ", "" +response.body());
                Log.d("Respone: ", "" +response.headers().toString());
                List<FridgeProductResponse> tmp = response.body();
                productsFridgeArrayList = (ArrayList) tmp;
                System.out.println("Size: " + productsFridgeArrayList.size());
                addItemsToView();

            }

            @Override
            public void onFailure(Call<List<FridgeProductResponse>> call, Throwable t) {
                Log.d("Failure: ", "" +t.getMessage());
            }
        });

    }

    private void loadAllProducts(){

        Call<List<Product>> call = productApi.getProductsList();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (!response.isSuccessful()) {
                    Log.d("Code: ", String.valueOf(response.code()));
                    return;
                }

                List<Product> tmp = response.body();
                productsArrayList = (ArrayList<Product>) tmp;
                System.out.println("ProductSize: " + productsFridgeArrayList.size());
                //Log.d("Product 3 id: ", String.valueOf(productsArrayList.get(3).getId()));

                loadFridgeProducts();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("Code: ", String.valueOf(t.getMessage()));
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
