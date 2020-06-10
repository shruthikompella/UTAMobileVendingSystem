package com.example.vendingmachineapplication;

import java.util.Calendar;
import java.util.Date;

public class AvailableVehicle {



        String name;
        String  type;
        String date;


    public AvailableVehicle(String name, String type, String date) {
        this.name = name;
        this.type = type;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}
