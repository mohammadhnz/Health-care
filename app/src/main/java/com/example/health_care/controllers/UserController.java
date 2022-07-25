package com.example.health_care.controllers;

import com.example.health_care.controllers.Exceptions.LoginExceptions;
import com.example.health_care.controllers.Exceptions.LoginOnceException;
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

    public String registerAdmin(String username, String password, String firstName, String email) {
        for (Admin teacher : allAdmins) {
            if (teacher.getUsername() == username.trim()) {
                return "Error: Admin exists with this username";
            }
        }
        if (this.user != null) {
            return "Error: You already logged in";
        }
        Admin admin = new Admin(username, password, firstName, email);
        this.allAdmins.add((Admin) admin);
        //this.saveAllAdmins();
        return "Successful!";
    }

    public String registerCustomer(String username, String password, String firstName, String email) {
        for (Customer student : allCustomers) {
            if (student.getUsername().equals(username.trim())) {
                return "Error: Student exists with this username";
            }
        }
        if (this.user != null) {
            return "Error: You already logged in";
        }
        Customer customer = new Customer(username, password, firstName, email);
        this.allCustomers.add((Customer) customer);
        //this.saveAllCustomers();
        return "Successful!";
    }

    public String registerPharmacy(String name, String location, String phone, String openingHours) {
        for (Pharmacy pharmacy : allPharmacies) {
            if (pharmacy.getName().equals(name.trim())) {
                return "Error: Student exists with this username";
            }
        }
        if (this.user != null) {
            return "Error: You already logged in";
        }
        Pharmacy pharmacy = new Pharmacy(name, location, phone, openingHours);
        this.allPharmacies.add((Pharmacy) pharmacy);
        //this.saveALlPharmacies();
        return "Successful!";
    }

    public String logout() {
        if (this.user == null) {
            return "Error: You are not logged in.";
        }
        this.user = null;
        return "Logged out";
    }

    public User getCurrentUser() {
        if (this.user == null) {
            return null;
        }
        return this.user;
    }

    public void login(String username, String password) throws LoginOnceException, LoginExceptions {
        if (this.user != null) {
            if (!this.user.getPassword().equals(password) || !this.user.getUsername().equals(username))
                throw new LoginOnceException(this.getCurrentUserType());
        }
        User currentUser = User.login(username, password);
        if (currentUser == null) {
            throw new LoginExceptions();
        }
        this.user = currentUser;
    }

    public String getCurrentUserType() {
        if (this.user == null) {
            return "Error! You are not logged in";
        }
        if (this.user instanceof Customer) {
            return "Customer";
        }
        else if (this.user instanceof Admin) {
            return "Admin";
        }
        return "Pharmacy";
    }

    public void setAllAdmins(ArrayList<Admin> allAdmins) {
        this.allAdmins = allAdmins;
    }

    public void setAllCustomers(ArrayList<Customer> allCustomers) {
        this.allCustomers = allCustomers;
    }

    public void setAllPharmacies(ArrayList<Pharmacy> allPharmacies) {
        this.allPharmacies = allPharmacies;
    }

    public void resetAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.addAll(allAdmins);
        users.addAll(allCustomers);
        User.setUsers(users);
        Admin.setAdmins(allAdmins);
        Customer.setCustomers(allCustomers);
        // TODO: `reset Pharmacies as well
    }

    public ArrayList<Admin> getAllAdmins() {
        return allAdmins;
    }

    public ArrayList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public ArrayList<Pharmacy> getAllPharmacies() {
        return allPharmacies;
    }
}
