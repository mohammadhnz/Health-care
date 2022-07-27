package com.example.health_care.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health_care.Exceptions.PharmacyLoginException;
import com.example.health_care.PharmacyPanelActivity;
import com.example.health_care.PharmacyRegisterActivity;
import com.example.health_care.R;
import com.example.health_care.controllers.Exceptions.LoginExceptions;
import com.example.health_care.controllers.Exceptions.LoginOnceException;
import com.example.health_care.controllers.PharmacyController;
import com.example.health_care.controllers.UserController;
import com.example.health_care.models.Drug;
import com.example.health_care.models.Pharmacy;

public class CreateDrugPage extends AppCompatActivity {
    EditText drugName;
    EditText drugId;
    AppCompatButton loginButton;
    TextView drugPrice;
    TextView drugDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_drug_page);

        drugName = findViewById(R.id.drug_name);
        drugId = findViewById(R.id.drug_id);
        drugPrice = findViewById(R.id.drug_price_id);
        drugDescription = findViewById(R.id.drug_desc_id);
        loginButton = findViewById(R.id.create_drug_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String drugNameValue = drugName.getText().toString();
                String drugPriceValue = drugPrice.getText().toString();
                String drugDescriptionValue = drugDescription.getText().toString();
                String drugIdValue = drugId.getText().toString();
                if (validateData(drugNameValue, drugPriceValue, drugDescriptionValue, drugIdValue)) {
                    new Drug(drugIdValue, drugNameValue, Float.parseFloat(drugPriceValue), drugDescriptionValue);
                    Toast toast = Toast.makeText(CreateDrugPage.this, "drug has been created.", Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                }
            }
        });
    }

    private boolean validateData(String drugNameValue, String drugPriceValue, String drugDescriptionValue, String drugIdValue) {
        if (isEmptyInput(drugNameValue, drugDescriptionValue)) {
            Toast toast = Toast.makeText(
                    CreateDrugPage.this,
                    "please enter valid name and description",
                    Toast.LENGTH_LONG
            );
            toast.show();
            return false;
        }
        try {
            float number = Float.parseFloat(drugPriceValue);
        } catch (Exception e) {
            createToast("Wrong Price!");
            return false;
        }
        try {
            float number = Integer.parseInt(drugIdValue);
        } catch (Exception e) {
            createToast("Wrong Id!");
            return false;
        }
        return true;
    }


    protected void showNotFoundError(PharmacyLoginException e) {
        Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
        toast.show();
    }

    private boolean isEmptyInput(String username, String password) {
        return username.isEmpty() || password.isEmpty();
    }

    private void createToast(String message) {
        Toast toast = Toast.makeText(CreateDrugPage.this, message, Toast.LENGTH_LONG);
        toast.show();
    }

}