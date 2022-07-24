package com.example.health_care;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.health_care.controllers.UserController;
import com.example.health_care.exceptions.LoggingException;
import com.example.health_care.exceptions.RegisteringException;
import com.google.android.gms.maps.MapView;

import javax.security.auth.login.LoginException;

public class PharmacyRegisterActivity extends AppCompatActivity {
    Button registerButton;
    EditText username;
    EditText password;
    EditText address;
    EditText name;
    MapView location;

    protected void showRegisteringError(RegisteringException e) {
        Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
        toast.show();
    }

    protected void showLoginError(LoggingException e) {
        Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
        toast.show();
    }

    protected void register() throws RegisteringException, LoggingException {
        UserController.createPharmacyUser(
                address.toString(),
                name.toString(),
                username.toString(),
                password.toString()
        );
        int id = UserController.pharmacyLogin(username.toString(), password.toString());
        Toast toast = Toast.makeText(this, "registered", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this, PharmacyPanelActivity.class);
        intent.putExtra("pharmacy_login_id", id);
        startActivity(intent);
    }

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

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    register();
                } catch (RegisteringException e) {
                    showRegisteringError(e);
                } catch (LoggingException e) {
                    showLoginError(e);
                }
            }
        });


    }
}
