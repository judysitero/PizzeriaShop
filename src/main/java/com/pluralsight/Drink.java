package com.pluralsight;

public class Drink extends OrderItem {

    private String size;
    private String flavor;

    public static double getPriceBySize(String size) {
        return switch (size.toLowerCase()) {
            case "small" -> 2.00;
            case "medium" -> 2.50;
            case "large" -> 3.00;
            default -> 0.00;
        };
    }

    public Drink( String size, String flavor) {
        super("Drink (" + flavor + ")", getPriceBySize(size));
        this.size = size;
        this.flavor = flavor;
    }

    public String getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    @Override
    public double getPrice() {
        return this.getBasePrice();
    }

    @Override
    public String getDescription() {
        return String.format("%s %s ($%.2f)", this.size, this.getName(), this.getBasePrice());
    }
}
