package com.pluralsight.Products;

import com.pluralsight.BaseClasses.OrderItem;

public class Drink extends OrderItem {
    //The unique data

    private String size;
    private String flavor;

    //This method creates a Drink object. It immediately calls super() to initialize the parent OrderItem's fields.

    public Drink( String size, String flavor) {
        super("Drink (" + flavor + ")", getPriceBySize(size));
        this.size = size;
        this.flavor = flavor;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFlavor() {
        return flavor;
    }


    @Override //This will fulfill the Orderable contract.
    public double getPrice() {
        return this.getBasePrice();

    }
    @Override
    public String getDescription() {
        // (string string float this.size(drink size ex "small"), this.getName(drink name ex "Drink(coke)") this.getBasePrice(inserts drinks price ex $2.50)
        return String.format("%s %s Drink - $%.2f", this.size, this.flavor, getPrice());
    }

    public static double getPriceBySize(String size) {

        return switch (size.toLowerCase()) {
            case "small" -> 2.00;
            case "medium" -> 2.50;
            case "large" -> 3.00;
            default -> 0.00;
        };
    }
}
