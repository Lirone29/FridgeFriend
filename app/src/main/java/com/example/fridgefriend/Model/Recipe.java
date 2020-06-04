package com.example.fridgefriend.Model;

import com.example.fridgefriend.Product.Product;

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

    public static class Recipes {

        public void setId(int id) {
            this.id = id;
        }

        private int id;

        private String user;

        private String name;

        private String description;

        private int userId;

        private String photo;

        public int getUserId() {
            return userId;
        }

        public String getPhoto() {
            return photo;
        }

        public int getId() {
            return id;
        }

        public String getUser() {
            return user;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }
}
