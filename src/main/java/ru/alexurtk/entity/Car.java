package ru.alexurtk.entity;

/**
 * Created by alex on 18.04.2018.
 */
public class Car {
    String id;
    Integer year;
    String brand;
    String color;
    Integer price;
    Boolean randomSoldState;

    public Car(String id,  String brand,  Integer year, String color, Integer price, Boolean randomSoldState) {
        this.id = id;
        this.year = year;
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.randomSoldState = randomSoldState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getRandomSoldState() {
        return randomSoldState;
    }

    public void setRandomSoldState(Boolean randomSoldState) {
        this.randomSoldState = randomSoldState;
    }
}
