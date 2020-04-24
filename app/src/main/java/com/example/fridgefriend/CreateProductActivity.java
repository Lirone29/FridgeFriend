package com.example.fridgefriend;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateProductActivity extends AppCompatActivity {

    EditText _name;
    EditText _weight;
    EditText _dateOfExpiration;
    EditText _calories;
    private Button _createButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
        initView();

    }

    public void initView(){
        _name = findViewById(R.id.productName);
        _weight = findViewById(R.id.productWeight);
        _dateOfExpiration = findViewById(R.id.productExpirationDate);
        _calories = findViewById(R.id.productCalories);

    }
}
