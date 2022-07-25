package com.example.health_care.controllers;

import com.example.health_care.models.Admin;
import com.example.health_care.models.Customer;
import com.example.health_care.models.Pharmacy;
import com.example.health_care.models.User;

import java.util.ArrayList;

public class UserController {
    private static UserController userController;
    private User user;
    private ArrayList<Admin> allAdmins = new ArrayList<>();
    private ArrayList<Customer> allCustomers = new ArrayList<>();
    private ArrayList<Pharmacy> allPharmacies = new ArrayList<>();

    private UserController() {
        this.user = null;
    }
    public static UserController getInstance() {
        if (userController == null) {
            synchronized (UserController.class) {
                if (userController == null) {
                    userController = new UserController();
                }
            }
        }
        return userController;
    }

    public String registerAdmin(String username, String password, String firstName, String lastName, String email) {
        for (Admin teacher : allAdmins) {
            if (teacher.getUsername() == username.trim()) {
                return "Error: Admin exists with this username";
            }
        }
        if (this.user != null) {
            return "Error: You already logged in";
        }
        Admin admin = new Admin(username, password, firstName, lastName, email);
        this.allAdmins.add((Admin) admin);
        //this.saveAllAdmins();
        return "Successful!";
    }

    public String registerCustomer(String username, String password, String firstName, String lastName, String email) {
        for (Customer student : allCustomers) {
            if (student.getUsername().equals(username.trim())) {
                return "Error: Student exists with this username";
            }
        }
        if (this.user != null) {
            return "Error: You already logged in";
        }
        Customer customer = new Customer(username, password, firstName, lastName, email);
        this.allCustomers.add((Customer) customer);
        //this.saveAllCustomers();
        return "Successful!";
    }

//    public String registerPharmacy(String name, String location, String phone, String openingHours) {
//        for (Pharmacy pharmacy : allPharmacies) {
//            if (pharmacy.get().equals(username.trim())) {
//                return "Error: Student exists with this username";
//            }
//        }
//        if (this.user != null) {
//            return "Error: You already logged in";
//        }
//        Student student = new Student(username, password, firstName, lastName, studentId);
//        this.allStudents.add((Student) student);
//        this.saveAllStudents();
//        return "Successful!";
//    }
}
