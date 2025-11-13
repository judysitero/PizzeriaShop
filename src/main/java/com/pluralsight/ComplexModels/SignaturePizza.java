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
    // Regular Toppings (Regular/Sauces cost 0 for inclusion)
    public static Topping createRegular(String name) {
        return new Topping(name, false, 0.00);
    }
    // Premium Meat Toppings - extra cost should be size-based, not fixed
    public static Topping createMeat(String name) {
        // The extra cost will be calculated dynamically in getPrice()
        // based on pizza size, so we can set this to 0 or remove the parameter
        return new Topping(name, true, 0.00);
    }

    // Premium Cheese Toppings
    public static Topping createCheese(String name) {
        // Same as above - extra cost calculated dynamically
        return new Topping(name, true, 0.00);
    }


}
