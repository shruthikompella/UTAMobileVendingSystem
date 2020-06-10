package com.example.vendingmachineapplication;

public class ViewMyPastOrdersPojo {
    String confirmation, date, time;

    public ViewMyPastOrdersPojo(String date, String time, String confirmation){
        this.date = date;
        this.time = time;

        this.confirmation = confirmation;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public String getDate() {
        return date;
    }


    public String getTime() {
        return time;
    }
}

