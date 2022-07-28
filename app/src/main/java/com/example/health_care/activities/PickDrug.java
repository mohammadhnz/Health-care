package com.example.health_care.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.health_care.Exceptions.PharmacyGetDrugsExceptions;
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
        Intent myIntent = getIntent();
        String deleted = myIntent.getStringExtra("deleted");
        loginButton = findViewById(R.id.create_drug_button);
        drugName = findViewById(R.id.drug_name);
        loginButton = findViewById(R.id.create_drug_button);
        if (deleted.equals("1")) {
            loginButton.setText("Deleted Drug");
            loginButton.setBackgroundColor(Color.RED);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String drugNameValue = drugName.getText().toString();
                if (!drugNameValue.isEmpty() && drugNameValue != null) {
                    PharmacyAdmin pharmacy = (PharmacyAdmin) UserController.getInstance().getCurrentUser();
                    if (deleted.equals("1")) {
                        try {
                            pharmacy.getPharmacy().removeDrug(drugNameValue);
                            createToast("Drug has been deleted.");
                            finish();
                        } catch (PharmacyGetDrugsExceptions e) {
                            createToast("Drug does not exists in out drug list.");
                        }
                    } else {
                        Drug drug = Drug.findByName(drugNameValue);
                        if (drug == null) {
                            createToast("Invalid Drug name!");
                            return;
                        }
                        try {
                            pharmacy.getPharmacy().addDrug(drugNameValue);
                        } catch (PharmacyGetDrugsExceptions e) {
                            createToast("Drug does not exists in out drug list.");
                        }
                        finish();
                    }
                } else {
                    createToast("Empty drug name");
                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return true;
    }

    private boolean isEmptyInput(String username, String password) {
        return username.isEmpty() || password.isEmpty();
    }

    private void createToast(String message) {
        Toast toast = Toast.makeText(PickDrug.this, message, Toast.LENGTH_LONG);
        toast.show();
    }

}