package com.coiffure.jackcoiffure;

public class Service {

    private String name, description;
    private int price;

    public Service(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Service() {
        this.name = null;
        this.description = null;
        this.price = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
