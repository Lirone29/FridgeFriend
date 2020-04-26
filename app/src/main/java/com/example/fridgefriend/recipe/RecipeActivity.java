package com.example.fridgefriend.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fridgefriend.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeActivity extends AppCompatActivity {

    private TextView textViewResult;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://mtx.pmlabs.net:8888/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    final RecipeApi recipeApi = retrofit.create(RecipeApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        textViewResult = findViewById(R.id.text_view_result);


        //GetRecipeById();

        GetRecipes();
    }

    void GetRecipes() {

        Call<String> call = recipeApi.getRecipes();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String>all, Response<String> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code" + response.code());
                    return;
                }

                System.out.println("Karolina");
                System.out.println(response.body());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                textViewResult.setText(t.getMessage());
                System.out.println("Karolinaaaaaa");
            }
        });

    }

    void GetRecipeById(){

        Call<Recipe> call = recipeApi.getRecipe();

        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe>call, Response<Recipe> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code" + response.code());
                    return;
                }

                System.out.println("Karolina");
                System.out.println(response.body().getName());

            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                textViewResult.setText(t.getMessage());
                System.out.println("Karolinaaaaaa");
            }
        });

    }
}
