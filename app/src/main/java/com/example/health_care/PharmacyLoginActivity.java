package com.example.health_care;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PharmacyLoginActivity extends AppCompatActivity {
    EditText loginUsername;
    EditText loginPassword;
    TextView redirectText;
    Button loginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_login);
        loginButton = findViewById(R.id.buttonPharmacyLogin);
        loginPassword = findViewById(R.id.editTextPharmacyPassword);
        loginUsername = findViewById(R.id.editTextPharmacyUsername);
        redirectText = findViewById(R.id.textViewPharmacyRegisterRedirect);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        redirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
