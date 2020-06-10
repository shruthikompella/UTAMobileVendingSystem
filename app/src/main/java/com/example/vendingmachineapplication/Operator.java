package com.example.vendingmachineapplication;

public class Operator {
    private String uname;
    private String firstname;
    private String lastName;

    public Operator(String uname, String firstname, String lastName) {
        this.uname = uname;
        this.firstname = firstname;
        this.lastName = lastName;
    }

    public String getUname() {
        return uname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }
}
