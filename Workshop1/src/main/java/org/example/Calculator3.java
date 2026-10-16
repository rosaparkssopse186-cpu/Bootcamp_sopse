package org.example;

import java.util.Scanner;

public class Calculator3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the monthly payout amount: ");
        double monthlyPayout = input.nextDouble();

        System.out.println("Enter the annual interest rate (%): ");
        double annualRate = input.nextDouble();

        System.out.println("Enter the number of years: ");
        int years = input.nextInt();

        double monthlyRate = (annualRate / 100.0) / 12;
        int totalPayments = years * 12;

        double presentValue = monthlyPayout *
                ((1 - Math.pow(1 + monthlyRate, -totalPayments))
                        / monthlyRate);

        System.out.printf("\nPresent Value of the Annuity: $%.2f%n", presentValue);

        input.close();
    }
}