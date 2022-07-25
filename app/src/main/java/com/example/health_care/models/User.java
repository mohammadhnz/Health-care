package com.example.health_care.models;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isLoggedIn;
    private static ArrayList<User> users = new ArrayList<>();

    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.firstName = name;
        this.isLoggedIn = false;
        this.email = email;
        users.add(this);
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
    public static ArrayList<User> getUsers() {
        return users;
    }
    public static void setUsers(ArrayList<User> users) {
        User.users = users;
    }

    public static boolean is_username_valid(String newUsername) {
        for (int i = 0; i < users.size(); i += 1) {
            if (users.get(i).username.equals(newUsername)) {
                return false;
            }
        }
        return true;
    }
    public static User login(String username, String password) {
        for (int i = 0; i < users.size(); i += 1) {
            if (users.get(i).username.equals(username) && users.get(i).password.equals(password)) {
                return users.get(i);
            }
        }
        return null;
    }
    public static User getUserById(String username){
        for (User user : users) {
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
}
