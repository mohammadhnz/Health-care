package com.example.health_care.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health_care.R;
import com.example.health_care.controllers.PharmacyController;
import com.example.health_care.controllers.UserController;
import com.example.health_care.models.Customer;
import com.example.health_care.models.Pharmacy;
import com.example.health_care.models.PharmacyAdmin;

public class PharmacySettingPage extends AppCompatActivity {
    TextView p_currentInfo;
    EditText p_phone_edit;
    EditText p_email;
    TextView p_name_setting;
    Button edit_btn;
    Button cancel_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_setting_page);

        p_currentInfo = findViewById(R.id.p_currentInfo);
        p_phone_edit = findViewById(R.id.p_phone_edit);
        p_email = findViewById(R.id.p_email);
        p_name_setting = findViewById(R.id.p_name_setting);
        edit_btn = findViewById(R.id.edit_btn);
        cancel_btn = findViewById(R.id.cancel_btn);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password").toString();

        PharmacyAdmin pharmacyAdmin = PharmacyAdmin.getByInfo(username, password);
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();

        String info = "name: " + pharmacy.getName() + "\n" + "phone: " + pharmacy.getPhone() + "\n" + "email: " + pharmacyAdmin.getEmail() + "\n" + "lovation: " + pharmacy.getLocation();
        p_currentInfo.setText(info);

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertSubmit = new AlertDialog.Builder(PharmacySettingPage.this);
                alertSubmit.setTitle("Editing Personal Info");
                alertSubmit.setMessage("Are you sure you want to apply changes?");
                alertSubmit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean t = false;
                        if (!p_phone_edit.getText().toString().isEmpty()) {
                            pharmacy.setPhone(p_phone_edit.getText().toString());
                            t = true;
                        }
                        if (!p_email.getText().toString().isEmpty()) {
                            pharmacyAdmin.setEmail(p_email.getText().toString());
                            t = true;
                        }
                        if (t) {
                            Toast.makeText(PharmacySettingPage.this, "changes applied", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                alertSubmit.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = Toast.makeText(PharmacySettingPage.this, "No Changes Were Made", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                alertSubmit.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    public void onDismiss(DialogInterface dialog) {
                        String info = "name: " + pharmacy.getName() + "\n" + "phone: " + pharmacy.getPhone() + "\n" + "email: " + pharmacyAdmin.getEmail() + "\n" + "lovation: " + pharmacy.getLocation();
                        p_currentInfo.setText(info);
                    }
                });
                alertSubmit.show();

            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                setResult(100, intent1);
                finish();
            }
        });

    }
}