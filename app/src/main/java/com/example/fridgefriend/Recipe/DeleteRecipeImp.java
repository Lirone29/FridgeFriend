package com.example.fridgefriend.Recipe;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeleteRecipeImp {

    private RecipeApi recipeApi;
    private int recipeId;

    public DeleteRecipeImp(int recipeId){
        deleteRecipe(recipeId);

    }

    private void deleteRecipe(int recipeId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mtx.pmlabs.net:8888/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recipeApi = retrofit.create(RecipeApi.class);

        DeleteRecipe deleteRecipe = new DeleteRecipe(recipeId) ;

        Call<DeleteRecipe> call = recipeApi.deletePost("ca61a446656139a887c2ffff4b0401e8d1b85068", deleteRecipe);

        call.enqueue(new Callback<DeleteRecipe>() {  @Override
        public void onResponse(Call<DeleteRecipe> call, Response<DeleteRecipe> response) {
            if (!response.isSuccessful()) {
                // textViewResult.setText("Code" + response.code());
                System.out.println("Code" + response.code());
                return;
            }

            DeleteRecipe delete = response.body();

        }

            @Override
            public void onFailure(Call<DeleteRecipe> call, Throwable t) {
                //textViewResult.setText(t.getMessage());
                System.out.println("Error: " + t.getMessage());
            }
        });

    }
}
