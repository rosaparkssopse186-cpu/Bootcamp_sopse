package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Product> products = FileManager.readProduct();

        ShoppingCart shoppingCart = new ShoppingCart();

        int choice;

        do {

            System.out.println("\n========== ONLINE STORE ==========");
            System.out.println("1. Display Products");
            System.out.println("2. Display Cart");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    System.out.println("\n========== AVAILABLE PRODUCTS ==========");

                    showAllProducts(products);

                    if (products.isEmpty()) {
                        System.out.println("No products available.");
                        break;
                    }

                    System.out.print("\nEnter SKU to add to cart: ");

                    String sku = scanner.next();

                    Product selected = selectProductBySku(sku, products);

                    if (selected != null) {

                        shoppingCart.addToCart(selected);

                        System.out.println(
                                selected.getName() + " added to cart."
                        );

                    } else {

                        System.out.println("Product not found.");
                    }

                    break;

                case 2:

                    System.out.println("\n========== SHOPPING CART ==========");

                    shoppingCart.displayCart();

                    if (!shoppingCart.getCart().isEmpty()) {

                        System.out.print(
                                "\nEnter SKU to remove or type NONE: "
                        );

                        String removeSku = scanner.next();

                        if (!removeSku.equalsIgnoreCase("NONE")) {

                            Product removeProduct = selectProductBySku(
                                    removeSku,
                                    shoppingCart.getCart()
                            );

                            if (removeProduct != null) {

                                shoppingCart.removeFromCart(removeProduct);

                                System.out.println("Product removed.");

                            } else {

                                System.out.println(
                                        "Product not found in cart."
                                );
                            }
                        }
                    }

                    break;

                case 0:

                    System.out.println("Thank you for shopping. Goodbye!");

                    break;

                default:

                    System.out.println("Invalid option. Please try again.");
            }

        } while (choice != 0);

        scanner.close();
    }

    public static Product selectProductBySku(
            String sku, List<Product> products) {

        for (Product product : products) {

            if (product.getSku().equalsIgnoreCase(sku)) {
                return product;
            }
        }

        return null;
    }

    public static void showAllProducts(List<Product> products) {

        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static void searchProductsByName(String name) {

        List<Product> products = FileManager.readProduct();

        for (Product product : products) {

            if (product.getName().toLowerCase()
                    .contains(name.toLowerCase())) {

                System.out.println(product);
            }
        }
    }

    public static void searchProductsByPriceRange(
            double minPrice, double maxPrice) {

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