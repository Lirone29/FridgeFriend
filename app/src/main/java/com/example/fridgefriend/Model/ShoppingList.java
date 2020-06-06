package com.example.fridgefriend.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShoppingList {

    @SerializedName("name")
    private String name;

    @SerializedName("is_archive")
    private  Boolean is_archive;

    @SerializedName("is_favorite")
    private  Boolean is_favorite;;

    @SerializedName("products")
    private List<ShoppingListProduct> products;

    public String getName() {return name;}

    public List<ShoppingListProduct> getProducts() {return products;}

    public void setProducts(List<ShoppingListProduct> products) {
        this.products = products;
    }
}

