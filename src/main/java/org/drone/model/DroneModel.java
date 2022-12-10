package org.drone.model;

public enum DroneModel {
    LIGHT_WEIGHT("Lightweight"),
    MIDDLE_WEIGHT("Middleweight"),
    CRUISER_WEIGHT("Cruiserweight"),
    HEAVY_WEIGHT("Heavyweight");

    private String value;
    DroneModel(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
