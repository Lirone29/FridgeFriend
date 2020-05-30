package com.example.fridgefriend.Data;

public class PostRegister {

    private String username;

    private String password;

    public PostRegister(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
