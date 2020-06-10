package com.example.vendingmachineapplication;

public class ViewPastOrdersPojo {

    String confirmation, lname, fname, date, time;

    public ViewPastOrdersPojo(String date, String time, String lname, String fname, String confirmation){
        this.date = date;
        this.time = time;
        this.lname = lname;
        this.fname = fname;
        this.confirmation = confirmation;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public String getDate() {
        return date;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getTime() {
        return time;
    }
}
