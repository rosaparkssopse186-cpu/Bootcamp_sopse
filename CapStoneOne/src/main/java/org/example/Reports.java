package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Reports {

    public static void monthToDate(
            List<Transaction> transactions) {

        LocalDate today = LocalDate.now();

        for (Transaction transaction : transactions) {

            if (transaction.getDate().getMonth() ==
                    today.getMonth()
                    &&
                    transaction.getDate().getYear() ==
                            today.getYear()) {

                System.out.println(transaction);
            }        }

    }

    public static void previousMonth(
            List<Transaction> transactions) {
        boolean found = false;
        LocalDate previous =
                LocalDate.now().minusMonths(1);

        for (Transaction transaction : transactions) {

            if (transaction.getDate().getMonth() ==
                    previous.getMonth()
                    &&
                    transaction.getDate().getYear() ==
                            previous.getYear()) {

                System.out.println(transaction);
                found = true;
            }
        }
        if (!found){
            System.out.println(" No Record for the Previous Month");
        }

    }

    public static void yearToDate(
            List<Transaction> transactions) {

        int currentYear = LocalDate.now().getYear();

        for (Transaction transaction : transactions) {

            if (transaction.getDate().getYear()
                    == currentYear) {

                System.out.println(transaction);
            }
        }
    }

    public static void previousYear(
            List<Transaction> transactions) {
        boolean found = false;
        int previousYear = LocalDate.now().getYear() - 1;

        for (Transaction transaction : transactions) {

            if (transaction.getDate().getYear()
                    == previousYear) {

                System.out.println(transaction);
                found = true;
            }
        }
        if (!found){
                System.out.println(" No Record for the Previous Year");
        }
    }

    public static void searchVendor(
            List<Transaction> transactions,
            Scanner scanner) {

        System.out.println("vendor: ");
        String vendor = scanner.nextLine();

        for (Transaction transaction : transactions) {

            if (transaction.getVendor().equalsIgnoreCase(vendor)) {
//            return statement
                System.out.println(transaction);
            }

        }
    }

    public static void accountSummary(
            List<Transaction> transactions) {

        double deposits = 0;
        double payments = 0;

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
//                return statement
                deposits += transaction.getAmount();
                } else {
//                return statement
                    payments += transaction.getAmount();
                }
            }
            System.out.println("Total deposits: $" + deposits);
            System.out.println("Total Payment: $" + payments);
            System.out.println("Balance: $" +(deposits + payments));
    }
}




