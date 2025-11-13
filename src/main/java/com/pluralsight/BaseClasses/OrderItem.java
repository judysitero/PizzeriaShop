package com.pluralsight.BaseClasses;

public abstract class OrderItem implements Orderable {
    private String name;
    private double basePrice;

    public OrderItem(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
    @Override
    public abstract double getPrice();

    @Override
    public String getDescription()  {
        return String.format("%s(Base: $%.2f)", this.name, this.basePrice);
    }
}
