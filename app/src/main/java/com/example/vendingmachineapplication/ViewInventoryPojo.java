package com.example.vendingmachineapplication;


public class ViewInventoryPojo {


    String name, type, drinks, snacks, sandwiches, endtime, current_loc, next_loc, revenue, op_name;

    public ViewInventoryPojo(String name, String type, String drinks, String snacks, String sandwiches, String endtime, String current_loc, String next_loc, String revenue, String op_name) {
        this.name = name;
        this.type = type;
        this.drinks = drinks;
        this.snacks = snacks;
        this.sandwiches = sandwiches;
        this.endtime = endtime;
        this.current_loc = current_loc;
        this.next_loc = next_loc;
        this.revenue = revenue;
        this.op_name = op_name;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDrinks() {
        return drinks;
    }

    public String getSnacks() {
        return snacks;
    }

    public String getSandwiches() {
        return sandwiches;
    }

    public String getEndtime() {
        return endtime;
    }

    public String getCurrent_loc() {
        return current_loc;
    }

    public String getNext_loc() {
        return next_loc;
    }

    public String getRevenue() {
        return revenue;
    }

    public String getOp_name() {
        return op_name;
    }
}