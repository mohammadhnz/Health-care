package com.example.health_care.models;

import android.database.Cursor;

import java.util.ArrayList;

public class Admin extends User {
    public static String ADMIN_KEY = "hehe";
    private static ArrayList<Admin> admins = new ArrayList<>();

    public Admin(String username, String password, String firstName, String gmail) {
        super(username, password, firstName, gmail);
        DBHelper.getDbHelper().insertAdminData(username, password, firstName, gmail);
    }

    public Admin(String username, String password, String firstName, String gmail, boolean sth) {
        super(username, password, firstName, gmail);
    }

    public static void initialize() {
        Cursor res = DBHelper.getDbHelper().getAdmindata();
        if (res.getCount() > 0) {
            while (res.moveToNext()) {
                new Admin(res.getString(0), res.getString(1), res.getString(2), res.getString(3), false);
            }
        }
    }

    public static Admin findByInfo(String name, String password) {
        for (Admin admin : admins
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
