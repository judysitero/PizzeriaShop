package com.pluralsight.ComplexModels;

import com.pluralsight.BaseModels.OrderItem;
import com.pluralsight.BaseModels.Topping;

import java.util.ArrayList;
import java.util.List;

//public class Pizza extends OrderItem {
//
//    private String size;
//    private String crust;
//    private String sauce;
//    private boolean stuffedCrust;
//    private List<Topping> toppings;
//
//    public Pizza(String size, String crust, String sauce, boolean stuffedCrust) {
//        super(size + " Pizza (" + crust + " Crust)", getBasePrice(size, crust));
//        this.size = size;
//        this.crust = crust;
//        this.sauce = sauce;
//        this.stuffedCrust = stuffedCrust;
//        this.toppings = new ArrayList<>();
//
//        // Note: Base price in super() is ONLY for size/crust. Toppings and stuffed crust are added in getPrice().
//    }
//
//    public String getSize() {
//        return size;
//    }
//
//    public String getCrust() {
//        return crust;
//    }
//
//    public String getSauce() {
//        return sauce;
//    }
//
//    public boolean isStuffedCrust() {
//        return stuffedCrust;
//    }
//
//    // TOPPING MANAGEMENT
//
//    // Allows external code to add a Topping object to the pizza's internal list (this.toppings). This builds the pizza's complexity.
//    public void addTopping(Topping topping) {
//        this.toppings.add(topping);
//    }
//
//    // Returns a new ArrayList containing the toppings. This is a defensive copy to prevent external code from
//    // directly corrupting the pizza's internal state (the original toppings list).
//    public List<Topping> getToppings() {
//        return new ArrayList<>(this.toppings);
//    }
//
//    @Override
//    public double getPrice() {
//        double total = this.getBasePrice();
//
//        // Calculate Topping Costs
//        for (Topping t : toppings) {
//            if (t.isPremium()) {
//                // Premium toppings cost the base premium price
//                double basePremiumCost = getPremiumToppingCost(this.size);
//
//                if (t.isExtra()) {
//                    // If it's extra, add the extra cost for this size
//                    double extraCost = getExtraToppingCost(this.size);
//                    total += basePremiumCost + extraCost;
//                } else {
//                    // Regular portion of premium topping
//                    total += basePremiumCost;
//                }
//            }
//            // Regular toppings cost $0 as they are 'Included' - no charge
//        }
//
//        // Add Stuffed Crust
//        if (this.stuffedCrust) {
//            total += stuffedCrustCost;
//        }
//        return total;
//    }
//
////    @Override
////    public String getDescription() {
////        StringBuilder description = new StringBuilder();
////        description.append(String.format("PIZZA: %s ($%.2f)\n", this.getName(), getPrice()));
////        description.append(String.format(" - Size/Crust: %s, %s (Base: $%.2f)\n", this.size, this.crust, this.getBasePrice()));
////        description.append(String.format(" - Sauce: %s\n", this.sauce));
////        description.append(String.format(" - Stuffed Crust: %s (Add $%.2f)\n", this.stuffedCrust ? "YES" : "NO", this.stuffedCrust ? stuffedCrustCost : 0.00));
////        description.append(" - Toppings:\n");
////
////        for (Topping t : toppings) {
////            description.append(String.format("  > %s\n", t.toString()));
////        }
////        return description.toString();
//
//    @Override
//    public String getDescription() {
//        StringBuilder description = new StringBuilder();
//        description.append(String.format("%s %s Pizza", this.size, this.crust));
//        description.append(String.format(" - $%.2f\n", getPrice()));
//        description.append(String.format("   Base: $%.2f", this.getBasePrice()));
//
//        // Add stuffed crust line if applicable
//        if (this.stuffedCrust) {
//            description.append(String.format(" | Stuffed Crust: +$%.2f", stuffedCrustCost));
//        }
//        description.append("\n");
//
//        // Add sauce
//        description.append(String.format("   Sauce: %s\n", this.sauce));
//
//        // Add toppings with pricing info
//        if (!toppings.isEmpty()) {
//            description.append("   Toppings:\n");
//            for (Topping t : toppings) {
//                if (t.isPremium()) {
//                    double baseCost = getPremiumToppingCost(this.size);
//                    if (t.isExtra()) {
//                        double extraCost = getExtraToppingCost(this.size);
//                        description.append(String.format("     %s (Premium): +$%.2f + $%.2f extra\n",
//                                t.getName(), baseCost, extraCost));
//                    } else {
//                        description.append(String.format("     %s (Premium): +$%.2f\n", t.getName(), baseCost));
//                    }
//                } else {
//                    description.append(String.format("     %s (Regular): Included\n", t.getName()));
//                }
//            }
//        } else {
//            description.append("   Toppings: None\n");
//        }
//
//        return description.toString();
//    }
//
//    // STATIC PRICING
//    private static final double stuffedCrustCost = 2.00;
//
//    private static double getBasePrice(String size, String crust) {
//        return switch (size.toLowerCase()) {
//            case "personal" -> 8.50;
//            case "medium" -> 12.00;
//            case "large" -> 16.50;
//            default -> 0.00;
//        };
//        // NOTE: Cauliflower crust is in the table but doesn't show a price difference; we'll treat it as base price for now.
//    }
//
//    private static double getPremiumToppingCost(String size, boolean isMeat) {
//        if (isMeat) {
//            return switch (size.toLowerCase()) {
//                case "personal" -> 1.00;
//                case "medium" -> 2.00;
//                case "large" -> 3.00;
//                default -> 0.00;
//            };
//        } else {
//            // Cheese pricing
//            return switch (size.toLowerCase()) {
//                case "personal" -> 0.75;
//                case "medium" -> 1.50;
//                case "large" -> 2.25;
//                default -> 0.00;
//            };
//        }
//    }
//
//    // Add this method to handle extra topping costs by size
//    private static double getExtraToppingCost(String size, boolean isMeat) {
//        if (isMeat) {
//            return switch (size.toLowerCase()) {
//                case "personal" -> 0.50;
//                case "medium" -> 1.00;
//                case "large" -> 1.50;
//                default -> 0.00;
//            };
//        } else {
//            // Extra cheese pricing
//            return switch (size.toLowerCase()) {
//                case "personal" -> 0.30;
//                case "medium" -> 0.60;
//                case "large" -> 0.90;
//                default -> 0.00;
//            };
//        }
//    }
//}
//
//
//
//
//
////new code //////////////////////////////////////////////////////////////////////////////////////
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