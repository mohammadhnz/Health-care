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

import com.example.health_care.Controllers.PharmacyController;
import com.example.health_care.Exceptions.PharmacyLoginException;

public class PharmacyLoginActivity extends AppCompatActivity {
    EditText loginUsername;
    EditText loginPassword;
    TextView redirectText;
    Button loginButton;

    protected void goToRegisteringPanel() {
        Intent intent = new Intent(this, PharmacyRegisterActivity.class);
        startActivity(intent);
    }

    protected void showNotFoundError(PharmacyLoginException e) {
        Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
        toast.show();
    }

    protected void login(String username, String password) {
        try {
            PharmacyController.login(username, password);
            Toast toast = Toast.makeText(this, "login", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(this, PharmacyPanelActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("password", password);
            startActivity(intent);
        } catch (PharmacyLoginException e) {
            showNotFoundError(e);
        }
    }

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
                login(loginUsername.toString(), loginPassword.toString());
            }
        });

        redirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegisteringPanel();
            }
        });


    }
}
