package com.example.vendingmachineapplication;

public class SearchVehiclePojo {




    String name,id,type,intersection,s_time,e_time,date;

    public SearchVehiclePojo(String name, String type, String id,String intersection,String date, String s_time, String e_time) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.intersection = intersection;
        this.s_time = s_time;
        this.e_time = e_time;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getIntersection() {
        return intersection;
    }

    public String getS_time() {
        return s_time;
    }

    public String getE_time() {
        return e_time;
    }

    public String getDate() {
        return date;
    }
}

