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
                        createCheese("Mozzarella"),
                        createRegular("Tomatoes"),
                        createRegular("Basil"),
                        createRegular("Olive Oil")
                )
        );
    }

}
