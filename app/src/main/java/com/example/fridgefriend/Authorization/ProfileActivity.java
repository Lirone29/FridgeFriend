package com.example.fridgefriend.Authorization;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fridgefriend.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "Profile Activity";
    private static final String TAG_TOKEN = "TOKEN";
    String TOKEN;

    private String urlString = "http://mtx.pmlabs.net:8888/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(urlString)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    LoginApi loginApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_registry);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            TOKEN = bundle.getString(TAG_TOKEN);
    }
}
