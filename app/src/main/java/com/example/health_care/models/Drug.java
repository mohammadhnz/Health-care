package com.example.health_care.models;

public class Drug {
    private String id;
    private String name;
    private double price;
    private String description;
    private String photo;

    public Drug(String id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getId() {
        return id;
    }
}
