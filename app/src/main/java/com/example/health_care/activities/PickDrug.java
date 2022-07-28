package com.example.health_care.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.health_care.Exceptions.PharmacyGetDrugsExceptions;
import com.example.health_care.Exceptions.PharmacyLoginException;
import com.example.health_care.R;
import com.example.health_care.controllers.UserController;
import com.example.health_care.models.Drug;
import com.example.health_care.models.PharmacyAdmin;

public class PickDrug extends AppCompatActivity {
    EditText drugName;
    AppCompatButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_drug_page);
        findViewById(R.id.drug_id).setVisibility(View.INVISIBLE);
        findViewById(R.id.drug_price_id).setVisibility(View.INVISIBLE);
        findViewById(R.id.drug_desc_id).setVisibility(View.INVISIBLE);
        loginButton = findViewById(R.id.create_drug_button);
        drugName = findViewById(R.id.drug_name);
        loginButton = findViewById(R.id.create_drug_button);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String drugNameValue = drugName.getText().toString();
                if (!drugNameValue.isEmpty() && drugNameValue != null) {
                    Drug drug = Drug.findByName(drugNameValue);
                    if (drug == null) {
                        createToast("Invalid Drug name!");
                        return;
                    }
                    PharmacyAdmin pharmacy = (PharmacyAdmin) UserController.getInstance().getCurrentUser();
                    try {
                        pharmacy.getPharmacy().addDrug(drugNameValue);
                    } catch (PharmacyGetDrugsExceptions e) {
                        e.printStackTrace();
                    }
                    finish();
                } else {
                    createToast("Invalid drug name");
                }
            }
        });
    }


    protected void showNotFoundError(PharmacyLoginException e) {
        Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
        toast.show();
    }

    private boolean isEmptyInput(String username, String password) {
        return username.isEmpty() || password.isEmpty();
    }

    private void createToast(String message) {
        Toast toast = Toast.makeText(PickDrug.this, message, Toast.LENGTH_LONG);
        toast.show();
    }

}