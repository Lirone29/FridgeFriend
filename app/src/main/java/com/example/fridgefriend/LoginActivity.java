package com.example.fridgefriend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_SIGNUP = 1;

    private EditText editTextName;
    private EditText editTextPassword;
    private TextView textViewInfo;
    private Button buttonLogin;
    TextView _registerLink;
    private int attemptCounter = 5 ;

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

        if((userName.equals("admin")) && (userPassword.equals("admin"))){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        else {
            attemptCounter--;
            textViewInfo.setText(String.valueOf("Attempt:" + attemptCounter));
            if(attemptCounter==0){
                buttonLogin.setEnabled(false);
            }
        }

    }
}
