package com.example.fridgefriend.Product;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridgefriend.Model.Product;
import com.example.fridgefriend.Product.OnProductCardAdapterListener;
import com.example.fridgefriend.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddProductActivity extends AppCompatActivity {

    private static final String TAG_ID = "id";

    private String urlString = "http://mtx.pmlabs.net:8888/";

    //components
    FloatingActionButton _addGroupButton;

    private SearchView _searchView;
    private RecyclerView _recyclerView;  //ListView
   /// private ProductCardAdapter adapter;
    private TextView _searchProductTextView;
    private Button _searchButton;

    ProductCardAdapter  _recycleViewAdapter;
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

        _recyclerView =  (RecyclerView) findViewById(R.id.addProductListId);
        _recyclerView.setHasFixedSize(true);

        _recycleViewLayoutManager = new LinearLayoutManager(this);
        _recyclerView.setEnabled(true);
        _recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        loadAllProducts();

        _recycleViewAdapter = new ProductCardAdapter(productsArrayList, new OnProductCardAdapterListener() {
            @Override
            public void onItemClick(Product item) {

                chooseProduct(0);
                //Intent intent = new Intent(getApplicationContext(), ProductsActivity.class);
                //intent.putExtra(TAG_ID,0);
                //startActivity(intent);
                //finish();
            }
        });

        _recycleViewAdapter.notifyDataSetChanged();
        _recyclerView.setLayoutManager(_recycleViewLayoutManager);
        _recyclerView.setAdapter(_recycleViewAdapter);

       // loadAllProducts();



    }


    public void loadAllProducts(){
        Call<List<Product>> call = productApi.getProductsList();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (!response.isSuccessful()) {
                    _searchProductTextView.setText("Code: " + response.code());
                    return;
                }

                List<Product> tmp = response.body();
                productsArrayList = (ArrayList<Product>) tmp;
                _recycleViewAdapter.addAllItems(productsArrayList);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                _searchProductTextView.setText(t.getMessage());
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

                //_recycleViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                _searchProductTextView.setText(t.getMessage());
            }
        });
    }

    public void chooseProduct(int _id){

        System.out.println("IN choose");
        //Intent intent = new Intent(getApplicationContext(), ProductsActivity.class);
        //intent.putExtra(TAG_ID,_id);
       //startActivity(intent);
        //finish();

    }

    public void searchProduct(){

        //Log.d("All Products: ", productsArrayList.get(1).getName());
        String productText = _searchProductTextView.getText().toString();
        for(int i = 0 ; i < productsArrayList.size(); i++){
            if(productsArrayList.get(i).getName().equals(productText)) {
                //Log.d("Indeks of kuczak ", String.valueOf(i+1));
                getProductsById(i+1);
            }
        }

    }
}
