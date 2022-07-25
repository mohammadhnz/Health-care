package com.example.health_care.models;

import java.util.ArrayList;

public class Admin extends User {
    private static ArrayList<Admin> admins = new ArrayList<>();
    public Admin(String username, String password, String firstName, String lastName, String gmail) {
        super(username, password, firstName, lastName, gmail);
    }

    public static void setAdmins(ArrayList<Admin> admins) {
        Admin.admins = admins;
    }
}
