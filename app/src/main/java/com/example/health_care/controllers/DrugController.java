package com.example.health_care.controllers;

import com.example.health_care.Models.Drug;

import java.util.ArrayList;

public class DrugController {
    public static ArrayList<Drug> getListOfDrugs(){
        //TODO: change this for db
        return Drug.getAllDrugs();
    }

}
