package com.example.fridgefriend.Data;

import com.google.gson.annotations.SerializedName;

public class PostProduct {
    public String name;
    public float calories;
    public float weight;
    public int daysToExpire;
    public int productId;


    //@SerializedName("body")
    //private String text;

    public String getName() {
        return name;
    }

    public float getCalories() {
        return calories;
    }

    public float getWeight() {
        return weight;
    }

    public int getDaysToExpire() {
        return daysToExpire;
    }

    public int getProductId() {
        return productId;
    }
}
