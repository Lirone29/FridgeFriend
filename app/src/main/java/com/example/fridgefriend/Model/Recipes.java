package com.example.fridgefriend.Model;



public class Recipes {

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
