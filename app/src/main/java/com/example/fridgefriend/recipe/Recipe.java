package com.example.fridgefriend.recipe;

import java.io.Serializable;

public class Recipe implements Serializable {

    private int id;

    private String name;

    private String description;

    private String photoUrl;

    private String products;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getProducts() {
        return products;
    }

    public int getUser() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(String user) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setProducts(String products) {
        this.products = products;
    }
}
