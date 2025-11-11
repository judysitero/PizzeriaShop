package com.pluralsight;

public class Topping {
    private String name;
    private boolean isPremium;
    private boolean isExtra; // Will track if the customer chose 'extra' of this topping.
    private double premiumExtraCost; // Cost added per extra premium topping

    public Topping(String name, boolean isPremium, double premiumExtraCost) {
        this.name = name;
        this.isPremium = isPremium;
        this.premiumExtraCost = premiumExtraCost;
        this.isExtra = false; // Default to not extra
    }

    public String getName() {
        return name;
    }


    public boolean isPremium() {
        return isPremium;
    }

    public boolean isExtra() {
        return isExtra;
    }

    public void setExtra(boolean isExtra) {
        this.isExtra = isExtra;
    }

    public double getPremiumExtraCost() {
        return premiumExtraCost;
    }

    @Override
    public String toString() {
        return name + (isPremium ? " (Premium)" : " (Regular)") + (isExtra ? " (Extra)" : "");
    }
}

//testing testing
