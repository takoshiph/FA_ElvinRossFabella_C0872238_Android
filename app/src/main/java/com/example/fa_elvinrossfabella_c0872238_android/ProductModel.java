package com.example.fa_elvinrossfabella_c0872238_android;

public class ProductModel {
    int id;
    String name;
    String description;
    double price;
    double latitude;
    double longitude;

    public ProductModel(String id, String name, String description, String price, String latitude, String longitude) {
        this.id = Integer.parseInt(id);
        this.name = name;
        this.description = description;
        this.price = Double.parseDouble(price);
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
