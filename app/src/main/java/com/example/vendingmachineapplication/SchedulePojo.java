package com.example.vendingmachineapplication;

public class SchedulePojo {
     private String name, loc_int, s_time, e_time, type, date;

    public SchedulePojo(String name, String loc_int, String s_time, String e_time, String type, String date) {
        this.name = name;
        this.loc_int = loc_int;
        this.s_time = s_time;
        this.e_time = e_time;
        this.type = type;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoc_int() {
        return loc_int;
    }

    public void setLoc_int(String loc_int) {
        this.loc_int = loc_int;
    }

    public String getS_time() {
        return s_time;
    }

    public void setS_time(String s_time) {
        this.s_time = s_time;
    }

    public String getE_time() {
        return e_time;
    }

    public void setE_time(String e_time) {
        this.e_time = e_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
