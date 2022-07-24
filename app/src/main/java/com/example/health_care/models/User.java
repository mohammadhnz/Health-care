package com.example.health_care.models;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isLoggedIn;

    private static ArrayList<User> users = new ArrayList<>();
    public User(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isLoggedIn = false;
        this.email = email;
        users.add(this);
    }
}
