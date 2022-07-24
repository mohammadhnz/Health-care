package com.example.health_care.Models;

import java.util.ArrayList;

public class Pharmacy {
    private static ArrayList<Pharmacy> pharmacies = new ArrayList<>();
    private String name;
    private String address;
    //TODO: Add map location field

    public static boolean isValidInfo(String name, String address){
        for (Pharmacy pharmacy: pharmacies
             ) {
            if (pharmacy.getAddress().equals(address) && pharmacy.getName().equals(name)){
                return false;
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Pharmacy(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
