package com.pluralsight;

import com.pluralsight.ComplexModels.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReceiptManager {
    private static final String receiptFolder = "receipts/";
    private static final DateTimeFormatter fileFormatter =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    public void saveReceipt(Order order) {
        String fileName = receiptFolder + order.getOrderTime().format(fileFormatter) + ".txt";
        try {
            File directory = new File(receiptFolder);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            try (FileWriter fileWriter = new FileWriter(fileName);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

                // --- Start Receipt Formatting ---
                bufferedWriter.write("==================================================");
                bufferedWriter.newLine();
                bufferedWriter.write("           THE PIZZERIA SHOP: Order Receipt");
                bufferedWriter.newLine();
                bufferedWriter.write("==================================================");
                bufferedWriter.newLine();
                bufferedWriter.write("Order Time: " + order.getOrderTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                bufferedWriter.newLine();
                bufferedWriter.write("--------------------------------------------------");
                bufferedWriter.newLine();

                // Write details for each item using the Orderable.getDetails() (Polymorphism)
                List<Orderable> items = order.getItems();
                for (Orderable item : items) {
                    // Each item's specific details are provided by its own class
                    bufferedWriter.write(item.getDescription());
                    bufferedWriter.newLine();
                }

                bufferedWriter.write("--------------------------------------------------");
                bufferedWriter.newLine();
                bufferedWriter.write(String.format("ORDER TOTAL: $%,.2f", order.calculateTotal()));
                bufferedWriter.newLine();
                bufferedWriter.write("==================================================");
            }

            System.out.println("\nSUCCESS: Receipt saved to " + fileName);

        } catch (IOException e) {
            System.err.println("\nERROR: Failed to save receipt: " + e.getMessage());
            throw new RuntimeException("I/O Error during receipt saving.", e);
        }
    }


}