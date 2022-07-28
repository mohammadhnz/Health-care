package com.example.health_care.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health_care.R;
import com.example.health_care.adapters.SearchPharmacyRecyclerAdapter;
import com.example.health_care.models.Drug;
import com.example.health_care.models.Pharmacy;
import androidx.appcompat.widget.SearchView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

public class DrugPage extends AppCompatActivity implements SearchPharmacyRecyclerAdapter.OnNoteListener{

    TextView drugNameT;
    TextView drugPriceT;
    TextView drugDescription;
    SearchView searchView;
    ImageView drugImage;
    RecyclerView pharmaciesRecycler;
    SearchPharmacyRecyclerAdapter pharmacyAdapter;

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_page);

        drugNameT = findViewById(R.id.drug_page_name_id);
        drugPriceT = findViewById(R.id.drug_page_price);
        drugImage = findViewById(R.id.drug_img_d_recId);
        drugDescription = findViewById(R.id.drug_des_d_id);
        searchView = findViewById(R.id.pharmacy_drug_page_id);
        pharmaciesRecycler = findViewById(R.id.search_result_drug_page);


        Intent intent = getIntent();
        String drugId = intent.getStringExtra("drugId");
        Drug drug = Drug.findDrugById(drugId);
        assert drug != null;
        drugNameT.setText(drug.getName());
        drugPriceT.setText("price: " + String.valueOf(drug.getPrice()));
        drugDescription.setText(drug.getDescription());
        if (drug.getPhoto() != null){
            int drugImg = getResources().getIdentifier("pngitem_479890", "drawable", this.getPackageName());
            drugImage.setImageResource(drugImg);
        }

        searchView.setIconified(false);
        searchView.setIconifiedByDefault(false);

        androidx.appcompat.widget.SearchView.SearchAutoComplete searchAutoComplete = (androidx.appcompat.widget.SearchView.SearchAutoComplete)searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.purple_200));
        searchAutoComplete.setTextColor(getResources().getColor(R.color.purple_700));

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText, drugId);
                return false;
            }
        });

        pharmaciesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        pharmacyAdapter = new SearchPharmacyRecyclerAdapter(this, Pharmacy.pharmaciesWithThisDrug(drugId), this);
        pharmaciesRecycler.setAdapter(pharmacyAdapter);
        pharmacyAdapter.notifyDataSetChanged();

    }

    private void filter(String text, String drug) {

        ArrayList<Pharmacy> filteredlist = new ArrayList<>();

        for (Pharmacy item : Pharmacy.pharmaciesWithThisDrug(drug)) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            pharmacyAdapter.filterList(filteredlist);
        }
    }

    @Override
    public void OnNoteListener(ImageView icon, TextView pharmacyName, int position) throws ParseException {
        // TODO: go to pharmacy page
    }
}