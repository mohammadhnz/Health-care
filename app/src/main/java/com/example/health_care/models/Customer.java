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

    public void addBookmarkPharmacy(Pharmacy pharmacy){
        customerPharmacySearches.add(pharmacy);
    }
    public void deleteBookMarkPharmacy(Pharmacy pharmacy){
        customerPharmacySearches.remove(pharmacy);
    }
    public void addBookmarkDrug(Drug drug){
        customerDrugSearches.add(drug);
    }
    public void deleteBookmarkDrug(Drug drug){
        customerDrugSearches.remove(drug);
    }
    public static void setCustomers(ArrayList<Customer> customers) {
        Customer.customers = customers;
    }

    public ArrayList<Pharmacy> getCustomerPharmacySearches() {
        return customerPharmacySearches;
    }

    public ArrayList<Drug> getCustomerDrugSearches() {
        return customerDrugSearches;
    }
}
