package com.pluralsight.ComplexModels;

import java.util.List;

public class SpecialOfTheDay extends SignaturePizza{
    public SpecialOfTheDay() {
        super(
                "SPECIAL OF THE DAY: The Arslan Special: Pineapple & Anchovy Delight",
                "MEDIUM",
                "REGULAR",
                "MARINARA",
                true,
                List.of(
                        createRegular("Pineapple"),
                        createRegular("Anchovies")
                )

        );
    }
}
