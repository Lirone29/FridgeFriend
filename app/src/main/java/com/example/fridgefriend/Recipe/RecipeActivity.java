package com.example.fridgefriend.Recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fridgefriend.MainActivity;
import com.example.fridgefriend.Model.Recipe;
import com.example.fridgefriend.Model.Recipes;
import com.example.fridgefriend.Product.CreateProductActivity;
import com.example.fridgefriend.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeActivity extends AppCompatActivity {

    private TextView textViewResult;
    private RecipeApi recipeApi;
    private ArrayList<Recipes> productsArrayList;
    private ListView listView;
    private ListViewAdapter listViewAdapter;
    private Button addRecipeButton;
    private Button returnButton;
    private static final String TAG_TOKEN = "TOKEN";
    String TOKEN = "ca61a446656139a887c2ffff4b0401e8d1b85068";




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        //textViewResult = findViewById(R.id.text_view_result);
        listView = (ListView)findViewById(R.id.listView);
        addRecipeButton = (Button)findViewById(R.id.addRecipeId);
        returnButton = (Button)findViewById(R.id.returnRecipeId) ;


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mtx.pmlabs.net:8888/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recipeApi = retrofit.create(RecipeApi.class);


       getRecipes();

        addRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRecipe();
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnButton();
            }
        });
    }

    private void getRecipes() {

        Call<ArrayList<Recipes>> call = recipeApi.getRecipes();

        call.enqueue(new Callback<ArrayList<Recipes>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipes>>all, Response<ArrayList<Recipes>>response) {
                if (!response.isSuccessful()) {
                 //   textViewResult.setText("Code" + response.code());
                    return;
                }

                productsArrayList = response.body();
                listViewAdapter = new ListViewAdapter( RecipeActivity.this, productsArrayList);
                listView.setAdapter(listViewAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<Recipes>> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
            }
        });

    }

    private void addRecipe(){
        Intent intent = new Intent(getApplicationContext(), AddRecipeActivity.class);
        intent.putExtra(TAG_TOKEN, TOKEN);
        startActivityForResult(intent, 1);
    }
    private void returnButton(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra(TAG_TOKEN, TOKEN);
        startActivityForResult(intent, 1);
        finish();
    }



}
