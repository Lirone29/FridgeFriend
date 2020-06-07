package com.example.fridgefriend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

public class Product {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("daysToExpire")
    @Expose
    public int daysToExpire;

    @SerializedName("weight")
    @Expose
    public int weight;

    @SerializedName("calories")
    @Expose
    public int calories;

   //public Product(){}

    public Product(int id, String name, int daysToExpire, int weight, int calories) {
        this.id = id;
        this.name = name;
        this.daysToExpire = daysToExpire;
        this.weight = weight;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product(String name, int daysToExpire, int weight, int calories) {
        this.name = name;
        this.daysToExpire = daysToExpire;
        this.weight = weight;
        this.calories = calories;

    }

    public String getName() {
        return name;
    }

    public int getDaysToExpire() {
        return daysToExpire;
    }

    public int getWeight() {
        return weight;
    }

    public int getCalories() {
        return calories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDaysToExpire(int daysToExpire) {
        this.daysToExpire = daysToExpire;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

}
