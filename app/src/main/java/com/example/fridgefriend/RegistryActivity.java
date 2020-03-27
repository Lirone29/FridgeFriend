package com.example.fridgefriend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class RegistryActivity extends AppCompatActivity {

    private static final String TAG = "RegistryActivity";
    private static final int REQUEST_LOGIN = 1;

    //components
    EditText _loginText;
    EditText _passwordText;
    Button _returnButton;
    Button _registerButton;

    boolean loginExistBoolean = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);

        _loginText = findViewById(R.id.registryUsername);
        _passwordText = findViewById(R.id.registryPassword);
        _returnButton = findViewById(R.id.registryLoginButton);
        _registerButton = findViewById(R.id.registerButton);

        _returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivityForResult(intent, REQUEST_LOGIN);
                finish();
            }
        });
        _registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }



    public void signUp(){

        if (!validate()) {
            onSignUpFailed();
            return;
        }

        //creating new user
       // new CreateUser();
        onSignUpSuccess();
    }

    public boolean validate(){

        //new LoadAllUsers();

        boolean valid=  true;
        String login = _loginText.getText().toString();
        String password = _passwordText.getText().toString();

        //verifying login constrains
        if (login.isEmpty() || login.length() < 5) {
            _loginText.setError("At least 5 characters");
            valid = false;
        } else {
            _loginText.setError(null);
        }

        //verifying login uniques in database
       /*for(int i = 0; i < usersList.size(); i++){
            if(usersList.get(i).get(TAG_LOGIN) == login) {
                valid = false;
                Toast.makeText(getBaseContext(), "Login already exist", Toast.LENGTH_LONG).show();
            }
        }*/

        //verifying password constrains
        if (password.isEmpty() || password.length() < 5 || password.length() > 20) {
            _passwordText.setError("Between 5 and 20 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        return valid;
    }


    public void onSignUpSuccess() {
        _returnButton.setEnabled(false);
        _registerButton.setEnabled(false);
        setResult(RESULT_OK, null);

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivityForResult(intent, REQUEST_LOGIN);
        finish();
    }

    public void onSignUpFailed() {
        Toast.makeText(getBaseContext(), "Registration failed", Toast.LENGTH_LONG).show();
        _returnButton.setEnabled(true);
        _registerButton.setEnabled(true);
    }




}
