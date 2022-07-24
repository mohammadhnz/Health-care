package com.example.health_care.controllers;

import com.example.health_care.Models.Pharmacy;
import com.example.health_care.Models.PharmacyAdmin;
import com.example.health_care.exceptions.LoggingException;
import com.example.health_care.exceptions.RegisteringException;

public class UserController {
    public static void logOutPharmacyAdmin(int id) throws LoggingException {
        PharmacyAdmin user = PharmacyAdmin.getByID(id);
        if (user != null) {
            user.setLoggedIn(false);
        } else {
            throw new LoggingException("logout failed with %s id", id);
        }
    }

    public static int pharmacyLogin(String username, String password) throws LoggingException {
        PharmacyAdmin user = PharmacyAdmin.getByInfo(username, password);
        if (user == null) {
            throw new LoggingException(String.format("no user with %s username and %s password", username, password), 0);
        }
        user.setLoggedIn(true);
        return user.getId();
    }

    public static Pharmacy createGetPharmacy(String address, String name) throws RegisteringException {
        if (Pharmacy.isValidInfo(name, address)) {
            return new Pharmacy(name, address);
        }
        throw new RegisteringException("pharmacy has admin!!");
    }

    public static int addNewPharmacyAdmin(String username, String password, Pharmacy pharmacy) throws RegisteringException {
        PharmacyAdmin user = PharmacyAdmin.getByInfo(username, password);
        if (user == null) {
            return (new PharmacyAdmin(pharmacy, username, password)).getId();
        }
        throw new RegisteringException("user exists!!");
    }
    public static int createPharmacyUser(String address, String name, String username, String password) throws RegisteringException {
        Pharmacy pharmacy = createGetPharmacy(address, name);
        return addNewPharmacyAdmin(username, password, pharmacy);
    }

}
