package com.pluralsight.ComplexModels;

import java.util.List;

public class MargheritaPizza extends SignaturePizza {
    public MargheritaPizza() {
        super(
                "Margherita Pizza",
                "MEDIUM",
                "REGULAR",
                "MARINARA",
                false,
                List.of(
                        ToppingFactory.createCheese("Mozzarella"),
                        ToppingFactory.createRegular("Tomatoes"),
                        ToppingFactory.createRegular("Basil"),
                        ToppingFactory.createRegular("Olive Oil")
                )
        );
    }

}
