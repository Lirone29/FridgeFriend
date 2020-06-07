package com.example.fridgefriend;

import com.example.fridgefriend.Model.Product;
import com.example.fridgefriend.Model.Recipes;
import com.example.fridgefriend.Model.ShoppingList;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ShoppingListApi {


    @GET("api/recipes/?page=1")
    Call<ArrayList<Recipes>>  getRecipes() ;

    @Headers({
            "Content-Type: application/json",
            "Authorization: Token 82dae18b776fe80c6d299b59627249f1ef57fcf4"
    })
    @GET("api/shopping-list/?id=1")
    Call<ShoppingList>  getShoppingList() ;

    @Headers({
            "Content-Type: application/json",
            "Authorization: Token 82dae18b776fe80c6d299b59627249f1ef57fcf4"
    })
    @POST("api/add-to-list/")
    Call<ShoppingListPostItem>  postShoppingListItem(@Body ShoppingListPostItem postItem) ;

    @Headers({
            "Content-Type: application/json",
            "Authorization: Token 82dae18b776fe80c6d299b59627249f1ef57fcf4"
    })
    @POST("api/shopping-list-product/")
    Call<ShoppingListBoughtItem>  postShoppingListBoughtItem(@Body ShoppingListBoughtItem postItem) ;

    @GET("api/products")
    Call<List<Product>> getProductsList();

}

class ShoppingListPostItem{

    @SerializedName("list_id")
    int list_id;

    @SerializedName("product_id")
    int product_id;

    ShoppingListPostItem(int list_id, int product_id){
       this.list_id = list_id;
       this.product_id = product_id;
    }
}

class ShoppingListBoughtItem{

    @SerializedName("shopping_list_product_id")
    String shopping_list_product_id;

    @SerializedName("is_bought")
    Boolean is_bought;

    ShoppingListBoughtItem(Integer list_id, Boolean is_bought){
        this.shopping_list_product_id = list_id.toString();
        this.is_bought = is_bought;
    }
}