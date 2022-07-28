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
import com.example.health_care.models.Drug;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class PharmacyForCustomerActivity extends AppCompatActivity implements PharmacyMainPageDrugAdapter.OnNoteListenerDrug {
    Button back;
    TextView phone;
    TextView address;
    TextView pharmacyName;
    ArrayList<Drug> drugs = new ArrayList<>();
    RecyclerView recyclerView;
    PharmacyMainPageDrugAdapter drugAdapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_for_customer_panel);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        back = findViewById(R.id.back_button_pharmacy_panel_customer);
        phone = findViewById(R.id.pharmacy_panel_phone_customer);
        address = findViewById(R.id.pharmacy_panel_address_customer);
        pharmacyName = findViewById(R.id.pharmacy_panel_pharmacy_name_customer);
        recyclerView = findViewById(R.id.pharmacy_panel_drugs_customer);


        try {
            drugs = com.example.health_care.controllers.PharmacyController.getPharmacyDrugs(username.toString(), password);
        } catch (PharmacyGetDrugsExceptions e) {
            Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        } catch (NullPointerException e) {
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
            Toast toast = Toast.makeText(PharmacyForCustomerActivity.this, "not found", Toast.LENGTH_SHORT);
            toast.show();
        }
        address.setText(
                info.get("address")
        );
        pharmacyName.setText(
                info.get("pharmacyName")
        );
        phone.setText(
                info.get("phone")
        );

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                startActivity(intent);
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
