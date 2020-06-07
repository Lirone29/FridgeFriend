package com.example.fridgefriend.Authorization;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fridgefriend.MainActivity;
import com.example.fridgefriend.Model.Product;
import com.example.fridgefriend.Product.IProduct;
import com.example.fridgefriend.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_SIGNUP = 1;
    private static final String TAG_TOKEN = "TOKEN";
    private String TOKEN;

    private EditText editTextName;
    private EditText editTextPassword;
    private TextView textViewInfo;
    private Button buttonLogin;
    TextView _registerLink;
    private int attemptCounter = 5 ;

    private String urlString = "http://mtx.pmlabs.net:8888/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(urlString)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    LoginApi loginApi = retrofit.create( LoginApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewInfo = (TextView) findViewById(R.id.textViewInfo);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        _registerLink = (TextView) findViewById(R.id.registerViewID);

        textViewInfo.setText("Attempt: 5");

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(editTextName.getText().toString(),editTextPassword.getText().toString());
            }
        });

        _registerLink.setEnabled(true);
        _registerLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Register Activity
                Intent intent = new Intent(getApplicationContext(), RegistryActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
            }
        });

    }

    private void validate(String userName, String userPassword) {

        System.out.println("USERNAME =" + userName);
        System.out.println("PASSWORD =" + userPassword);
        if((userName.equals("")) && (userPassword.equals(""))){
            return;
        }
        LoginRequest loginRequest = new LoginRequest(userName,userPassword);

        Call<LoginResponse> call = loginApi.getToken(userName, userPassword);
        //Call<String> call = loginApi.getToken(loginRequest);

         call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (!response.isSuccessful()) {
                    editTextName.setText("Code: " + response.code());
                    return;
                }

                LoginResponse tmp = response.body();
                TOKEN = tmp.getToken();
                System.out.println("TOKEN IN LOGIN == " + tmp.getToken());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(TAG_TOKEN, tmp.getToken());
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                editTextName.setText(t.getMessage());
            }
        });


    }

    public void openMenu(){
        //TOKEN = "7c1a4c733efb18d3a3dc8bbc4fef0f30274bb52a";
       // System.out.println("TOKEN IN LOGIN 2 = " + TOKEN);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra(TAG_TOKEN, TOKEN);
        startActivityForResult(intent, 2);
        finish();
    }

}
