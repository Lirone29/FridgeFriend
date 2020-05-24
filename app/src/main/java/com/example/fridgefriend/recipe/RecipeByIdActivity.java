package com.example.fridgefriend.recipe;

import androidx.appcompat.app.AppCompatActivity;
import com.example.fridgefriend.R;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeByIdActivity extends AppCompatActivity {

    private RecipeApi recipeApi;
    private TextView recipeName;
    private TextView recipeDescription;
    private TextView products;

   //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_by_id);

        recipeName = (TextView) findViewById(R.id.recipeName);
        recipeDescription = (TextView) findViewById(R.id.decsriptionRecipe) ;
        products = (TextView) findViewById(R.id.productInRecipe) ;


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mtx.pmlabs.net:8888/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recipeApi = retrofit.create(RecipeApi.class);
        //getRecipeById();

    }


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
                System.out.println(response.body().getName());


            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
               // textViewResult.setText(t.getMessage());
                System.out.println("Error: " + t.getMessage());

            }
        });

    }
}
