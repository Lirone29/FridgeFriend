package com.example.fridgefriend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FridgeProduct {

    public String name;
    public int daysToExpire;

    private int fridgeId;
    private String userId;
    private String productId;

    private String dateAdded;
    private String removed;

    public FridgeProduct() {
    }

    public FridgeProduct(String name, int daysToExpire) {
        this.name = name;
        this.daysToExpire = daysToExpire;
    }

    public FridgeProduct(String name, int daysToExpire, int fridgeId, String userId, String productId, String dateAdded, String removed) {
        this.name = name;
        this.daysToExpire = daysToExpire;
        this.fridgeId = fridgeId;
        this.userId = userId;
        this.productId = productId;
        this.dateAdded = dateAdded;
        this.removed = removed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDaysToExpire() {
        return daysToExpire;
    }

    public void setDaysToExpire(int daysToExpire) {
        this.daysToExpire = daysToExpire;
    }
}