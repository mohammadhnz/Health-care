package com.example.health_care;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health_care.Exceptions.PharmacyGetDrugsExceptions;
import com.example.health_care.Exceptions.PharmacyLogoutException;
import com.example.health_care.activities.LoginPage;
import com.example.health_care.adapters.CustomerMainPageDrugAdapter;
import com.example.health_care.adapters.CustomerMainPagePharmacyAdapter;
import com.example.health_care.models.Drug;
import com.example.health_care.models.PharmacyAdmin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class PharmacyPanelInfoActivity extends AppCompatActivity implements PharmacyMainPageDrugAdapter.OnNoteListenerDrug {
    TextView username;
    TextView fullName;
    String password;
    TextView phone;
    TextView address;
    TextView pharmacyName;
    Button logout;
    Button addDrug;
    ArrayList<Drug> drugs = new ArrayList<>();
    RecyclerView recyclerView;
    PharmacyMainPageDrugAdapter drugAdapter;


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_panel_info);
        Intent intent = getIntent();
        username = findViewById(R.id.pharmacy_panel_username);
        fullName = findViewById(R.id.pharmacy_panel_name);
        pharmacyName = findViewById(R.id.pharmacy_panel_pharmacy_name);
        phone = findViewById(R.id.pharmacy_panel_phone);
        address = findViewById(R.id.pharmacy_panel_address);
        logout = findViewById(R.id.logout_button_pharmacy_panel);
        password = intent.getStringExtra("password").toString();
        addDrug = findViewById(R.id.add_drug_button_pharmacy_panel);
        recyclerView = findViewById(R.id.pharmacy_panel_drugs);
        username.setText(
                intent.getStringExtra("username").toString()
        );

        try {
            drugs = com.example.health_care.controllers.PharmacyController.getPharmacyDrugs(username.getText().toString(), password);
        } catch (PharmacyGetDrugsExceptions e) {
            Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        } catch (NullPointerException e){
            Log.d("error", "onCreate: salam");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        drugAdapter = new PharmacyMainPageDrugAdapter(this, drugs, this);
        recyclerView.setAdapter(drugAdapter);
        drugAdapter.notifyDataSetChanged();
        HashMap<String, String> info = new HashMap<>();
        try {
            info = com.example.health_care.controllers.PharmacyController.getUserInfo(intent.getStringExtra("username"), intent.getStringExtra("password"));
        } catch (PharmacyGetDrugsExceptions pharmacyGetDrugsExceptions) {
            Toast toast = Toast.makeText(PharmacyPanelInfoActivity.this, "not found", Toast.LENGTH_SHORT);
            toast.show();
        }
        username.setText(
                info.get("username")
        );
        fullName.setText(
                info.get("fullName")
        );

        address.setText(
                info.get("address")
        );
        pharmacyName.setText(
                info.get("pharmacyName")
        );
        phone.setText(
                info.get("phone")
        );

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    com.example.health_care.controllers.PharmacyController.logout(username.getText().toString(), password);
                    Intent intent1 = new Intent(PharmacyPanelInfoActivity.this, LoginPage.class);
                    startActivity(intent1);
                } catch (PharmacyLogoutException e) {
                    Toast toast = Toast.makeText(PharmacyPanelInfoActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        addDrug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PharmacyPanelInfoActivity.this, PharmacyDrugsActivity.class);
                intent1.putExtra("username", username.getText().toString());
                intent1.putExtra("password",password);
                startActivityForResult(intent1, 200);
            }
        });


    }

    @Override
    public void OnNoteListenerDrug(ImageView icon, TextView drugName, TextView drugPrice, int position, String id) throws ParseException {
        //TODO: go to drug detail acivity
        /*
        Intent intent = new Intent(PharmacyPanelInfoActivity.this, );
        intent.putExtra("id", id);
        startActivity(intent);
         */
    }
}
