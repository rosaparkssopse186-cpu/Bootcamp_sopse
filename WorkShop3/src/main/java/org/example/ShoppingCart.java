package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    public List<Product> cart = new ArrayList<>();

    public void addToCart(Product product) {
        cart.add(product);
    }

    public void removeFromCart(Product product) {
        cart.remove(product);
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

        System.out.println("Cart Total: $" + getCartTotal());
    }
}