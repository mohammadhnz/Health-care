package com.example.health_care.models;

import android.database.Cursor;

import java.util.ArrayList;

public class Customer extends User {
    private String profPhoto;
    private ArrayList<Drug> customerDrugSearches = new ArrayList<>();
    private ArrayList<Pharmacy> customerPharmacySearches = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();

    public Customer(String username, String password, String firstName, String email) {
        super(username, password, firstName, email);
        DBHelper.getDbHelper().insertCustomerData(username, password, firstName, email);
    }

    public Customer(String username, String password, String firstName, String email, boolean sth) {
        super(username, password, firstName, email);
    }

    public static void initialize() {
        Cursor res = DBHelper.getDbHelper().getCustomerdata();
        if (res.getCount() > 0) {
            while (res.moveToNext()) {
                new Customer(res.getString(0), res.getString(1), res.getString(2), res.getString(3), false);
            }
        }
    }

    public void addBookmarkPharmacy(Pharmacy pharmacy) {
        customerPharmacySearches.add(pharmacy);
    }

    public void deleteBookMarkPharmacy(Pharmacy pharmacy) {
        customerPharmacySearches.remove(pharmacy);
    }

    public void addBookmarkDrug(Drug drug) {
        customerDrugSearches.add(drug);
    }

    public void deleteBookmarkDrug(Drug drug) {
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
