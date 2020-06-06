package com.example.fridgefriend.Recipe;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fridgefriend.MainActivity;
import com.example.fridgefriend.Model.Product;
import com.example.fridgefriend.Model.Recipe;
import com.example.fridgefriend.R;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeByIdActivity extends AppCompatActivity {

    private static final String TAG_TOKEN = "TOKEN";
    String TOKEN;

    private RecipeApi recipeApi;
    private TextView recipeName;
    private TextView recipeDescription;
    private TextView products;
    private Button returnButton;
    int recipeId;

   //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_by_id);
        Bundle bundle = getIntent().getExtras();
        if(bundle!= null){
            recipeId = bundle.getInt("id");
            TOKEN = bundle.getString(TAG_TOKEN);
        }

        recipeName = (TextView) findViewById(R.id.recipeName);
        recipeDescription = (TextView) findViewById(R.id.decsriptionRecipe) ;
        products = (TextView) findViewById(R.id.productInRecipe) ;
        returnButton = (Button)findViewById(R.id.returnButtonToRecipe);

        Intent intent = getIntent();
        String receivedName =  intent.getStringExtra("name");
        recipeName.setText(receivedName);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mtx.pmlabs.net:8888/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        recipeApi = retrofit.create(RecipeApi.class);
        getRecipeById(recipeId);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnButton();
            }
        });

    }

    ArrayList<Product> productArrayList;

    void getRecipeById(int id){

        Call<Recipe> call = recipeApi.getRecipe(id);

        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe>call, Response<Recipe> response) {
                if (!response.isSuccessful()) {
                  //  textViewResult.setText("Code" + response.code());
                    System.out.println("Code" + response.code());

                    return;
                }

                recipeName.setText(response.body().getName());
                recipeDescription.setText(response.body().getDescription());
                productArrayList= new ArrayList<>(response.body().getProducts());
                for(int i = 0 ; i <productArrayList.size(); i ++){
                    products.setText(productArrayList.get(i).getName());

                }


            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
               // textViewResult.setText(t.getMessage());
                System.out.println("Error: " + t.getMessage());

            }
        });

    }

    private void returnButton(){
        Intent intent = new Intent(getApplicationContext(), RecipeActivity.class);
        intent.putExtra(TAG_TOKEN, TOKEN);
        startActivityForResult(intent, 1);
        finish();
    }
}
