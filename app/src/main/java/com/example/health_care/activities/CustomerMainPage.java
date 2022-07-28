package com.example.health_care.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health_care.Exceptions.PharmacyGetDrugsExceptions;
import com.example.health_care.R;
import com.example.health_care.adapters.CustomerMainPageDrugAdapter;
import com.example.health_care.adapters.CustomerMainPagePharmacyAdapter;
import com.example.health_care.controllers.UserController;
import com.example.health_care.models.Customer;
import com.example.health_care.models.Drug;
import com.example.health_care.models.Pharmacy;

import java.text.ParseException;

public class CustomerMainPage extends AppCompatActivity implements CustomerMainPagePharmacyAdapter.OnNoteListener, CustomerMainPageDrugAdapter.OnNoteListenerDrug{
    RecyclerView markedPharmacyRecycler;
    RecyclerView markedDrugRecycler;
    Button getStartedBtn;
    ImageView personalInfoIcon;
    ImageView covid_prediction_icon;
    Customer customer;
    CustomerMainPagePharmacyAdapter pharmacyAdapter;
    CustomerMainPageDrugAdapter drugAdapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_main_page);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        markedPharmacyRecycler = findViewById(R.id.top_recycler_id);
        markedDrugRecycler = findViewById(R.id.botton_recycler_id);
        getStartedBtn = findViewById(R.id.get_started_button_id);
        personalInfoIcon = findViewById(R.id.persona_icon_id);
        covid_prediction_icon = findViewById(R.id.covid_prediction_icon);

        customer = (Customer) UserController.getInstance().getCurrentUser();

        // TODO: delete below test later
        Pharmacy pharmacy1 = new Pharmacy("ph1", "loc1", "pho1", "op1");
        Pharmacy pharmacy2 = new Pharmacy("ph2", "loc2", "pho2", "op2");
        Pharmacy pharmacy3 = new Pharmacy("ph3", "loc3", "pho3", "op3");
        customer.addBookmarkPharmacy(pharmacy1);
        customer.addBookmarkPharmacy(pharmacy2);
        customer.addBookmarkPharmacy(pharmacy3);

        Drug drug1 = new Drug("id1", "n1", 1.0, "des 1");
        Drug drug2 = new Drug("id2", "n2", 2.0, "des 1");
        Drug drug3 = new Drug("id3", "n3", 3.0, "des 1");

        try {
            pharmacy1.addDrugToPharmacy(drug1);
        } catch (PharmacyGetDrugsExceptions pharmacyGetDrugsExceptions) {
            pharmacyGetDrugsExceptions.printStackTrace();
        }
        try {
            pharmacy2.addDrugToPharmacy(drug1);
        } catch (PharmacyGetDrugsExceptions pharmacyGetDrugsExceptions) {
            pharmacyGetDrugsExceptions.printStackTrace();
        }

        customer.addBookmarkDrug(drug1);
        customer.addBookmarkDrug(drug2);
        customer.addBookmarkDrug(drug3);

        // TODO: Done above

        markedPharmacyRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        pharmacyAdapter = new CustomerMainPagePharmacyAdapter(this, customer.getCustomerPharmacySearches(), this);
        markedPharmacyRecycler.setAdapter(pharmacyAdapter);
        pharmacyAdapter.notifyDataSetChanged();

        markedDrugRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        drugAdapter = new CustomerMainPageDrugAdapter(this, customer.getCustomerDrugSearches(), this);
        markedDrugRecycler.setAdapter(drugAdapter);
        drugAdapter.notifyDataSetChanged();

        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerMainPage.this, SearchPage.class);
                startActivity(intent);
            }
        });

        personalInfoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerMainPage.this, UserSettingPage.class);
                startActivity(intent);
            }
        });

        covid_prediction_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerMainPage.this, CoviidQue.class);
                startActivity(intent);
            }
        });

    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void OnNoteListener(ImageView icon, TextView pharmacyName, int position) {
        // TODO: go to pharmacy page
    }

    @Override
    public void OnNoteListenerDrug(ImageView icon, TextView drugName, TextView drugPrice, int position) throws ParseException {
        Toast toast = Toast.makeText(this, "CLICKED", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(
                CustomerMainPage.this,
                DrugPage.class
        );
        Drug drug = customer.getCustomerDrugSearches().get(position);
        Customer customer = (Customer) UserController.getInstance().getCurrentUser();
        customer.addBookmarkDrug(drug);
        intent.putExtra("drugId", String.valueOf(drug.getId()));
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        UserController.getInstance().logout();
        this.finish();
        return true;
    }


}