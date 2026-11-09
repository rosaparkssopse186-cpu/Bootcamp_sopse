package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FileManager {

    private static final Path INVENTORY_FILE =
            Paths.get("src/main/resources/Inventory");

    public static List<Product> readProduct() {

        List<Product> productList = new ArrayList<>();

        try (BufferedReader bufferedReader =
                     Files.newBufferedReader(INVENTORY_FILE)) {

            String itemRow;

            while ((itemRow = bufferedReader.readLine()) != null) {

                if (itemRow.trim().isEmpty()
                        || itemRow.toLowerCase().startsWith("sku|")) {
                    continue;
                }

                String[] rowSplitter = itemRow.split("\\|", -1);

                if (rowSplitter.length != 4) {
                    System.out.println("Invalid inventory row: " + itemRow);
                    continue;
                }

                String sku = rowSplitter[0].trim();
                String productName = rowSplitter[1].trim();
                double price = Double.parseDouble(rowSplitter[2].trim());
                String department = rowSplitter[3].trim();

                Product product = new Product(
                        sku, productName, price, department
                );

                productList.add(product);
            }

        } catch (IOException ex) {

            System.out.println("Could not find or read Inventory.csv.");
            System.out.println("File location: "
                    + INVENTORY_FILE.toAbsolutePath());

        } catch (NumberFormatException ex) {

            System.out.println("Invalid price in Inventory.csv: "
                    + ex.getMessage());
        }

        return productList;
    }

    public static void writeInventory(Product product) {

        try {

            Files.createDirectories(INVENTORY_FILE.getParent());

            boolean needsHeader = !Files.exists(INVENTORY_FILE)
                    || Files.size(INVENTORY_FILE) == 0;

            try (BufferedWriter bufferedWriter =
                         Files.newBufferedWriter(
                                 INVENTORY_FILE,
                                 StandardOpenOption.CREATE,
                                 StandardOpenOption.APPEND)) {

                if (needsHeader) {
                    bufferedWriter.write(
                            "SKU|Name|Price|Department"
                    );
                    bufferedWriter.newLine();
                }

                String line = String.format(
                        Locale.US,
                        "%s|%s|%.2f|%s",
                        product.getSku(),
                        product.getName(),
                        product.getPrice(),
                        product.getDepartment()
                );

                bufferedWriter.write(line);
                bufferedWriter.newLine();

                System.out.println("Item added to Inventory.");
            }

        } catch (IOException ex) {

            System.out.println("Could not write to Inventory.csv.");
            ex.printStackTrace();
        }
    }
}