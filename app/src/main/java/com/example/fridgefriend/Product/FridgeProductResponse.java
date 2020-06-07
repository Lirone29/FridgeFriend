package com.example.fridgefriend.Product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

public class FridgeProductResponse {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("removed")
    @Expose
    private String removed;

    @SerializedName("dateAdded")
    @Expose
    private String dateAdded;

    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("product")
    @Expose
    private String product;


    public String getDate() {
        return dateAdded;
    }

    public void setDate(String date) {
        this.dateAdded = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public FridgeProductResponse(String id, String removed, String dateAdded, String user, String product) {
        this.id = id;
        this.removed = removed;
        this.dateAdded = dateAdded;
        this.user = user;
        this.product = product;
    }
}
