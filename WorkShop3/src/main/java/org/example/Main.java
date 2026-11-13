
package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {

    // ===== LUXURY COLOR THEME =====
    public static final String GOLD = "\u001B[38;5;220m";
    public static final String GREEN = "\u001B[38;5;114m";
    public static final String CREAM = "\u001B[38;5;230m";
    public static final String WHITE = "\u001B[97m";
    public static final String GRAY = "\u001B[38;5;245m";
    public static final String BOLD = "\u001B[1m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Product> products = FileManager.readProduct();

        ShoppingCart shoppingCart = new ShoppingCart();

        String choice;

        do {

            printMainMenu();

            System.out.print(GOLD + "  Your choice: " + RESET);

            choice = scanner.nextLine().trim();

            switch (choice) {

                case "1":

                    printHeader("AVAILABLE PRODUCTS");

                    showAllProducts(products);

                    if (products.isEmpty()) {
                        pause(scanner);
                        break;
                    }

                    System.out.print(
                            "\n" + GOLD + "  Enter SKU to add to cart: " + RESET
                    );

                    String sku = scanner.nextLine().trim();

                    Product selected = selectProductBySku(sku, products);

                    if (selected != null) {

                        shoppingCart.addToCart(selected);

                        printSuccess(
                                selected.getName() + " added to your cart!"
                        );

                    } else {

                        printError("Product not found. Please check the SKU.");
                    }

                    pause(scanner);
                    break;

                case "2":

                    printHeader("YOUR SHOPPING CART");

                    shoppingCart.displayCart();

                    if (!shoppingCart.getCart().isEmpty()) {

                        System.out.print(
                                "\n" + GOLD
                                        + "  Enter SKU to remove or type NONE: "
                                        + RESET
                        );

                        String removeSku = scanner.nextLine().trim();

                        if (!removeSku.equalsIgnoreCase("NONE")) {

                            Product removeProduct = selectProductBySku(
                                    removeSku,
                                    shoppingCart.getCart()
                            );

                            if (removeProduct != null) {

                                shoppingCart.removeFromCart(removeProduct);

                                printSuccess("Product removed from cart!");

                            } else {

                                printError("Product not found in your cart.");
                            }
                        }
                    }

                    pause(scanner);
                    break;

                case "0":

                    printExitScreen();
                    break;

                default:

                    printError("Invalid option. Please try again.");
                    pause(scanner);
            }

        } while (!choice.equals("0"));

        scanner.close();
    }

    // ===== MAIN MENU =====

    public static void printMainMenu() {

        System.out.println();

        System.out.println(GOLD + BOLD
                + "  =================================================="
                + RESET);

        System.out.println(GOLD + BOLD
                + "                  THE GOLDEN STORE"
                + RESET);

        System.out.println(CREAM
                + "          YOUR PREMIUM SHOPPING EXPERIENCE"
                + RESET);

        System.out.println(GOLD
                + "  =================================================="
                + RESET);

        System.out.println(GREEN
                + "              Welcome! Happy Shopping."
                + RESET);

        System.out.println();

        System.out.println(GOLD + BOLD
                + "                  MAIN MENU"
                + RESET);

        System.out.println(GRAY
                + "  --------------------------------------------------"
                + RESET);

        System.out.println(WHITE + "       [1]  Display Products" + RESET);
        System.out.println(WHITE + "       [2]  View Shopping Cart" + RESET);
        System.out.println(GREEN + "       [0]  Exit Store" + RESET);

        System.out.println(GRAY
                + "  --------------------------------------------------"
                + RESET);
    }

    // ===== SCREEN HEADER =====

    public static void printHeader(String title) {

        System.out.println();

        System.out.println(GOLD + BOLD
                + "  =================================================="
                + RESET);

        System.out.println(GOLD + BOLD
                + "                  " + title
                + RESET);

        System.out.println(GOLD
                + "  =================================================="
                + RESET);

        System.out.println();
    }

    // ===== DISPLAY ALL PRODUCTS =====

    public static void showAllProducts(List<Product> products) {

        if (products.isEmpty()) {

            printError("No products are currently available.");
            return;
        }

        System.out.println(GRAY
                + "  --------------------------------------------------"
                + RESET);

        System.out.printf(
                GOLD + BOLD + "  %-12s %-22s %12s %-16s%n" + RESET,
                "SKU", "PRODUCT", "PRICE", "DEPARTMENT"
        );

        System.out.println(GRAY
                + "  --------------------------------------------------"
                + RESET);

        for (Product product : products) {

            System.out.printf(
                    CREAM + "  %-12s %-22s %12s %-16s%n" + RESET,
                    shorten(product.getSku(), 12),
                    shorten(product.getName(), 22),
                    String.format("$%.2f", product.getPrice()),
                    shorten(product.getDepartment(), 16)
            );
        }

        System.out.println(GRAY
                + "  --------------------------------------------------"
                + RESET);

        System.out.println(
                GREEN + "  Total products: " + products.size() + RESET
        );
    }

    // ===== FIND PRODUCT BY SKU =====

    public static Product selectProductBySku(
            String sku, List<Product> products) {

        for (Product product : products) {

            if (product.getSku().equalsIgnoreCase(sku)) {

                return product;
            }
        }

        return null;
    }

    // ===== SEARCH BY PRODUCT NAME =====

    public static void searchProductsByName(String name) {

        List<Product> products = FileManager.readProduct();

        boolean found = false;

        for (Product product : products) {

            if (product.getName().toLowerCase()
                    .contains(name.toLowerCase())) {

                System.out.println(product);
                found = true;
            }
        }

        if (!found) {
            printError("No matching products found.");
        }
    }

    // ===== SEARCH BY PRICE RANGE =====

    public static void searchProductsByPriceRange(
            double minPrice, double maxPrice) {

        List<Product> products = FileManager.readProduct();

        boolean found = false;

        for (Product product : products) {

            if (product.getPrice() >= minPrice
                    && product.getPrice() <= maxPrice) {

                System.out.println(product);
                found = true;
            }
        }

        if (!found) {
            printError("No products found in this price range.");
        }
    }

    // ===== SEARCH BY CATEGORY =====

    public static void searchProductsByCategory(String category) {

        List<Product> products = FileManager.readProduct();

        boolean found = false;

        for (Product product : products) {

            if (product.getDepartment()
                    .equalsIgnoreCase(category)) {

                System.out.println(product);
                found = true;
            }
        }

        if (!found) {
            printError("No products found in this department.");
        }
    }

    // ===== SUCCESS MESSAGE =====

    public static void printSuccess(String message) {

        System.out.println(
                "\n" + GREEN + "  ✔ " + message + RESET
        );
    }

    // ===== ERROR MESSAGE =====

    public static void printError(String message) {

        System.out.println(
                "\n" + GOLD + "  ! " + message + RESET
        );
    }

    // ===== EXIT SCREEN =====

    public static void printExitScreen() {

        System.out.println();

        System.out.println(GOLD + BOLD
                + "  =================================================="
                + RESET);

        System.out.println(GOLD + BOLD
                + "               THANK YOU FOR SHOPPING!"
                + RESET);

        System.out.println(CREAM
                + "          We appreciate your visit."
                + RESET);

        System.out.println(GREEN
                + "             Have a wonderful day!"
                + RESET);

        System.out.println(GOLD
                + "  =================================================="
                + RESET);
    }

    // ===== SHORTEN LONG TEXT =====

    public static String shorten(String text, int length) {

        if (text == null) {
            return "";
        }

        if (text.length() > length) {

            return text.substring(0, length - 3) + "...";
        }

        return text;
    }

    // ===== PAUSE SCREEN =====

    public static void pause(Scanner scanner) {

        System.out.println();

        System.out.print(
                GRAY + "  Press ENTER to continue..." + RESET
        );

        scanner.nextLine();
    }
}
