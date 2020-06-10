package com.example.vendingmachineapplication;

public class ViewCartPojo {

    private String itemType;
    private String itemcost;
    private int itemQuantity;
    private double cost;

    public ViewCartPojo(String itemType, String itemcost, int itemQuantity, double cost) {
        this.itemType = itemType;
        this.itemcost = itemcost;
        this.itemQuantity = itemQuantity;
        this.cost = cost;
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemcost() {
        return itemcost;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public double getCost() {
        return cost;
    }
}
