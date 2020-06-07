package com.example.fridgefriend.Product;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridgefriend.Data.Dialog;
import com.example.fridgefriend.FridgeActivity;
import com.example.fridgefriend.MainActivity;
import com.example.fridgefriend.Model.Product;
import com.example.fridgefriend.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class CreateProductActivity extends AppCompatActivity {

    String baseUrl = "http://mtx.pmlabs.net:8888/";
    private static final String TAG_TOKEN = "TOKEN";
    String TOKEN;

    EditText _name;
    EditText _weight;
    EditText _dateOfExpiration;
    EditText _calories;
    private Button _createButton;
    private Button _returnButton;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    IProduct productApi;

    String name;
    int weight;
    int dateOfExpiration;
    int calories;

    //components
    RecyclerView _recyclerView;
    FridgeProductAdapter _recycleViewAdapter;
    RecyclerView.LayoutManager _recycleViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            TOKEN = bundle.getString(TAG_TOKEN);
        initView();
    }

    public void initView() {
        _name = findViewById(R.id.productName);
        _weight = findViewById(R.id.productWeight);
        _dateOfExpiration = findViewById(R.id.productExpirationDate);
        _calories = findViewById(R.id.productCalories);

        _createButton = findViewById(R.id.createProductButton);
        _createButton.setEnabled(true);
        _createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewProduct();
            }
        });

        _returnButton = findViewById(R.id.createProductReturnButton);
        _returnButton.setEnabled(true);
        _returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnButton();
            }
        });

        productApi = retrofit.create(IProduct.class);



    }

    public void createNewProduct() {
        name = _name.getText().toString();
        weight = Integer.valueOf(_weight.getText().toString());
        dateOfExpiration = Integer.valueOf(_dateOfExpiration.getText().toString());
        calories = Integer.valueOf(_calories.getText().toString());

        if(TextUtils.isEmpty(name)) {
            return;
        }


        System.out.println(TOKEN);
        System.out.println(name);
        System.out.println(weight);
        System.out.println(dateOfExpiration);
        System.out.println(calories);
        PostProduct postProduct = new PostProduct(name, weight, calories, dateOfExpiration);

        Call<PostProduct> call = productApi.createPost(TOKEN, postProduct);

        call.enqueue(new Callback<PostProduct>() {
            @Override
            public void onResponse(Call<PostProduct> call, Response<PostProduct> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Code" + response.code());
                    return;
                }

                PostProduct postResponse = response.body();
                System.out.println("Code " + response.code());
                openDialog();
            }

            @Override
            public void onFailure(Call<PostProduct> call, Throwable t) {
                //textViewResult.setText(t.getMessage());
                System.out.println("Error: " + t.getMessage());
            }
        });

    }

    private void openDialog() {
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    private void returnButton(){
        Intent intent = new Intent(getApplicationContext(), FridgeActivity.class);
        intent.putExtra(TAG_TOKEN, TOKEN);
        startActivityForResult(intent, 1);
        finish();
    }
}
