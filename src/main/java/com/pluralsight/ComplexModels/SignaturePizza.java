package com.pluralsight.ComplexModels;

import com.pluralsight.BaseModels.Topping;

import java.util.List;

public class SignaturePizza extends Pizza {
    public SignaturePizza(String name, String size, String crust, String sauce, boolean stuffedCrust, List<Topping> defaultToppings) {
        super(size, crust, sauce, stuffedCrust);
        super.name = name;
        for (Topping t : defaultToppings) {
            super.addTopping(t);
        }
    }


}
