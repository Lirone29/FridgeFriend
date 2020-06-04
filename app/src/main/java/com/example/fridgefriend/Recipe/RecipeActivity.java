package com.example.fridgefriend.Recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fridgefriend.Model.Recipe;
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
    private ArrayList<Recipe.Recipes> productsArrayList;
    private ListView listView;
    private ListViewAdapter listViewAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        //textViewResult = findViewById(R.id.text_view_result);
        listView = (ListView)findViewById(R.id.listView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mtx.pmlabs.net:8888/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recipeApi = retrofit.create(RecipeApi.class);
       getRecipes();
    }

    void getRecipes() {

        Call<ArrayList<Recipe.Recipes>> call = recipeApi.getRecipes();

        call.enqueue(new Callback<ArrayList<Recipe.Recipes>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe.Recipes>>all, Response<ArrayList<Recipe.Recipes>>response) {
                if (!response.isSuccessful()) {
                 //   textViewResult.setText("Code" + response.code());
                    return;
                }

                productsArrayList = response.body();
                listViewAdapter = new ListViewAdapter( RecipeActivity.this, productsArrayList);
                listView.setAdapter(listViewAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Recipe.Recipes>> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
            }
        });

    }



}
