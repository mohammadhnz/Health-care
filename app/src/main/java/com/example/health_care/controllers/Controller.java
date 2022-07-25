package com.example.health_care.controllers;

public class Controller {
    private static Controller controller;


    public static Controller getInstance() {
        if (controller == null) {
            synchronized (Controller.class) {
                if (controller == null) {
                    controller = new Controller();
                }
            }
        }
        return controller;
    }

}