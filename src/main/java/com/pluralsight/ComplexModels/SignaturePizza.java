package com.pluralsight.ComplexModels;

import com.pluralsight.BaseModels.Topping;

import java.util.List;

public class SignaturePizza extends Pizza {
    public SignaturePizza(String name, String size, String crust, String sauce, boolean stuffedCrust, List<Topping> defaultToppings) {
        super(size, crust, sauce, stuffedCrust);
//        this.name = name;
//        super.name = name;
        this.setName(name);
        for (Topping t : defaultToppings) {
            super.addTopping(t);
        }
    }

    static class ToppingFactory {

        // Regular Toppings (Regular/Sauces cost 0 for inclusion)
        public static Topping createRegular(String name) {
            return new Topping(name, false, 0.00);
        }

        // Premium Meat Toppings
        public static Topping createMeat(String name) {
            // Set premium to true, extra cost to 1.50 for simplicity
            return new Topping(name, true, 1.50);
        }

        // Premium Cheese Toppings
        public static Topping createCheese(String name) {
            // Set premium to true, extra cost to 0.90 for simplicity
            return new Topping(name, true, 0.90);
        }
    }


}
