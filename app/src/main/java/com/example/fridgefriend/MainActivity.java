package com.example.fridgefriend;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fridgefriend.Authorization.ProfileActivity;
import com.example.fridgefriend.Product.AddProductActivity;
import com.example.fridgefriend.Recipe.RecipeActivity;
import com.example.fridgefriend.Recipe.AddRecipeActivity;
import com.example.fridgefriend.Recipe.RecipeActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String TAG_TOKEN = "TOKEN";
    private static final int USER_ACTION = 3;
    String TOKEN = "ca61a446656139a887c2ffff4b0401e8d1b85068";

    private String urlString = "http://mtx.pmlabs.net:8888/";

    Button _fridgeButton;
    Button _recipesButton;
    Button _profileButton;
    Button _archieveShoppinListButton;
    FloatingActionButton _createShoppinListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            TOKEN = bundle.getString(TAG_TOKEN);

        initView();

    }

    private void initView() {
        _fridgeButton = findViewById(R.id.fridgeButton);
        _recipesButton = findViewById(R.id.recipeButton);
        _profileButton = findViewById(R.id.profileButton);
        _archieveShoppinListButton = findViewById(R.id.shoppingArchiveButton);
        _createShoppinListButton = (FloatingActionButton) findViewById(R.id.createShoppingListButton);

        _fridgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FridgeActivity.class);
                intent.putExtra(TAG_TOKEN,TOKEN);
                startActivityForResult(intent, 2);
                finish();
            }
        });

        _recipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecipeActivity.class);
                intent.putExtra(TAG_TOKEN,TOKEN);
                startActivityForResult(intent, USER_ACTION);
                finish();
            }
        });

        _profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra(TAG_TOKEN,TOKEN);
                startActivityForResult(intent, USER_ACTION);
                finish();
            }
        });

        _archieveShoppinListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.putExtra(TAG_TOKEN,TOKEN);
                startActivityForResult(intent, 1);
                finish();
            }
        });

        _createShoppinListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(), FridgeActivity.class);
                //intent.putExtra(TAG_TOKEN,TOKEN);
                //startActivityForResult(intent, 1);
                //finish();
            }
        });

    }
}
