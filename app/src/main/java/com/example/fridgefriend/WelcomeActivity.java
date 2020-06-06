package com.example.fridgefriend;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fridgefriend.Authorization.LoginActivity;
import com.example.fridgefriend.Model.Product;
import com.example.fridgefriend.Recipe.RecipeActivity;
import com.example.fridgefriend.Recipe.RecipeByIdActivity;

public class WelcomeActivity extends AppCompatActivity {
    private static int TIME_OUT = 1500;
    private static final String TAG = "WelcomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, RecipeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.xml.fade_in,R.xml.fade_out);
                finish();
            }
        },TIME_OUT);

    }
}
