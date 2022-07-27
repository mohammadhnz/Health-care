package com.example.health_care.controllers;

import com.example.health_care.Exceptions.PharmacyGetDrugsExceptions;
import com.example.health_care.Exceptions.PharmacyLogoutException;
import com.example.health_care.Exceptions.PharmacyAdminRegisterException;
import com.example.health_care.Exceptions.PharmacyLoginException;
import com.example.health_care.Exceptions.PharmacyRegisterException;
import com.example.health_care.models.Drug;
import com.example.health_care.models.Pharmacy;
import com.example.health_care.models.PharmacyAdmin;

import java.util.ArrayList;
import java.util.HashMap;

public class PharmacyController {
    public static void login(String username, String password) throws PharmacyLoginException {
        PharmacyAdmin user = PharmacyAdmin.getByInfo(username, password);
        if (user != null) {
            user.setLoggedIn(true);
        }
        throw new PharmacyLoginException("user not found!!");
    }

    public static void register(String username, String password, String[] name, String[] pharmacyInfo) throws PharmacyRegisterException, PharmacyAdminRegisterException {
        String firstname = name[0];
        String lastname = name[1];
        String pharmacyName = pharmacyInfo[0];
        String address = pharmacyInfo[1];
        String phone = pharmacyInfo[2];
        String hours = pharmacyInfo[3];
        PharmacyAdmin user = PharmacyAdmin.getByInfo(username, password);
        if (user != null) {
            throw new PharmacyAdminRegisterException("user is repeated!!");
        }
        Pharmacy pharmacy = registerPharmacy(
                pharmacyName,
                address,
                phone,
                hours
        );
        PharmacyAdmin newUser = new PharmacyAdmin(
                username,
                password,
                firstname,
                lastname,
                pharmacy
        );
        newUser.setLoggedIn(true);

    }

    //TODO: add location
    public static Pharmacy registerPharmacy(String name, String address, String phone, String hours) throws PharmacyRegisterException {
        Pharmacy pharmacy = Pharmacy.findByInfo(name, address);
        if (pharmacy != null) {
            throw new PharmacyRegisterException("repeated pharmacy!!");
        }
        return new Pharmacy(name, address, phone, hours);
    }

    public static void logout(String username, String password) throws PharmacyLogoutException {
        PharmacyAdmin user = PharmacyAdmin.getByInfo(username, password);
        if (user != null) {
            user.setLoggedIn(false);
            return;
        }
        throw new PharmacyLogoutException("not found user!!");
    }

    public static ArrayList<Drug> getPharmacyDrugs(String username, String password) throws PharmacyGetDrugsExceptions {
        PharmacyAdmin user = PharmacyAdmin.getByInfo(username, password);
        if (user != null) {
            //
            new Drug("12", "degz", 12.2, "desc");
            user.getPharmacy().addDrug("12");
            //
            return user.getPharmacy().getDrugs();
        }
        throw new PharmacyGetDrugsExceptions("not found User!!");
    }

    public static HashMap<String, String> getUserInfo(String username, String password) throws PharmacyGetDrugsExceptions {
        PharmacyAdmin user = PharmacyAdmin.getByInfo(username, password);
        HashMap<String, String> result = new HashMap<>();
        if (user != null) {
            result.put("username", user.getUsername());
            result.put("password", user.getPassword());
            result.put("pharmacyName", user.getPharmacy().getName());
            result.put("phone", user.getPharmacy().getPhone());
            result.put("address", user.getPharmacy().getLocation());
            result.put("fullName", user.getFullName());
            return result;

        }
        throw new PharmacyGetDrugsExceptions("not found User!!");


    }

    public static void create(String username, String password, String firstname, String lastname, String address, String phone, String pharmacyName, String email, String hours) throws PharmacyRegisterException {
        Pharmacy pharmacy = Pharmacy.findByInfo(pharmacyName, address);
        if (pharmacy != null){
            throw new PharmacyRegisterException("pharmacy repeated !!");
        }
        PharmacyAdmin user = PharmacyAdmin.getByInfo(username, password);
        if (user != null){
            throw new PharmacyRegisterException("repeated User!!");
        }
        Pharmacy newPharmacy = new Pharmacy(pharmacyName, address, phone, hours);
        PharmacyAdmin newUser = new PharmacyAdmin(username, password, firstname, lastname, newPharmacy);
    }
}
