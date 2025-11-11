package com.pluralsight.ComplexModels;

import com.pluralsight.BaseModels.Drink;
import com.pluralsight.BaseModels.GarlicKnots;
import com.pluralsight.Orderable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// This class is to manage the collection of items and perform calculations on that collection
public class Order {
    private List<Orderable> items;
    private final LocalDateTime orderTime;

    public Order() {
        this.items = new ArrayList<>();
        this.orderTime = LocalDateTime.now();
    }
    public void addItem(Orderable item) {
        this.items.add(item);
    }

    //The calculateTotal() method is the most powerful part of this class, showcasing advanced Java concepts.

    //Java Streams (.stream()): This is a modern, concise way to process collections, which you saw in the streams
    // workbook. It converts the static List into a sequential stream of data that can be efficiently processed. pg 66

    //Polymorphism in Action (Orderable::getPrice): This is called a Method Reference. It tells the stream to call
    // the getPrice() method on every single item (Pizza, Drink, or GarlicKnots) in the list
    public double calculateTotal() {
        double total = 0.0;
        total = this.items.stream()
                // Map each Orderable to its price
                .mapToDouble(Orderable::getPrice)
                .sum();
        //This is the terminal operation that aggregates all the prices retrieved into a single double value (total).


        // Alternative: using a traditional loop (like in my LedgerApp)
        // for (Orderable item : items) {
        //     total += item.getPrice();
        // }

        return total;
    }

    public boolean hasPizza() {
        return items.stream().anyMatch(item ->item instanceof Pizza);
        //Uses a stream operation (anyMatch) to check if any item in the list is an instance of the specific Pizza class.
    }

    public boolean hasItem() {
        return !items.isEmpty();
        //Checks if the list of items is not empty. !items.isEmpty() is a quick way to return true if any items exist.
    }

    public boolean hasSideOrDrink() {
        return items.stream().anyMatch(item -> item instanceof Drink || item instanceof GarlicKnots);
        //Uses a stream (anyMatch) to check if any item is an instance of either Drink OR GarlicKnots. This directly
        // supports the rule: "If a customer places an order with 0 pizzas, they must purchase garlic knots or a drink."
    }

    public List<Orderable> getItems() {
        return new ArrayList<>(items); //Return a defensive copy
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
        //Returns the creation time of the order, used by the ReceiptManager for the file name.
    }


}
