package com.example.health_care.Models;

import java.util.ArrayList;

//TODO: change for db
public class PharmacyAdmin extends User {

    public PharmacyAdmin(Pharmacy pharmacy, String username, String password) {
        super(username, password);
        this.pharmacy = pharmacy;
    }

    private static final ArrayList<PharmacyAdmin> pharmacyAdmins = new ArrayList<>();
    private Pharmacy pharmacy;

    public static PharmacyAdmin getByID(int id) {
        for (PharmacyAdmin admin : pharmacyAdmins
        ) {
            if (admin.getId() == id) {
                return admin;
            }
        }
        return null;
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

}

