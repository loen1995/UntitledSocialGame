package com.gimbernat.UntitledSocialGame.Entities;

public class EventEntity {

    public String name;
    public String description;
    public double latitude;
    public double longitude;

    public EventEntity(String name, String description, double latitude, double longitude) {
        this.name=name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
