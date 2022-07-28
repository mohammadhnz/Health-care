package com.example.health_care.models;

import android.database.Cursor;

import java.util.ArrayList;

public class Drug {
    private String id;
    private String name;
    private double price;
    private String description;
    private String photo;
    private static ArrayList<Drug> drugs = new ArrayList<>();

    public Drug(String id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        DBHelper.getDbHelper().insertDrugData(id, name, price, description);
        drugs.add(this);
    }

    public Drug(String id, String name, double price, String description, boolean dont_write_in_db) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        drugs.add(this);
    }

    public static void initialize() {
        Cursor res = DBHelper.getDbHelper().getDrugdata();
        if (res.getCount() > 0) {
            while (res.moveToNext()) {
                new Drug(res.getString(0), res.getString(1), Float.parseFloat(res.getString(2)), res.getString(3), false);
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public static Drug findDrugById(String id) {
        for (Drug drug : drugs) {
            if (drug.getId().equals(id)) {
                return drug;
            }
        }
        return null;
    }


    public static Drug findByName(String name) {
        for (Drug drug : drugs) {
            if (drug.getName().equals(name)) {
                return drug;
            }
        }
        return null;
    }

    public static Drug removeDrug(String id) {
        Drug checkDrug = null;
        for (Drug drug : drugs) {
            if (drug.getId().equals(id)) {
                checkDrug = drug;
                break;
            }
        }
        if (checkDrug != null) {
            Drug.drugs.remove(checkDrug);
            Pharmacy.removeDrugFromAllPharmacy(checkDrug);
            return checkDrug;
        }
        return null;
    }


    public static ArrayList<Drug> getDrugs() {
        return drugs;
    }
}
