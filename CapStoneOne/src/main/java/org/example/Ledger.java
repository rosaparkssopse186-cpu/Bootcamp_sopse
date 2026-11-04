package org.example;

import java.util.Collections;
import java.util.List;

public class Ledger {

    public static void displayAll(
            List<Transaction> transactions) {

        Collections.reverse(transactions);

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public static void displayDeposits(
            List<Transaction> transactions) {

        for (Transaction transaction : transactions) {

            if (transaction.getAmount() > 0) {
                System.out.println(transaction);
            }
        }
    }
    public static void displayPayments(
            List<Transaction> transactions) {

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                System.out.println(transaction);
            }
        }

    }

}
