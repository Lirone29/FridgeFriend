package com.example.fridgefriend.Model;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {

    private int id;

    private String name;

    private String description;

    private String photoUrl;

    private List<Product> products;


    public List<Product> getProducts() {
        return products;
    }



    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public int getId() {
        return id;
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


}
