package com.pluralsight.BaseModels;

public class GarlicKnots extends OrderItem {
    private int quantity;

    private static final double pricePerOrder = 1.50;


    public GarlicKnots(int quantity) {
        super("Garlic Knots", pricePerOrder * quantity);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double getPrice() {
        return this.getBasePrice();
    }

    @Override
    public String getDescription() {
        return String.format("%d x %s @ $%.2f", this.quantity, this.getName(), this.getBasePrice());
    }
}
