package com.example.health_care.Models;

import java.util.ArrayList;
import java.util.List;

//TODO: change this for db and structure
public class Drug {
    private static final ArrayList<Drug> drugs = new ArrayList<Drug>();
    private String name;
    private Double price;
    private String code;
    private String producer;
    private String type;

    public static ArrayList<Drug> getAllDrugs(){
        return drugs;
    }

}
