package com.example.health_care;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.health_care.controllers.UserController;
import com.example.health_care.exceptions.LoggingException;

public class PharmacyPanelActivity extends AppCompatActivity {
    Button logoutButton;

    protected void goToLoginPanel() {
        Intent intent = new Intent(this, PharmacyLoginActivity.class);
        startActivity(intent);
    }

    protected void logout(int id) throws LoggingException {
        UserController.logOutPharmacyAdmin(id);
        Toast toast = Toast.makeText(this, "logout", Toast.LENGTH_SHORT);
        toast.show();
        goToLoginPanel();
    }

    public void logoutError(LoggingException e) {
        Toast toast = Toast.makeText(this, e.getLogoutMessage(), Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_panel_main);

        logoutButton = findViewById(R.id.buttonPharmacyLogout);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    logout();
                } catch (LoggingException e) {
                    logoutError(e);
                }
            }
        });

    }
}
