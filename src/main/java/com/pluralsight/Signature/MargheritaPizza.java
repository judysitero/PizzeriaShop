package com.pluralsight.Signature;

import java.util.List;

public class MargheritaPizza extends SignaturePizza {
    public MargheritaPizza() {
        super(
                "MARGHERITA PIZZA",
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
