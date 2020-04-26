package com.example.fridgefriend.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fridgefriend.R;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeActivity extends AppCompatActivity {

    private TextView textViewResult;
    private RecipeApi recipeApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mtx.pmlabs.net:8888/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recipeApi = retrofit.create(RecipeApi.class);

        //getRecipeById(1);

       getRecipes();


    }

    void getRecipes() {

        Call<String> call = recipeApi.getRecipes();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String>all, Response<String>response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code" + response.code());
                    return;
                }

                System.out.println(response.body());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }

    void getRecipeById(int id){

        Call<Recipe> call = recipeApi.getRecipe(id);

        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe>call, Response<Recipe> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code" + response.code());
                    return;
                }
                System.out.println(response.body().getName());

            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }

}
