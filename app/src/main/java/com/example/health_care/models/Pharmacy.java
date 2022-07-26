package com.example.health_care.models;

import com.example.health_care.Exceptions.PharmacyGetDrugsExceptions;
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
    private ArrayList<Drug> drugs = new ArrayList<Drug>();
    private ArrayList<String> comments = new ArrayList<>();

    public Pharmacy(String name, String location, String phone, String openingHours) {
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.openingHours = openingHours;
        pharmacies.add(this);
    }

    public static ArrayList<Pharmacy> pharmaciesWithThisDrug(String drugId) {
        ArrayList<Pharmacy> pharmaciesWithDrug = new ArrayList<>();
        for (Pharmacy pharmacy : pharmacies) {
            for (Drug drug : pharmacy.drugs) {
                if (drug.getId().equals(drugId)) {
                    pharmaciesWithDrug.add(pharmacy);
                }
            }
        }
        return pharmaciesWithDrug;
    }

    public static Pharmacy findByInfo(String name, String address) {
        for (Pharmacy pharmacy : pharmacies
        ) {
            if (pharmacy.getName().equals(name) && pharmacy.getLocation().equals(address)) {
                return pharmacy;
            }
        }
        return null;
    }

    public static void removeDrugFromAllPharmacy(Drug checkDrug) {
        for (Pharmacy pharmacy : pharmacies
        ) {
            if (pharmacy.drugs.contains(checkDrug)) {
                pharmacy.drugs.remove(checkDrug);
            }
        }
    }

    public void addDrugToPharmacy(String id, String name, double price, String description) {
        drugs.add(new Drug(id, name, price, description));
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getProfPhoto() {
        return profPhoto;
    }

    public void setProfPhoto(String profPhoto) {
        this.profPhoto = profPhoto;
    }

    public String getBackPhoto() {
        return backPhoto;
    }

    public void setBackPhoto(String backPhoto) {
        this.backPhoto = backPhoto;
    }

    public static ArrayList<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public static void setPharmacies(ArrayList<Pharmacy> pharmacies) {
        Pharmacy.pharmacies = pharmacies;
    }

    public ArrayList<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(ArrayList<Drug> drugs) {
        this.drugs = drugs;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public void addDrug(String nameOrId) throws PharmacyGetDrugsExceptions {
        for (Drug drug : Drug.getDrugs()
        ) {
            if (drug.getId().equals(nameOrId) || drug.getName().equals(nameOrId)) {
                this.drugs.add(drug);
                return;
            }
        }
        throw new PharmacyGetDrugsExceptions("not found drug!!");
    }


    public void addDrugToPharmacy(Drug drug) throws PharmacyGetDrugsExceptions {
        drugs.add(drug);
        throw new PharmacyGetDrugsExceptions("not found drug!!");
    }

    public void removeDrug(String nameOrId) throws PharmacyGetDrugsExceptions {
        Drug check_drug = null;
        for (Drug drug : this.getDrugs()
        ) {
            if (drug.getId().equals(nameOrId) || drug.getName().equals(nameOrId)) {
                check_drug = drug;
            }
        }
        if (check_drug != null) {
            this.drugs.remove(check_drug);
        } else {
            throw new PharmacyGetDrugsExceptions("not found drug!!");
        }
    }
}
