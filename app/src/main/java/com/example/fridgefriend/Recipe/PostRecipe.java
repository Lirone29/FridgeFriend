package com.example.fridgefriend.Recipe;

public class PostRecipe {

    private String name;

    private String description;


    public PostRecipe(String name, String description) {
        this.name = name;
        this.description = description;
      //  this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


}
