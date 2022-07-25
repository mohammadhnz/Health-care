package com.example.health_care.models;

import java.util.ArrayList;

public class Customer extends User {
    private String profPhoto;
    private ArrayList<Drug> customerDrugSearches = new ArrayList<>();
    private ArrayList<Pharmacy> customerPharmacySearches = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();

    public Customer(String username, String password, String firstName, String email) {
        super(username, password, firstName, email);
    }

    private void addBookmarkPharmacy(Pharmacy pharmacy){
        customerPharmacySearches.add(pharmacy);
    }
    private void deleteBookMarkPharmacy(Pharmacy pharmacy){
        customerPharmacySearches.remove(pharmacy);
    }
    private void addBookmarkDrug(Drug drug){
        customerDrugSearches.add(drug);
    }
    private void deleteBookmarkDrug(Drug drug){
        customerDrugSearches.remove(drug);
    }
    public static void setCustomers(ArrayList<Customer> customers) {
        Customer.customers = customers;
    }
}
