package com.example.health_care.models;

public class Customer extends User {
    private String profPhoto;
    public Customer(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }
}
