package com.example.health_care;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.health_care.activities.CustomerMainPage;
import com.example.health_care.activities.LoginPage;
import com.example.health_care.activities.SignInPage;
import com.example.health_care.controllers.PharmacyController;
import com.example.health_care.Exceptions.PharmacyAdminRegisterException;
import com.example.health_care.Exceptions.PharmacyRegisterException;
import com.example.health_care.models.Admin;
import com.example.health_care.models.User;
import com.google.android.gms.maps.MapView;

public class PharmacyRegisterActivity extends AppCompatActivity {
    EditText username;
    EditText firstName;
    EditText lastName;
    EditText phone;
    EditText pharmacyName;
    EditText pharmacyAddress;
    EditText email;
    EditText password;
    EditText validationKey;
    TextView loginBack;
    EditText hours;
    Button registerButton;
    TextView alreadyHavAcc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_register);

        username = findViewById(R.id.editTextPharmacyUsername);
        password = findViewById(R.id.editTextPharmacyPassword);
        firstName = findViewById(R.id.editTextTextPersonName);
        lastName = findViewById(R.id.editTextTextPersonLastName);
        phone = findViewById(R.id.editTextPharmacyPhone);
        pharmacyName = findViewById(R.id.editTextPharmacyName);
        pharmacyAddress = findViewById(R.id.editTextPharmacyAddress);
        email = findViewById(R.id.editTextPharmacyEmail);
        registerButton = findViewById(R.id.buttonRegisterPharmacy);
        hours = findViewById(R.id.editTextPharmacyHours);
        loginBack = findViewById(R.id.textViewPharmacyRegisterBack);

        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PharmacyRegisterActivity.this, LoginPage.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    PharmacyController.create(
                            username.getText().toString(),
                            password.getText().toString(),
                            firstName.getText().toString(),
                            lastName.getText().toString(),
                            pharmacyAddress.getText().toString(),
                            phone.getText().toString(),
                            pharmacyName.getText().toString(),
                            email.getText().toString(),
                            hours.getText().toString()
                    );
                    Intent intent = new Intent(PharmacyRegisterActivity.this, PharmacyPanelInfoActivity.class);
                    intent.putExtra("username", username.getText().toString());
                    intent.putExtra("password", password.getText().toString());
                    startActivity(intent);
                } catch (PharmacyRegisterException e) {
                    Toast toast = Toast.makeText(PharmacyRegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }
}
