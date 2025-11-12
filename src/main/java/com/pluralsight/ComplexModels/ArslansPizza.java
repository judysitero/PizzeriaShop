package com.pluralsight.ComplexModels;

import java.util.List;

public class ArslansPizza extends SignaturePizza{
    public ArslansPizza() {
        super(
                "Special Of the Day: The Arslan Special (Pineapple & Anchovy)",
                "MEDIUM",
                "THICK",
                "MARINARA",
                true,
                List.of(
                        createRegular("Pineapple"),
                        createRegular("Anchovies")
                )

        );
    }
}
