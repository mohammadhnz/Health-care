package com.example.health_care.Models;

import java.util.ArrayList;

public abstract class User {
    private String username;
    private int id;
    private String password;
    private Boolean loggedIn;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.loggedIn = false;
    }

    public int getId() {
        return id;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
