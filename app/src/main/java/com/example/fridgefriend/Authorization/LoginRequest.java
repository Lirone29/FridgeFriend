package com.example.fridgefriend.Authorization;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Field;

public class LoginRequest {
    @SerializedName("username")
    String username;

    @SerializedName("password")
    String  password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
