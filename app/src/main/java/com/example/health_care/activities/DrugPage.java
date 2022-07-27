package com.example.health_care.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.health_care.R;
import com.example.health_care.models.Drug;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Objects;

public class DrugPage extends AppCompatActivity {

    EditText drugNameT;
    EditText drugPriceT;
    ImageView drugImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_page);

        drugNameT = findViewById(R.id.drug_page_name_id);
        drugPriceT = findViewById(R.id.drug_page_price);
        drugImage = findViewById(R.id.drug_img_recId);

        Intent intent = getIntent();
        String drugId = intent.getStringExtra("drugId");
        String drugName = Objects.requireNonNull(Drug.findDrugById(drugId)).getName();

        drugNameT.setText(drugName);
    }
}