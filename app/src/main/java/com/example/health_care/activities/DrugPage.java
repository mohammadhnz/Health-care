package com.example.health_care.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health_care.R;
import com.example.health_care.controllers.UserController;
import com.example.health_care.models.Drug;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

public class DrugPage extends AppCompatActivity {

    EditText drugNameT;
    EditText drugPriceT;
    EditText drugDescription;
    SearchView searchView;
    ImageView drugImage;
    RecyclerView pharmaciesRecycler;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_page);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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
        if (drug.getPhoto().isEmpty()){
            int drugImg = getResources().getIdentifier("pngitem_479890", "drawable", this.getPackageName());
            drugImage.setImageResource(drugImg);
        }

        searchView.setIconified(false);
        searchView.setIconifiedByDefault(false);

        androidx.appcompat.widget.SearchView.SearchAutoComplete searchAutoComplete = (androidx.appcompat.widget.SearchView.SearchAutoComplete)searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.purple_200));
        searchAutoComplete.setTextColor(getResources().getColor(R.color.purple_700));

//        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                filter(newText);
//                return false;
//            }
//        });

    }
    public boolean onOptionsItemSelected(MenuItem item){
        this.finish();
        return true;
    }




}