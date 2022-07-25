package com.example.health_care.controllers;

import com.example.health_care.Exceptions.PharmacyAdminRegisterException;
import com.example.health_care.Exceptions.PharmacyLoginException;
import com.example.health_care.Exceptions.PharmacyRegisterException;
import com.example.health_care.PharmacyPanelActivity;
import com.example.health_care.models.Pharmacy;
import com.example.health_care.models.PharmacyAdmin;

import java.lang.reflect.Array;

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
}
