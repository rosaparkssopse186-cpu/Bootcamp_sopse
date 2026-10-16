package org.example;

import java.util.Scanner;

public class Calculator2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the deposit amount: ");
        double principal = input.nextDouble();

        System.out.println("Enter the annual interest rate (%): ");
        double annualRate = input.nextDouble();

        System.out.println("Enter the number of years: ");
        int years = input.nextInt();

        double rate = annualRate / 100.0;

        int daysPerYear = 365;
        int totalDays = daysPerYear * years;

        double futureValue =
                principal * Math.pow (1 + (rate / daysPerYear), totalDays);

        double interestEarned = futureValue - principal;

        System.out.printf("\nFuture Value: $%.2f%n", futureValue);
        System.out.printf("Total Interest Earned: $%.2f%n", interestEarned);

        input.close();
    }
}
