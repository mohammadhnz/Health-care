package com.example.health_care.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    AppCompatButton getStartedBtn;
    ImageView personalInfoIcon;
    ImageView settingIcon;
    Customer customer;
    CustomerMainPagePharmacyAdapter pharmacyAdapter;
    CustomerMainPageDrugAdapter drugAdapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_main_page);

        markedPharmacyRecycler = findViewById(R.id.top_recycler_id);
        markedDrugRecycler = findViewById(R.id.botton_recycler_id);
        getStartedBtn = findViewById(R.id.get_started_button_id);
        personalInfoIcon = findViewById(R.id.persona_icon_id);
        settingIcon = findViewById(R.id.setting_icon_id);

        customer = (Customer) UserController.getInstance().getCurrentUser();

        // TODO: delete below test later
        Pharmacy pharmacy1 = new Pharmacy("ph1", "loc1", "pho1", "op1");
        Pharmacy pharmacy2 = new Pharmacy("ph2", "loc2", "pho2", "op2");
        Pharmacy pharmacy3 = new Pharmacy("ph3", "loc3", "pho3", "op3");
        customer.addBookmarkPharmacy(pharmacy1);
        customer.addBookmarkPharmacy(pharmacy2);
        customer.addBookmarkPharmacy(pharmacy3);

        Drug drug1 = new Drug("id1", "n1", 1.0, "des 1");
        Drug drug2 = new Drug("id1", "n1", 1.0, "des 1");
        Drug drug3 = new Drug("id1", "n1", 1.0, "des 1");
        customer.addBookmarkDrug(drug1);
        customer.addBookmarkDrug(drug1);
        customer.addBookmarkDrug(drug1);

        // TODO: Done above

        markedPharmacyRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        pharmacyAdapter = new CustomerMainPagePharmacyAdapter(this, customer.getCustomerPharmacySearches(), this);
        markedPharmacyRecycler.setAdapter(pharmacyAdapter);
        pharmacyAdapter.notifyDataSetChanged();

        markedDrugRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        drugAdapter = new CustomerMainPageDrugAdapter(this, customer.getCustomerDrugSearches(), this);
        markedDrugRecycler.setAdapter(drugAdapter);
        drugAdapter.notifyDataSetChanged();

    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void OnNoteListener(ImageView icon, TextView pharmacyName, int position) {

    }

    @Override
    public void OnNoteListenerDrug(ImageView icon, TextView drugName, TextView drugPrice, int position) throws ParseException {

    }
}