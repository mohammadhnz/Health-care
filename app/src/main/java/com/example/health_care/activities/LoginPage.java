package com.example.health_care.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.health_care.R;

public class LoginPage extends AppCompatActivity {
    EditText login_username;
    EditText login_password;
    AppCompatButton login_button;
    TextView alreadyHavAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        login_username = findViewById(R.id.login_username_id);
        login_password = findViewById(R.id.login_password_id);
        login_button = findViewById(R.id.login_button_id);
        alreadyHavAcc = findViewById(R.id.already_hav);


    }
}