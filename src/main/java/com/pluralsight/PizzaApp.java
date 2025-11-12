package com.pluralsight;

import com.pluralsight.BaseModels.Drink;
import com.pluralsight.BaseModels.GarlicKnots;
import com.pluralsight.BaseModels.Topping;
import com.pluralsight.ComplexModels.*;

import java.sql.SQLOutput;
import java.util.Scanner;

public class PizzaApp {
    private final InputHandler inputHandler;
    private final ReceiptManager receiptManager;
    private Order currentOrder;

    public PizzaApp() {
        // Initialize the InputHandler with System streams
        this.inputHandler = new InputHandler(new Scanner(System.in), System.out);
        this.receiptManager = new ReceiptManager();
        this.currentOrder = null; // No order initially
    }

    // --- MAIN ENTRY POINT ---

    public static void main(String[] args) {
        // Use try-with-resources if we were managing I/O here, but since the Scanner
        // is managed internally by InputHandler, we just call the application loop.
        PizzaApp app = new PizzaApp();
        app.start();
    }

    /**
     * Starts the main application loop (Home Screen).
     */
    public void start() {
        showHomeScreen();
        System.out.println("Thank you for your order! Application exited.");
        // Close resources if needed (InputHandler manages Scanner internally)
    }

    // --- SCREEN FLOW METHODS (Similar to your LedgerApp menus) ---

    /**
     * Displays the Home Screen and handles navigation (1: New Order, 0: Exit).
     */
    private void showHomeScreen() {
        boolean running = true;
        while (running) {
            System.out.println("\n===== \uD83C\uDFE0THE PIZZERIA: HOME SCREEN =====");
            System.out.println("1) New Order");
            System.out.println("0) Exit");

            int choice = inputHandler.getInt("Please enter your choice", 0, 1);

            switch (choice) {
                case 1:
                    startNewOrder();
                    showOrderScreen();
                    break;
                case 0:
                    running = false;
                    break;
            }
        }
    }

    private void startNewOrder() {
        this.currentOrder = new Order();
        System.out.println("\n--- Starting New Order ---");
    }

    /**
     * Displays the Order Screen and handles adding items and checkout.
     */
    private void showOrderScreen() {
        boolean ordering = true;
        while (ordering) {
            System.out.println("\n===== \uD83D\uDECD\uFE0FORDER SCREEN (Total: $" + String.format("%,.2f", currentOrder.calculateTotal()) + ") =====");
            System.out.println("\uD83C\uDF551) Add Pizza");
            System.out.println("\uD83E\uDD642) Add Drink");
            System.out.println("3) Add Garlic Knots");
            System.out.println("✅4) Checkout");
            System.out.println("❌0) Cancel Order");

            int choice = inputHandler.getInt("Please enter your choice", 0, 4);

            switch (choice) {
                case 1:
                    showAddPizzaScreen();
                    break;
                case 2:
                    showAddDrinkScreen();
                    break;
                case 3:
                    showAddGarlicKnotsScreen();
                    break;
                case 4:
                    // Checkout will attempt to finalize the order
                    if (isOrderValidForCheckout()) {
                        showCheckoutScreen();
                        ordering = false; // Exit order loop after checkout confirmation
                    }
                    break;
                case 0:
                    cancelOrder();
                    ordering = false; // Exit order loop back to Home
                    break;
            }
        }
    }

    private boolean isOrderValidForCheckout() {
        if (!currentOrder.hasItem()) {
            System.out.println("\nERROR: Order is empty! Please add items.");
            return false;
        }

        // Rule: If a customer places an order with 0 pizzas, they must purchase garlic knots or a drink.
        if (!currentOrder.hasPizza() && !currentOrder.hasSideOrDrink()) {
            System.out.println("\nERROR: Orders without a pizza must include a drink or garlic knots.");
            return false;
        }

        return true;
    }

    private void showCheckoutScreen() {
        System.out.println("\n--- CHECKOUT ---");
        System.out.println("Review Your Order:");
        // Print all items
        for (Orderable item : currentOrder.getItems()) {
            System.out.println(item.getDescription());
        }
        System.out.println("-----------------");
        System.out.println(String.format("ORDER TOTAL: $%,.2f", currentOrder.calculateTotal()));
        System.out.println("-----------------");

        boolean confirm = inputHandler.getYesNo("Confirm order?");

        if (confirm) {
            receiptManager.saveReceipt(currentOrder);
            this.currentOrder = null; // Clear order for next customer
        } else {
            System.out.println("Checkout cancelled. Returning to Order Menu.");
            // Order is kept in currentOrder variable for further modification
        }
    }

    private void cancelOrder() {
        System.out.println("\nOrder cancelled. All items deleted.");
        this.currentOrder = null;
    }

    //========================================================================================================================================
    private void showAddDrinkScreen() {
        System.out.println("\n===== \uD83E\uDD64ADD DRINK =====");

        // 1. Get Flavor
        String flavor = inputHandler.getString("Enter drink flavor (e.g., Coke, Sprite, Water)");

        // 2. Get Size and Price
        System.out.println("\n--- Select Size ---");
        System.out.println("1) Small ($2.00)");
        System.out.println("2) Medium ($2.50)");
        System.out.println("3) Large ($3.00)");

        int sizeChoice = inputHandler.getInt("Select a size", 1, 3);
        String size;

        switch (sizeChoice) {
            case 1:
                size = "Small";
                break;
            case 2:
                size = "Medium";
                break;
            case 3:
                size = "Large";
                break;
            default:
                // Should not happen due to inputHandler logic
                size = "Medium";
        }

        // 3. Create and Add to Order
        Drink drink = new Drink(flavor, size);
        currentOrder.addItem(drink);

        System.out.println("\nSUCCESS: Added " + drink.getDescription());
    }

    /**
     * Prompts for quantity of garlic knots and adds them to the order.
     */
    private void showAddGarlicKnotsScreen() {
        System.out.println("\n===== ADD GARLIC KNOTS =====");

        // 1. Get Quantity
        int quantity = inputHandler.getInt("Enter quantity of garlic knots to add", 1, 10);

        // 2. Create and Add to Order
        // Note: The price calculation is encapsulated inside the GarlicKnots constructor.
        GarlicKnots knots = new GarlicKnots(quantity);
        currentOrder.addItem(knots);

        System.out.printf("SUCCESS: Added %d orders of Garlic Knots to order. Current total: $%,.2f\n",
                quantity, currentOrder.calculateTotal());

    }


    /**
     * Guides the user through customizing a pizza and adds it to the order.
     * This method is complex and will require more development.
     */
    private void showAddPizzaScreen() {
        System.out.println("\n===== \uD83C\uDF55ADD PIZZA MENU =====");
        System.out.println("1) Build Custom Pizza");
        System.out.println("2) Margherita Pizza (Medium)");
        System.out.println("3) Veggie Pizza (Personal)");
        System.out.println("4) Arslan's Special (Medium, Stuffed)");
        System.out.println("0) Back to Order Menu");

        int typeChoice = inputHandler.getInt("Select pizza type", 0, 4);

        if (typeChoice == 0) {
            return;
        }

        Pizza pizza;

        // Use polymorphism: instantiate the specific class based on the choice
        switch (typeChoice) {
            case 1:
                pizza = buildCustomPizza(); // Custom logic moved here
                break;
            case 2:
                pizza = new MargheritaPizza();
                break;
            case 3:
                pizza = new VeggiePizza();
                break;
            case 4:
                pizza = new ArslansPizza();
                break;
            default:
                return;
        }

        // --- Customization for Signature Pizzas (2, 3, or 4) ---
        if (typeChoice != 1) {
            boolean customize = inputHandler.getYesNo("Do you want to customize the toppings on this " + pizza.getName() + "?");
            if (customize) {
                // Allows adding new toppings to the pre-configured Signature Pizza
                addCustomToppings(pizza);
            }
        }

        // Final Add to Order and Confirmation
        currentOrder.addItem(pizza);

        System.out.printf("\nSUCCESS: Added %s! Current total: $%,.2f\n",
                pizza.getName(), currentOrder.calculateTotal());
    }

    private Pizza buildCustomPizza() {
        System.out.println("\n--- BUILD CUSTOM PIZZA ---");

        // --- SIZE ---
        System.out.println("\n--- Select Size ---");
        System.out.println("1) Personal 8\" (Base: $8.50)");
        System.out.println("2) Medium 12\" (Base: $12.00)");
        System.out.println("3) Large 16\" (Base: $16.50)");

        int sizeChoice = inputHandler.getInt("Select pizza size", 1, 3);
        String size = switch (sizeChoice) {
            case 1 -> "Personal";
            case 2 -> "Medium";
            case 3 -> "Large";
            default -> "Medium";
        };

        // --- CRUST ---
        System.out.println("\n--- Select Crust ---");
        System.out.println("1) Thin");
        System.out.println("2) Regular");
        System.out.println("3) Thick");
        System.out.println("4) Cauliflower");

        int crustChoice = inputHandler.getInt("Select crust type", 1, 4);
        String crust = switch (crustChoice) {
            case 1 -> "Thin";
            case 2 -> "Regular";
            case 3 -> "Thick";
            case 4 -> "Cauliflower";
            default -> "Regular";
        };

        // --- STUFFED CRUST ---
        boolean stuffedCrust = inputHandler.getYesNo("Would you like stuffed crust? (+$2.00)");

        // --- SAUCE ---
        System.out.println("\n--- Select Sauce ---");
        System.out.println("1) Marinara");
        System.out.println("2) Alfredo");
        System.out.println("3) Pesto");
        System.out.println("4) BBQ");

        int sauceChoice = inputHandler.getInt("Select a sauce", 1, 4);
        String sauce = switch (sauceChoice) {
            case 1 -> "Marinara";
            case 2 -> "Alfredo";
            case 3 -> "Pesto";
            case 4 -> "BBQ";
            default -> "Marinara";
        };

        // Create base pizza
        Pizza pizza = new Pizza(size, crust, sauce, stuffedCrust);

        // Add toppings loop
        addCustomToppings(pizza);

        return pizza;
    }

    /**
     * Allows the user to add multiple custom toppings to an existing pizza.
     */
    private void addCustomToppings(Pizza pizza) {
        boolean done = false;
        while (!done) {
            String currentPrice = String.format("$%,.2f", pizza.getPrice());
            System.out.println("\n--- ADD TOPPINGS (Current Price: " + currentPrice + ") ---");

            // Simplified Topping Menu: Expand this later to include all meats/cheeses/regular items
            System.out.println("1) Pepperoni (Premium - Extra: +$1.50)");
            System.out.println("2) Onions (Regular - Included)");
            System.out.println("0) Finish Toppings");

            int choice = inputHandler.getInt("Select topping to add", 0, 2);

            if (choice == 0) {
                done = true;
                break;
            }

            // Create the Topping object based on selection
            Topping selected;
            if (choice == 1) {
                // Premium Meat: $1.50 extra cost for extra portion
                selected = new Topping("Pepperoni", true, 1.50);
            } else {
                // Regular Topping: $0.00 extra cost
                selected = new Topping("Onions", false, 0.00);
            }

            // Ask for 'Extra' status
            boolean isExtra = inputHandler.getYesNo("Add " + selected.getName() + " as EXTRA? (Affects Premium Price)");
            selected.setExtra(isExtra);

            pizza.addTopping(selected);
            System.out.println("Added " + selected.getName() + ".");
        }
    }
}