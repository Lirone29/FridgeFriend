package com.example.fridgefriend.Model;

import com.google.gson.annotations.SerializedName;

public class ShoppingListProduct
{
    @SerializedName("id")
    private int id;

    @SerializedName("bought")
    private boolean bought;

    @SerializedName("name")
    private String name;

    @SerializedName("weight")
    private int weight;

    @SerializedName("calories")
    private int calories;


    public int getCalories() {
        return calories;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public boolean isBought() {
        return bought;
    }

    public int getId() {
        return id;
    }
}
