package com.example.fridgefriend.Product;

public class PostProduct {
    public String name;
    public float calories;
    public float weight;
    public int daysToExpire;

    public PostProduct(String name, float calories, float weight, int daysToExpire) {
        this.name = name;
        this.calories = calories;
        this.weight = weight;
        this.daysToExpire = daysToExpire;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getDaysToExpire() {
        return daysToExpire;
    }

    public void setDaysToExpire(int daysToExpire) {
        this.daysToExpire = daysToExpire;
    }
}
