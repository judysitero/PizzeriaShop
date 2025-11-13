package com.pluralsight.ComplexModels;

import com.pluralsight.BaseModels.Topping;

import java.util.List;

public class SignaturePizza extends Pizza {
    public SignaturePizza(String name, String size, String crust, String sauce, boolean stuffedCrust, List<Topping> defaultToppings) {
        super(size, crust, sauce, stuffedCrust);
        this.setName(name);
        for (Topping t : defaultToppings) {
            super.addTopping(t);
        }

    }
    public static Topping createRegular(String name) {
        return new Topping(name, false, false);
    }

    public static Topping createMeat(String name) {
        return new Topping(name, true, true); // Premium, isMeat = true
    }

    public static Topping createCheese(String name) {
        return new Topping(name, true, false); // Premium, isMeat = false
    }

}
