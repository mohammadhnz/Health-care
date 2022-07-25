package com.example.health_care.models;

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
        drugs.add(this);
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

    public static Drug findDrugById(String id){
        for (Drug drug : drugs) {
            if (drug.getId().equals(id)){
                return drug;
            }
        }
        return null;
    }
}
