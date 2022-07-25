package com.example.health_care;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.health_care.controllers.PharmacyController;
import com.example.health_care.Exceptions.PharmacyAdminRegisterException;
import com.example.health_care.Exceptions.PharmacyRegisterException;
import com.google.android.gms.maps.MapView;

public class PharmacyRegisterActivity extends AppCompatActivity {
    Button registerButton;
    EditText username;
    EditText password;
    EditText address;
    EditText name;
    MapView location;
    EditText phone;
    EditText hours;
    EditText firstname;
    EditText lastname;

    protected void showPharmacyRegisterError(PharmacyRegisterException e) {
        Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
        toast.show();
    }

    protected void showPharmacyAdminError(PharmacyAdminRegisterException e) {
        Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
        toast.show();
    }

    protected void register(String[] info, String[] name, String[] pharmacyInfo) {
        String username = info[0];
        String password = info[1];
        try {
            PharmacyController.register(
                    username,
                    password,
                    name,
                    pharmacyInfo
            );
        } catch (PharmacyRegisterException e) {
            showPharmacyRegisterError(e);
        } catch (PharmacyAdminRegisterException e) {
            showPharmacyAdminError(e);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_register);

        username = findViewById(R.id.editTextPharmacyUsernameRegisteration);
        password = findViewById(R.id.editTextPharmacyPasswordRegister);
        firstname = findViewById(R.id.editTextfirstnamePharmacy);
        lastname = findViewById(R.id.editTextlastnamePharmacy);
        registerButton = findViewById(R.id.buttonPharmacyRegister);
        address = findViewById(R.id.editTextPostalAddress);
        name = findViewById(R.id.editTextPharmacyName);
        location = findViewById(R.id.mapViewPharmacy);
        phone = findViewById(R.id.editTextPhoneNumber);
        hours = findViewById(R.id.editTextPharmacyHours);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String [] info = {username.toString(), password.toString()};
                String[] nameInfo = {firstname.toString(), lastname.toString()};
                String[] pharmacyInfo = {
                        name.toString(),
                        address.toString(),
                        phone.toString(),
                        hours.toString()
                };
                register(info, nameInfo, pharmacyInfo);
            }
        });


    }
}
