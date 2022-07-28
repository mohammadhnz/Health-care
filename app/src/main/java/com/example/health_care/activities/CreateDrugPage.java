package com.example.health_care.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health_care.Exceptions.PharmacyLoginException;
import com.example.health_care.R;
import com.example.health_care.models.Drug;

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
        Intent myIntent = getIntent();
        String deleted = myIntent.getStringExtra("deleted");
        if (deleted.equals("1")) {
            drugName.setVisibility(View.INVISIBLE);
            drugPrice.setVisibility(View.INVISIBLE);
            drugDescription.setVisibility(View.INVISIBLE);
            loginButton.setText("Delete Drug");
            loginButton.setBackgroundColor(Color.RED);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String drugNameValue = drugName.getText().toString();
                String drugPriceValue = drugPrice.getText().toString();
                String drugDescriptionValue = drugDescription.getText().toString();
                String drugIdValue = drugId.getText().toString();
                if (deleted.equals("1")) {
                    if (!drugIdValue.isEmpty() && drugIdValue != null) {
                        Drug drug = Drug.removeDrug(drugIdValue);
                        if (drug == null){
                            createToast("There is no drug with this id");
                        }else {
                            createToast("Drug deleted successfully");
                        }
                    }
                    return;
                }
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