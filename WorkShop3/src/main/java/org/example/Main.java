package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Product> products = FileManager.readProduct();
        List<Product> cart = new ArrayList<>();

        int choice;

        do {

            System.out.println("\nONLINE STORE");
            System.out.println("1. Display Products");
            System.out.println("2. Display Cart");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    System.out.println("\nAVAILABLE PRODUCTS");

                    for (Product product : products) {
                        System.out.printf("|%s|%s|%.2f|%s%n",
                                product.getSku(),
                                product.getName(),
                                product.getPrice(),
                                product.getDepartment());
                    }

                    System.out.print("\nEnter SKU to add to cart: ");
                    String sku = scanner.next();

                    Product selected = selectProductBySku(sku);

                    if (selected != null) {

                        cart.add(selected);

                        System.out.println(
                                selected.getSku() +
                                        " added to cart.");

                    } else {

                        System.out.println("Product not found.");
                    }

                    break;

                case 2:

                    System.out.println("\nSHOPPING CART");

                    if (cart.isEmpty()) {

                        System.out.println("Cart is empty.");

                    } else {

                        for (Product product : cart) {
                            System.out.println(product);
                        }

                        System.out.print(
                                "\nEnter SKU to remove or type NONE: ");

                        String removeSku = scanner.next();

                        if (!removeSku.equalsIgnoreCase("NONE")) {

                            Product removeProduct =
                                    selectProductBySku(removeSku);

                            if (removeProduct != null) {

                                cart.remove(removeProduct);

                                System.out.println("Product removed.");
                            } else {

                                System.out.println("Product not found.");
                            }
                        }
                    }

                    break;

                case 0:

                    System.out.println("Goodbye!");
                    break;

                default:

                    System.out.println("Invalid Option");
            }

        } while (choice != 0);

        scanner.close();
    }

    public static Product selectProductBySku(String sku) {

        List<Product> products = FileManager.readProduct();

        for (Product product : products) {

            if (product.getSku().equalsIgnoreCase(sku)) {
                return product;
            }
        }

        return null;
    }

    public static void showAllProducts() {

        System.out.println("\nAVAILABLE PRODUCTS");

        List<Product> products = FileManager.readProduct();

        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static void searchProductsByName(String name) {

        List<Product> products = FileManager.readProduct();

        for (Product product : products) {

            if (product.getName()
                    .toLowerCase()
                    .contains(name.toLowerCase())) {

                System.out.println(product);
            }
        }
    }

    public static void searchProductsByPriceRange(
            double minPrice,
            double maxPrice) {

        List<Product> products = FileManager.readProduct();

        for (Product product : products) {

            if (product.getPrice() >= minPrice
                    && product.getPrice() <= maxPrice) {

                System.out.println(product);
            }
        }
    }

    public static void searchProductsByCategory(String category) {

        List<Product> products = FileManager.readProduct();

        for (Product product : products) {

            if (product.getDepartment()
                    .equalsIgnoreCase(category)) {

                System.out.println(product);
            }
        }
    }
}