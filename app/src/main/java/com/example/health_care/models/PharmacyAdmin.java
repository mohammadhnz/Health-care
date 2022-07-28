package com.example.health_care.models;

import com.example.health_care.Exceptions.PharmacyGetDrugsExceptions;

import java.util.ArrayList;

public class PharmacyAdmin extends User {
    private static ArrayList<PharmacyAdmin> pharmacyAdmins = new ArrayList<>();
    private Pharmacy pharmacy;

    public PharmacyAdmin(String username, String password, String firstName, String lastName, Pharmacy pharmacy) {
        super(username, password, firstName, lastName);
        this.pharmacy = pharmacy;
        this.setLastName(lastName);
        pharmacyAdmins.add(this);
    }

    public Pharmacy getPharmacy() {
        return this.pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public static PharmacyAdmin getByInfo(String username, String password) {
        for (PharmacyAdmin user : pharmacyAdmins
        ) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }


    public void addDrugToPharmacy(Drug drug) throws PharmacyGetDrugsExceptions {
        pharmacy.addDrugToPharmacy(drug);
    }
}
