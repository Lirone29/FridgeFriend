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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();

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


        if((userName.equals("")) && (userPassword.equals(""))){
            return;
        }


        //Call<String> call = loginApi.getToken(userName, userPassword);

        /*call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (!response.isSuccessful()) {
                    editTextName.setText("Code: " + response.code());
                    return;
                }

                String tmp = response.body();

                TOKEN = tmp;
                //productsArrayList = (ArrayList<Product>) tmp;
                //_recycleViewAdapter.addAllItems(productsArrayList);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                editTextName.setText(t.getMessage());
            }
        });*/


    }

    public void openMenu(){
        //String TOKEN = "ca61a446656139a887c2ffff4b0401e8d1b85068";
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra(TAG_TOKEN, "ca61a446656139a887c2ffff4b0401e8d1b85068");
        startActivityForResult(intent, 2);
        finish();
    }

}
