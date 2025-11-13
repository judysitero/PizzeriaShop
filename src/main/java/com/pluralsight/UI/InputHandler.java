package com.pluralsight.UI;

import java.io.PrintStream;
import java.util.Scanner;

public class InputHandler {

    private final Scanner scanner;
    private final PrintStream out;

    public InputHandler(Scanner scanner, PrintStream out) {
        this.scanner = scanner;
        this.out = out;
    }

    /** Reads a string from the user. */
    public String getString(String prompt) {
        out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    /** Reads a choice within a specified range (min/max). */
    public int getInt(String prompt, int min, int max) {
        while (true) {
            out.print(prompt + " (Min: " + min + ", Max: " + max + "): ");
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value < min || value > max) {
                    out.println("ERROR: Please enter a number in the valid range.");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                out.println("ERROR: Invalid input. Please enter a whole number.");
            }
        }
    }

    /** Reads a confirmation choice (Y/N). */
    public boolean getYesNo(String prompt) {
        while (true) {
            String choice = getString(prompt + " (Y/N)").toUpperCase();
            if (choice.equals("Y")) {
                return true;
            } else if (choice.equals("N")) {
                return false;
            } else {
                out.println("ERROR: Invalid choice. Enter Y for Yes or N for No.");
            }
        }
    }
}
