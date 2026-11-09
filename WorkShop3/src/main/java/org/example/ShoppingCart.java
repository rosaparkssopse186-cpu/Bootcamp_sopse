package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Product> cart = new ArrayList<>();

    public void addToCart(Product product) {
        cart.add(product);
    }

    public void removeFromCart(Product product) {
        cart.remove(product);
    }

    public List<Product> getCart() {
        return cart;
    }

    public double getCartTotal() {

        double total = 0;

        for (Product product : cart) {
            total += product.getPrice();
        }

        return total;
    }

    public void displayCart() {

        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        for (Product product : cart) {
            System.out.println(product);
        }

        System.out.printf("Cart Total: $%.2f%n", getCartTotal());
    }
}