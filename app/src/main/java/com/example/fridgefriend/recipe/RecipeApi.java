package com.example.fridgefriend.recipe;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeApi {

    @GET("api/recipe/1")
    Call <Recipe> getRecipe();
}

