package com.pluralsight.BaseClasses;

public class Topping {
    private String name;
    private boolean isPremium;
    private boolean isMeat; // true for meat, false for cheese
    private boolean isExtra;

    public Topping(String name, boolean isPremium, boolean isMeat) {
        this.name = name;
        this.isPremium = isPremium;
        this.isMeat = isMeat;
        this.isExtra = false;
    }

    public String getName() {
        return name;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public boolean isMeat() {
        return isMeat;
    }

    public boolean isExtra() {
        return isExtra;
    }

    public void setExtra(boolean isExtra) {
        this.isExtra = isExtra;
    }

    @Override
    public String toString() {
        return name + (isPremium ? " (Premium)" : " (Regular)") + (isExtra ? " (Extra)" : "");
    }
}