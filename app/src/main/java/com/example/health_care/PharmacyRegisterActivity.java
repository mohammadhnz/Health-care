package com.example.health_care;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.health_care.Exceptions.PharmacyRegisterException;
import com.example.health_care.controllers.Exceptions.LoginExceptions;
import com.example.health_care.controllers.Exceptions.LoginOnceException;
import com.example.health_care.controllers.PharmacyController;
import com.example.health_care.controllers.UserController;

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
                    UserController.getInstance().login(username.getText().toString(), password.getText().toString());
                    Intent intent = new Intent(PharmacyRegisterActivity.this, PharmacyPanelInfoActivity.class);
                    startActivity(intent);
                } catch (PharmacyRegisterException e) {
                    Toast toast = Toast.makeText(PharmacyRegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG);
                    toast.show();
                } catch (LoginOnceException e) {
                    e.printStackTrace();
                } catch (LoginExceptions loginExceptions) {
                    loginExceptions.printStackTrace();
                }
            }
        });

    }
}
