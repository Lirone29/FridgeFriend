package com.example.fridgefriend.Product;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fridgefriend.Data.Dialog;
import com.example.fridgefriend.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateProductActivity extends AppCompatActivity {

    String baseUrl = "http://mtx.pmlabs.net:8888/";
    private static final String TAG_TOKEN = "TOKEN";
    String TOKEN;

    EditText _name;
    EditText _weight;
    EditText _dateOfExpiration;
    EditText _calories;
    private Button _createButton;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    IProduct productApi;

    String name;
    Float weight;
    int dateOfExpiration;
    Float calories;
    String token;

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
        productApi = retrofit.create(IProduct.class);

    }

    public void createNewProduct() {
        name = _name.getText().toString();
        weight = Float.valueOf(_weight.getText().toString());
        dateOfExpiration = Integer.valueOf(_dateOfExpiration.getText().toString());
        calories = Float.valueOf(_calories.getText().toString());

        if(TextUtils.isEmpty(name)) {
            return;
        }

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
                System.out.println("Code" + response.code());
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
}
