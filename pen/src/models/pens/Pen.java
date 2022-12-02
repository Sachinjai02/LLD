package models.pens;

import models.Cap;
import writestrategies.WriteStrategy;

public abstract class Pen {
    private WriteStrategy writeStrategy;
    private Cap cap;
    private String name;

    private String brand;
    private double length;
    private double cost;

    public Pen(WriteStrategy writeStrategy) {
        this.writeStrategy = writeStrategy;
    }

    public Cap getCap() {
        return cap;
    }

    public void setCap(Cap cap) {
        this.cap = cap;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }



    public void write() {
        writeStrategy.writeBehaviour();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
