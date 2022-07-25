package com.example.health_care.models;

import com.example.health_care.models.Drug;

import java.util.ArrayList;

public class Pharmacy {
    private String name;
    // just having location attribute, maybe not a string!
    private String location;
    private String phone;
    private String openingHours;
    private String profPhoto;
    private String backPhoto;
    private static ArrayList<Pharmacy> pharmacies = new ArrayList<>();
    private ArrayList<Drug> drugs = new ArrayList<>();
    private ArrayList<String> comments = new ArrayList<>();

    public Pharmacy(String name, String location, String phone, String openingHours) {
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.openingHours = openingHours;
        pharmacies.add(this);
    }

    public static ArrayList<Pharmacy> pharmaciesWithThisDrug(String drugId){
        ArrayList<Pharmacy> pharmaciesWithDrug = new ArrayList<>();
        for (Pharmacy pharmacy : pharmacies) {
            for (Drug drug : pharmacy.drugs) {
                if (drug.getId().equals(drugId)){
                    pharmaciesWithDrug.add(pharmacy);
                }
            }
        }
        return pharmaciesWithDrug;
    }

    public String getName() {
        return name;
    }
}
