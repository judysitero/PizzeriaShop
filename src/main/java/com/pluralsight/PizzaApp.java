package com.pluralsight;

import com.pluralsight.ComplexModels.Order;
import com.pluralsight.ComplexModels.Pizza;

import java.sql.SQLOutput;
import java.util.Scanner;

public class PizzaApp {
    private final InputHandler inputHandler;
    private final ReceiptManager receiptManager;
    private Order currentOrder;

    public PizzaApp() {
        this.inputHandler = new InputHandler(new Scanner(System.in), System.out);
        this.receiptManager = new ReceiptManager();
        this.currentOrder = null;
    }

    public static void main(String[] args) {
        PizzaApp app = new PizzaApp();
        app.start();
    }
    /**
     * Starts the main application loop (Home Screen).
     */
    public void start() {
        showHomeScreen();
        System.out.println("Thank you for your order! Application Exited");
    }
    // --- SCREEN FLOW METHODS
    /**
     * Displays the Home Screen and handles navigation (1: New Order, 0: Exit).
     */
    private void showHomeScreen() {
        boolean running = true;
        while (running) {
            System.out.println("\n======== \uD83C\uDFE0THE PIZZERIA: HOME SCREEN ============");
            System.out.println("1) New Order");
            System.out.println("0) Exit");

            int choice = inputHandler.getInt("Please enter a choice", 0, 1);
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
            System.out.println("\n====== \uD83D\uDECD\uFE0FORDER MENU (Total: $" + String.format("%,.2f", currentOrder.calculateTotal()) + ")====");
            System.out.println("\uD83C\uDF551) Add Pizza");
            System.out.println("\uD83E\uDD642) Add Drink");
            System.out.println("\uD83E\uDD563) Add Garlic Knots");
            System.out.println("✅4) Checkout");
            System.out.println("❌0) Cancel Order");
        }
        int choice = inputHandler.getInt("Please enter choices", 0, 4);

    }
}
