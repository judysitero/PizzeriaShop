package com.pluralsight.ComplexModels;

import java.util.List;

public class VeggiePizza extends SignaturePizza {
    public VeggiePizza() {
        super(
                "VEGGIE PIZZA",
                "PERSONAL",
                "REGULAR",
                "MARINARA",
                false,
                List.of(
                        createCheese("Mozzarella"),
                        createRegular("Bell peppers"),
                        createRegular("Spinach"),
                        createRegular("Olives"),
                        createRegular("Onions")

                )
        );
    }

}
