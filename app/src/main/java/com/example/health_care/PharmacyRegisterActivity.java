package com.example.health_care;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.MapView;

public class PharmacyRegisterActivity extends AppCompatActivity {
    Button registerButton;
    EditText username;
    EditText password;
    EditText address;
    EditText name;
    MapView location;
    EditText phone;
    EditText hours;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_register);

        username = findViewById(R.id.editTextPharmacyUsernameRegisteration);
        password = findViewById(R.id.editTextPharmacyPasswordRegister);
        registerButton = findViewById(R.id.buttonPharmacyRegister);
        address = findViewById(R.id.editTextPostalAddress);
        name = findViewById(R.id.editTextPharmacyName);
        location = findViewById(R.id.mapViewPharmacy);
        phone = findViewById(R.id.editTextPhoneNumber);
        hours = findViewById(R.id.editTextPharmacyHours);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
