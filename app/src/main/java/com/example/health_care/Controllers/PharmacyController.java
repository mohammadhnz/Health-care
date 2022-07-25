package com.example.health_care.Controllers;

import com.example.health_care.Exceptions.PharmacyLoginException;
import com.example.health_care.models.PharmacyAdmin;

public class PharmacyController {
    public static void login(String username, String password) throws PharmacyLoginException {
        PharmacyAdmin user = PharmacyAdmin.getByInfo(username, password);
        if (user != null) {
            user.setLoggedIn(true);
        }
        throw new PharmacyLoginException("user not found!!");
    }
}
