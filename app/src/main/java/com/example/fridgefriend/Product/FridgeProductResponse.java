package com.example.fridgefriend.Product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

public class FridgeProductResponse {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("product_id")
    @Expose
    private String product_id;

    @SerializedName("removed")
    @Expose
    private boolean removed;

    @SerializedName("dateAdded")
    @Expose
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public FridgeProductResponse(int id, String user_id, String product_id) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
    }

    public FridgeProductResponse(int id, String user_id, String product_id, String date) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.date = date;
    }

    public FridgeProductResponse(int id, String user_id, String product_id, boolean removed, String date) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.removed = removed;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
