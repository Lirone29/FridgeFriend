package com.example.fridgefriend.Authorization;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fridgefriend.Authorization.LoginActivity;
import com.example.fridgefriend.Model.Product;
import com.example.fridgefriend.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistryActivity extends AppCompatActivity {

    private static final String TAG = "RegistryActivity";
    private static final int REQUEST_LOGIN = 1;

    //components
    EditText _loginText;
    EditText _passwordText;
    Button _returnButton;
    Button _registerButton;

    boolean loginExistBoolean = false;

    private String urlString = "http://mtx.pmlabs.net:8888/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(urlString)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    LoginApi loginApi;

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
        loginApi =  retrofit.create(LoginApi.class);
    }



    public void signUp(){

        if (!validate()) {
            onSignUpFailed();
            return;
        }

        //creating new user
        createUser(this._loginText.getText().toString(), this._passwordText.getText().toString());
    }

    public void createUser(String username, String password){

        Call<String> call = loginApi.createUser(username, password);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (!response.isSuccessful()) {
                    _loginText.setText("Code: " + response.code());
                    return;
                }

                String answer = response.body();

                Log.d("All Products: ", answer);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                _loginText.setText(t.getMessage());
            }
        });

        onSignUpSuccess();
    }

    public boolean validate(){

        //new LoadAllUsers();

        boolean valid=  true;
        String login = _loginText.getText().toString();
        String password = _passwordText.getText().toString();

        //verifying login constrains
        if (login.isEmpty() || login.length() < 8 || login.length() > 30) {
            _loginText.setError("Between 8 and 30 alphanumeric characters");
            valid = false;
        } else {
            _loginText.setError(null);
        }

        //verifying password constrains
        if (password.isEmpty() || password.length() < 8 || password.length() > 30) {
            _passwordText.setError("Between 8 and 30 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        //verifying login uniques in database
       /*for(int i = 0; i < usersList.size(); i++){
            if(usersList.get(i).get(TAG_LOGIN) == login) {
                valid = false;
                Toast.makeText(getBaseContext(), "Login already exist", Toast.LENGTH_LONG).show();
            }
        }*/


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
