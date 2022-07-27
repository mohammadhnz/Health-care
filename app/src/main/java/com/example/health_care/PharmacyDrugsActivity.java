package com.example.health_care;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health_care.models.Drug;
import com.example.health_care.models.PharmacyAdmin;

import java.text.ParseException;
import java.util.ArrayList;

public class PharmacyDrugsActivity extends AppCompatActivity  implements PharmacyAddPageDrugAdaptor.OnNoteListenerDrug{
    ArrayList<Drug> drugs = new ArrayList<>();
    RecyclerView recyclerView;
    PharmacyAddPageDrugAdaptor drugAdapter;
    PharmacyAdmin user;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug_pharmacy);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        user = PharmacyAdmin.getByInfo(username, password);
        drugs = Drug.getDrugs();
        recyclerView = findViewById(R.id.add_drugs_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        drugAdapter = new PharmacyAddPageDrugAdaptor(this, drugs, this, user);
        recyclerView.setAdapter(drugAdapter);
        drugAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnNoteListenerDrug(ImageView icon, TextView drugName, TextView drugPrice, int position) throws ParseException {

    }
}
