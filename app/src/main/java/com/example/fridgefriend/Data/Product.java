package com.example.fridgefriend.Data;

public class Product {

    public String name;
    public float calories;
    public float weight;
    public int daysToExpire;
    public int productId;

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

    //@SerializedName("body")
    //private String text;

}
