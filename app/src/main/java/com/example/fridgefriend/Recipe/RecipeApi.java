package com.example.fridgefriend.Recipe;

import com.example.fridgefriend.Model.Recipe;
import com.example.fridgefriend.Model.Recipes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeApi {

    @GET("api/recipe/")
    Call <Recipe> getRecipe(@Query("id") int id );

    @GET("api/recipes/?page=1")
    Call<ArrayList<Recipes>>  getRecipes() ;

    @Headers({
            "Content-Type: application/json",
            "Authorization: Token 82dae18b776fe80c6d299b59627249f1ef57fcf4"
    })
    @POST("api/recipe/")
    Call<PostRecipe> createPost(@Header ("Token") String token, @Body PostRecipe postRecipe);

}

