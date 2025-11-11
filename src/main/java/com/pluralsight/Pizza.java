package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Pizza extends OrderItem{

    private String size;
    private String crust;
    private String sauce;
    private boolean stuffedCrust;
    private List<Topping> toppings;

    // STATIC PRICING
    private  static final double stuffedCrustCost = 2.00;

    private static double getBasePrice(String size, String crust) {
        return switch (size.toLowerCase()) {
            case "personal" -> 8.50;
            case "medium" -> 12.00;
            case "large" -> 16.50;
            default -> 0.00;
        };
        // NOTE: Cauliflower crust is in the table but doesn't show a price difference; we'll treat it as base price for now.
    }
    private static double getPremiumToppingCost(String size) {
        return switch (size.toLowerCase()) {
            case"personal" -> 1.00;
            case "medium" -> 2.00;
            case "large" -> 3.00;
            default -> 0.00;
        };
    }

    public Pizza( String size, String crust, String sauce, boolean stuffedCrust) {
        super(size + "Pizza (" + crust + "Crust)", getBasePrice(size, crust));
        this.size = size;
        this.crust = crust;
        this.sauce = sauce;
        this.stuffedCrust = stuffedCrust;
        this.toppings = new ArrayList<>();

        // Note: Base price in super() is ONLY for size/crust. Toppings and stuffed crust are added in getPrice().
    }
    //TOPPING MANAGEMENT
    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public  List<Topping> getToppings() {
        return new ArrayList<>(this.toppings);
    }
    @Override
    public double getPrice() {
        double total = this.getBasePrice();

        // 1. Calculate Topping Costs (Regular are 'Included', Premiums are extra)
        for (Topping t : toppings) {
            if(t.isPremium()) {
                // If Premium (Meat/Cheese): it costs the base premium cost + extra cost if marked extra
                double basePremiumCost = getPremiumToppingCost((this.size));
                double extraCharge = t.isExtra() ? t.getPremiumExtraCost() : 0.00;

                total += basePremiumCost;
                total += extraCharge;
            }
            // Regular toppings cost $0 as they are 'Included'.
        }
        // 2. Add Stuffed Crust
        if (this.stuffedCrust) {
            total += stuffedCrustCost;

        }
        return total;


    }
    @Override
    public String getDescription() {
        StringBuilder description = new StringBuilder();
        description.append(String.format("PIZZA: %s ($%.2f)\n", this.getName(), getPrice()));
        description.append(String.format(" -Size/Crust: %s, %s (Base: $%.2f)\n", this.size, this.crust, this.getBasePrice()));
        description.append(String.format(" -Sauce: %s\n", this.sauce));
        description.append(String.format(" -Stuffed Crust: %s (Add $%.2f)\n", this.stuffedCrust ? "YES" : "NO", this.stuffedCrust ? stuffedCrustCost : 0.00));
        description.append(" -Toppings:\n");

        for(Topping t: toppings) {
            description.append(String.format("  >%s\n", t.toString()));
        }
        return description.toString();

    }

    public String getSize() {
        return size;
    }

    public String getCrust() {
        return crust;
    }

    public String getSauce() {
        return sauce;
    }

    public boolean isStuffedCrust() {
        return stuffedCrust;
    }
}
