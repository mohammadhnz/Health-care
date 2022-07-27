package com.example.health_care.models;

import java.util.ArrayList;

public class Admin extends User {
    public static String ADMIN_KEY = "hehe";
    private static ArrayList<Admin> admins = new ArrayList<>();
    public Admin(String username, String password, String firstName, String gmail) {
        super(username, password, firstName, gmail);
    }
    public static Admin findByInfo(String name, String password) {
        for (Admin admin: admins
        ) {
            if (admin.getUsername().equals(name) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    public static void setAdmins(ArrayList<Admin> admins) {
        Admin.admins = admins;
    }
}
