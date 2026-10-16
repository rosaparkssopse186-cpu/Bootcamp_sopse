package org.example;

import java.util.Scanner;
public class Calculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the loan principal: ");
        double principal = input.nextDouble();

        System.out.print("Enter the annual interest rate (%): ");
        double annualRate = input.nextDouble();

        System.out.print("Enter the loan length in years: ");
        int years = input.nextInt();

        double monthlyRate = (annualRate / 100) / 12;
        int numberOfPayments = years * 12;

        double monthlyPayment =
                  principal * (monthlyRate * Math.pow(1 + monthlyRate, numberOfPayments))
                        / (Math.pow(1 + monthlyRate, numberOfPayments) - 1);

        double totalInterest =
                (monthlyPayment * numberOfPayments) - principal;

        System.out.printf("%nMonthly Payment: $%.2f%n", monthlyPayment);
        System.out.printf("Total Interest Paid: $%.2f%n", totalInterest);

        input.close();
    }
}