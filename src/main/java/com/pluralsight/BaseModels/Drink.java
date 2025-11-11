package com.pluralsight.BaseModels;

public class Drink extends OrderItem {
    //The unique data

    private String size;
    private String flavor;

    //This method creates a Drink object. It immediately calls super() to initialize the parent OrderItem's fields.

    public Drink( String size, String flavor) {
        //This is where the price logic connects: The Drink class figures out its own base price. It sets the parent's
        // name property to include the flavor (e.g., "Drink (Coke)") and sets the parent's basePrice by calling the
        // static helper method getPriceBySize(size).
        super("Drink (" + flavor + ")", getPriceBySize(size));
        this.size = size;
        this.flavor = flavor;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFlavor() {
        return flavor;
    }


    @Override //This fulfills the Orderable contract.
    public double getPrice() {
        return this.getBasePrice();
    }
    //This ensures you haven't misspelled the method name or messed up the parameters.
    @Override
    public String getDescription() {
        // (string string float this.size(drink size ex "small"), this.getName(drink name ex "Drink(coke)") this.getBasePrice(inserts drinks price ex $2.50)
        return String.format("%s %s ($%.2f)", this.size, this.getName(), this.getBasePrice()); //creates a formatted string for the receipt using the item's properties.
    }

    public static double getPriceBySize(String size) {

        //a Switch Expression. It allows the switch block to return a value directly, making the code cleaner than a traditional switch statement with breaks.
        return switch (size.toLowerCase()) {
            case "small" -> 2.00; //Case Matching: It takes the input size,
            case "medium" -> 2.50;
            case "large" -> 3.00; //The arrow indicates the value to be returned if that case matches (e.g., if the size is "small," return the price $2.00$
            default -> 0.00; //default -> 0.00: This ensures the method handles any invalid input gracefully by returning $0.00$, preventing errors.
        };
    }
}
