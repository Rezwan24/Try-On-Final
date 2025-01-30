package com.example.freshstart.models;

import java.time.LocalDateTime;

public class History {
    private String tShirtColor;
    private String imageUrl;
    private LocalDateTime triedOnDate;

    public History(String tShirtColor, String imageUrl) {
        this.tShirtColor = tShirtColor;
        this.imageUrl = imageUrl;
        this.triedOnDate = LocalDateTime.now();
    }

    public String getTShirtColor() {
        return tShirtColor;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public LocalDateTime getTriedOnDate() {
        return triedOnDate;
    }
}


