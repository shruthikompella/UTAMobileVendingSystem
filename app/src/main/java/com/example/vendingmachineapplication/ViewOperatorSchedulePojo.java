package com.example.vendingmachineapplication;

public class ViewOperatorSchedulePojo {

    private String startTime;
    private String endTime;
    private String locationId;
    private String locationIntersection;

    public ViewOperatorSchedulePojo(String startTime, String endTime, String locationId, String locationIntersection) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.locationId = locationId;
        this.locationIntersection = locationIntersection;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getLocationIntersection() {
        return locationIntersection;
    }
}
