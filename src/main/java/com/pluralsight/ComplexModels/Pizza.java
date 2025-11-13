package com.pluralsight.ComplexModels;

import com.pluralsight.BaseModels.OrderItem;
import com.pluralsight.BaseModels.Topping;

import java.util.ArrayList;
import java.util.List;

public class Pizza extends OrderItem {

    private String size;
    private String crust;
    private String sauce;
    private boolean stuffedCrust;
    private List<Topping> toppings;

    public Pizza(String size, String crust, String sauce, boolean stuffedCrust) {
        super(size + " Pizza (" + crust + " Crust)", getBasePrice(size, crust));
        this.size = size;
        this.crust = crust;
        this.sauce = sauce;
        this.stuffedCrust = stuffedCrust;
        this.toppings = new ArrayList<>();
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

    // TOPPING MANAGEMENT
    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public List<Topping> getToppings() {
        return new ArrayList<>(this.toppings);
    }

    @Override
    public double getPrice() {
        double total = this.getBasePrice();

        // Calculate Topping Costs
        for (Topping t : toppings) {
            if (t.isPremium()) {
                double basePremiumCost = getPremiumToppingCost(this.size, t.isMeat());

                if (t.isExtra()) {
                    double extraCost = getExtraToppingCost(this.size, t.isMeat());
                    total += basePremiumCost + extraCost;
                } else {
                    total += basePremiumCost;
                }
            }
            // Regular toppings cost $0 as they are 'Included' - no charge
        }

        // Add Stuffed Crust
        if (this.stuffedCrust) {
            total += stuffedCrustCost;
        }
        return total;
    }

    @Override
    public String getDescription() {
        StringBuilder description = new StringBuilder();
        description.append(String.format("%s %s Pizza", this.size, this.crust));
        description.append(String.format(" - $%.2f\n", getPrice()));
        description.append(String.format("   Base: $%.2f", this.getBasePrice()));

        // Add stuffed crust line if applicable
        if (this.stuffedCrust) {
            description.append(String.format(" | Stuffed Crust: +$%.2f", stuffedCrustCost));
        }
        description.append("\n");

        // Add sauce
        description.append(String.format("   Sauce: %s\n", this.sauce));

        // Add toppings with pricing info
        if (!toppings.isEmpty()) {
            description.append("   Toppings:\n");
            for (Topping t : toppings) {
                if (t.isPremium()) {
                    double baseCost = getPremiumToppingCost(this.size, t.isMeat());
                    if (t.isExtra()) {
                        double extraCost = getExtraToppingCost(this.size, t.isMeat());
                        description.append(String.format("     %s (Premium): +$%.2f + $%.2f extra\n",
                                t.getName(), baseCost, extraCost));
                    } else {
                        description.append(String.format("     %s (Premium): +$%.2f\n",
                                t.getName(), baseCost));
                    }
                } else {
                    description.append(String.format("     %s (Regular): Included\n", t.getName()));
                }
            }
        } else {
            description.append("   Toppings: None\n");
        }

        return description.toString();
    }

    // STATIC PRICING
    private static final double stuffedCrustCost = 2.00;

    private static double getBasePrice(String size, String crust) {
        return switch (size.toLowerCase()) {
            case "personal" -> 8.50;
            case "medium" -> 12.00;
            case "large" -> 16.50;
            default -> 0.00;
        };
    }

    private static double getPremiumToppingCost(String size, boolean isMeat) {
        if (isMeat) {
            return switch (size.toLowerCase()) {
                case "personal" -> 1.00;
                case "medium" -> 2.00;
                case "large" -> 3.00;
                default -> 0.00;
            };
        } else {
            // Cheese pricing
            return switch (size.toLowerCase()) {
                case "personal" -> 0.75;
                case "medium" -> 1.50;
                case "large" -> 2.25;
                default -> 0.00;
            };
        }
    }

    private static double getExtraToppingCost(String size, boolean isMeat) {
        if (isMeat) {
            return switch (size.toLowerCase()) {
                case "personal" -> 0.50;
                case "medium" -> 1.00;
                case "large" -> 1.50;
                default -> 0.00;
            };
        } else {
            // Extra cheese pricing
            return switch (size.toLowerCase()) {
                case "personal" -> 0.30;
                case "medium" -> 0.60;
                case "large" -> 0.90;
                default -> 0.00;
            };
        }
    }
}