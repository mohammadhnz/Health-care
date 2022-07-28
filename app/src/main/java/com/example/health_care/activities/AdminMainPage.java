package com.example.health_care.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.health_care.R;
import com.example.health_care.adapters.CustomerMainPageDrugAdapter;
import com.example.health_care.adapters.CustomerMainPagePharmacyAdapter;
import com.example.health_care.controllers.UserController;
import com.example.health_care.models.Admin;
import com.example.health_care.models.Drug;

import java.text.ParseException;

public class AdminMainPage extends AppCompatActivity implements CustomerMainPagePharmacyAdapter.OnNoteListener, CustomerMainPageDrugAdapter.OnNoteListenerDrug {
    RecyclerView markedPharmacyRecycler;
    RecyclerView markedDrugRecycler;
    Button getStartedBtn;
    Button createDrugBtn;
    Button deleteDrugBtn;
    ImageView personalInfoIcon;
    ImageView settingIcon;
    Admin admin;
    CustomerMainPageDrugAdapter drugAdapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_page);

        markedPharmacyRecycler = findViewById(R.id.top_recycler_id);
        getStartedBtn = findViewById(R.id.get_started_button_id);
        createDrugBtn = findViewById(R.id.create_drug);
        personalInfoIcon = findViewById(R.id.persona_icon_id);
        settingIcon = findViewById(R.id.setting_icon_id);
        deleteDrugBtn = findViewById(R.id.delete_drug);
        admin = (Admin) UserController.getInstance().getCurrentUser();
        Drug drug1 = new Drug("id1", "n1", 1.0, "des 1");
        Drug drug2 = new Drug("id2", "n2", 2.0, "des 1");
        Drug drug3 = new Drug("id3", "n3", 3.0, "des 1");
        markedPharmacyRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        drugAdapter = new CustomerMainPageDrugAdapter(this, Drug.getDrugs(), this);
        markedPharmacyRecycler.setAdapter(drugAdapter);
        drugAdapter.notifyDataSetChanged();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        createDrugBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMainPage.this, CreateDrugPage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("deleted", "0");
                startActivity(intent);
            }
        });
        deleteDrugBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMainPage.this, CreateDrugPage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("deleted", "1");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onRestart() {
        drugAdapter.notifyDataSetChanged();
        super.onRestart();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        UserController.getInstance().logout();
        this.finish();
        return true;
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void OnNoteListener(ImageView icon, TextView pharmacyName, int position) {

    }

    @Override
    public void OnNoteListenerDrug(ImageView icon, TextView drugName, TextView drugPrice, int position) throws ParseException {

    }
}