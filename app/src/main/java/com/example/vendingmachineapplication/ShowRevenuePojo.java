package com.example.vendingmachineapplication;

public class ShowRevenuePojo {

    private String vehicleName;
    private float revenue;

    public ShowRevenuePojo(String vehicleName, float revenue) {
        this.vehicleName = vehicleName;
        this.revenue = revenue;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public float getRevenue() {
        return revenue;
    }
}
