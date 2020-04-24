package com.example.fridgefriend;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Button _fridgeButton;
    Button _recipesButton;
    Button _profileButton;
    Button _archieveShoppinListButton;
    Button _createShoppinListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        _fridgeButton = findViewById(R.id.registerButton);
        _recipesButton = findViewById(R.id.recipeButton);
        _profileButton = findViewById(R.id.profileButton);
        _archieveShoppinListButton = findViewById(R.id.shoppingArchiveButton);
        _createShoppinListButton = findViewById(R.id.createShoppingListButton);

        _fridgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FridgeActivity.class);
                startActivityForResult(intent, 1);
                finish();
            }
        });
        _recipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(getApplicationContext(), FridgeActivity.class);
                //startActivityForResult(intent, 1);
                finish();
            }
        });
        _profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(), FridgeActivity.class);
                //startActivityForResult(intent, 1);
                finish();
            }
        });
        _archieveShoppinListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(), FridgeActivity.class);
                //startActivityForResult(intent, 1);
                finish();
            }
        });
        _createShoppinListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(getApplicationContext(), FridgeActivity.class);
                // startActivityForResult(intent, 1);
                finish();
            }
        });

    }
}
