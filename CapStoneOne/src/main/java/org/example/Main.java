

package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    // =====================================
    // LUXURY TERMINAL COLOR PALETTE
    // =====================================

    static final String RESET = "\u001B[0m";
    static final String BOLD = "\u001B[1m";

    // Champagne Gold
    static final String GOLD = "\u001B[38;2;214;182;107m";

    // Soft Ivory
    static final String WHITE = "\u001B[38;2;245;240;230m";

    // Sage Green
    static final String GREEN = "\u001B[38;2;143;175;145m";

    // Dusty Blue
    static final String BLUE = "\u001B[38;2;169;184;200m";

    // Warm Gray
    static final String GRAY = "\u001B[38;2;119;119;119m";

    // Soft Amber for warnings and errors
    static final String AMBER = "\u001B[38;2;222;184;105m";

    static final String LINE =
            "================================================";

    public static void main(String[] args) {

        boolean running = true;

        while (running) {

            displayHomeScreen();

            String choice = scanner.nextLine()
                    .trim().toUpperCase();

            switch (choice) {

                case "D":
                    addDeposit();
                    break;

                case "P":
                    addPayment();
                    break;

                case "L":
                    showLedger();
                    break;

                case "X":
                    displayExitScreen();
                    running = false;
                    break;

                default:
                    showError("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    // =====================================
    // HOME SCREEN
    // =====================================

    public static void displayHomeScreen() {

        clearScreen();

        System.out.println(GOLD + BOLD);
        System.out.println(LINE);
        System.out.println("                 FINANCE TRACKER");
        System.out.println("          PERSONAL FINANCIAL MANAGEMENT");
        System.out.println(LINE + RESET);

        System.out.println();

        System.out.println(WHITE + BOLD +
                "                  WELCOME HOME" + RESET);

        System.out.println();

        System.out.println(GOLD + BOLD +
                "                    MAIN MENU" + RESET);

        System.out.println();

        System.out.println(GREEN +
                "              [D]  Add Deposit" + RESET);

        System.out.println();

        System.out.println(BLUE +
                "              [P]  Make Payment" + RESET);

        System.out.println();

        System.out.println(GOLD +
                "              [L]  View Ledger" + RESET);

        System.out.println();

        System.out.println(WHITE +
                "              [X]  Exit Application" + RESET);

        System.out.println();

        displayFooter();

        System.out.print(GOLD + BOLD +
                "          Select an option: " + RESET);
    }

    // =====================================
    // ADD DEPOSIT
    // =====================================

    public static void addDeposit() {

        clearScreen();

        displayHeader("ADD DEPOSIT");

        System.out.println();

        System.out.println(GREEN + BOLD +
                "          ENTER YOUR DEPOSIT DETAILS" + RESET);

        System.out.println();

        System.out.print(GOLD + "          Description : " + RESET);
        String description = scanner.nextLine();

        System.out.print(GOLD + "          Vendor      : " + RESET);
        String vendor = scanner.nextLine();

        double amount = getAmount();

        if (amount <= 0) {
            showError("Deposit amount must be greater than zero.");
            return;
        }

        Transaction transaction = new Transaction(
                LocalDate.now(),
                LocalTime.now(),
                description,
                vendor,
                amount
        );

        FileManager.saveTransaction(transaction);

        System.out.println();

        System.out.println(GREEN + BOLD +
                "          DEPOSIT SAVED SUCCESSFULLY!" + RESET);

        System.out.printf(GREEN +
                "          Amount Added: $%.2f%n" + RESET, amount);

        displayFooter();

        pauseScreen();
    }

    // =====================================
    // MAKE PAYMENT
    // =====================================

    public static void addPayment() {

        clearScreen();

        displayHeader("MAKE PAYMENT");

        System.out.println();

        System.out.println(BLUE + BOLD +
                "          ENTER YOUR PAYMENT DETAILS" + RESET);

        System.out.println();

        System.out.print(GOLD + "          Description : " + RESET);
        String description = scanner.nextLine();

        System.out.print(GOLD + "          Vendor      : " + RESET);
        String vendor = scanner.nextLine();

        double amount = getAmount();

        if (amount <= 0) {
            showError("Payment amount must be greater than zero.");
            return;
        }

        Transaction transaction = new Transaction(
                LocalDate.now(),
                LocalTime.now(),
                description,
                vendor,
                -amount
        );

        FileManager.saveTransaction(transaction);

        System.out.println();

        System.out.println(BLUE + BOLD +
                "          PAYMENT RECORDED SUCCESSFULLY!" + RESET);

        System.out.printf(BLUE +
                "          Amount Paid: $%.2f%n" + RESET, amount);

        displayFooter();

        pauseScreen();
    }

    // =====================================
    // LEDGER SCREEN
    // =====================================

    public static void showLedger() {

        boolean insideLedger = true;

        while (insideLedger) {

            clearScreen();

            ArrayList<Transaction> transactions =
                    FileManager.loadTransaction();

            displayHeader("ACCOUNT LEDGER");

            System.out.println();

            System.out.println(GOLD + BOLD +
                    "          TRANSACTION MENU" + RESET);

            System.out.println();

            System.out.println(WHITE +
                    "          [A]  All Transactions" + RESET);

            System.out.println();

            System.out.println(GREEN +
                    "          [D]  Deposits Only" + RESET);

            System.out.println();

            System.out.println(BLUE +
                    "          [P]  Payments Only" + RESET);

            System.out.println();

            System.out.println(GOLD +
                    "          [R]  Financial Reports" + RESET);

            System.out.println();

            System.out.println(WHITE +
                    "          [H]  Return Home" + RESET);

            System.out.println();

            displayFooter();

            System.out.print(GOLD + BOLD +
                    "          Select an option: " + RESET);

            String choice = scanner.nextLine()
                    .trim().toUpperCase();

            switch (choice) {

                case "A":
                    clearScreen();
                    displayHeader("ALL TRANSACTIONS");
                    Ledger.displayAll(transactions);
                    pauseScreen();
                    break;

                case "D":
                    clearScreen();
                    displayHeader("DEPOSIT HISTORY");
                    Ledger.displayDeposits(transactions);
                    pauseScreen();
                    break;

                case "P":
                    clearScreen();
                    displayHeader("PAYMENT HISTORY");
                    Ledger.displayPayments(transactions);
                    pauseScreen();
                    break;

                case "R":
                    showReports(transactions);
                    break;

                case "H":
                    insideLedger = false;
                    break;

                default:
                    showError("Invalid option. Please try again.");
            }
        }
    }

    // =====================================
    // FINANCIAL REPORTS SCREEN
    // =====================================

    public static void showReports(
            ArrayList<Transaction> transactions) {

        boolean inReport = true;

        while (inReport) {

            clearScreen();

            displayHeader("FINANCIAL REPORTS");

            System.out.println();

            System.out.println(GOLD + BOLD +
                    "          ANALYZE YOUR FINANCES" + RESET);

            System.out.println();

            System.out.println(WHITE +
                    "          [1]  Month To Date" + RESET);

            System.out.println();

            System.out.println(WHITE +
                    "          [2]  Previous Month" + RESET);

            System.out.println();

            System.out.println(WHITE +
                    "          [3]  Year To Date" + RESET);

            System.out.println();

            System.out.println(WHITE +
                    "          [4]  Previous Year" + RESET);

            System.out.println();

            System.out.println(BLUE +
                    "          [5]  Search Vendor" + RESET);

            System.out.println();

            System.out.println(GREEN +
                    "          [6]  Account Summary" + RESET);

            System.out.println();

            System.out.println(GOLD +
                    "          [0]  Back to Ledger" + RESET);

            System.out.println();

            displayFooter();

            System.out.print(GOLD + BOLD +
                    "          Select an option: " + RESET);

            String choice = scanner.nextLine().trim();

            switch (choice) {

                case "1":
                    clearScreen();
                    displayHeader("MONTH TO DATE");
                    Reports.monthToDate(transactions);
                    pauseScreen();
                    break;

                case "2":
                    clearScreen();
                    displayHeader("PREVIOUS MONTH");
                    Reports.previousMonth(transactions);
                    pauseScreen();
                    break;

                case "3":
                    clearScreen();
                    displayHeader("YEAR TO DATE");
                    Reports.yearToDate(transactions);
                    pauseScreen();
                    break;

                case "4":
                    clearScreen();
                    displayHeader("PREVIOUS YEAR");
                    Reports.previousYear(transactions);
                    pauseScreen();
                    break;

                case "5":
                    clearScreen();
                    displayHeader("SEARCH VENDOR");
                    Reports.searchVendor(transactions, scanner);
                    pauseScreen();
                    break;

                case "6":
                    clearScreen();
                    displayHeader("ACCOUNT SUMMARY");
                    Reports.accountSummary(transactions);
                    pauseScreen();
                    break;

                case "0":
                    inReport = false;
                    break;

                default:
                    showError("Invalid option. Please try again.");
            }
        }
    }

    // =====================================
    // REUSABLE DISPLAY METHODS
    // =====================================

    public static void displayHeader(String title) {

        System.out.println(GOLD + BOLD);
        System.out.println(LINE);
        System.out.println("                 FINANCE TRACKER");
        System.out.println(LINE);

        System.out.println(WHITE + BOLD +
                "                 " + title + RESET);

        System.out.println(GOLD + LINE + RESET);
    }

    public static void displayFooter() {

        System.out.println(GOLD + LINE + RESET);

        System.out.println(GRAY +
                "             YOUR MONEY. YOUR CONTROL." + RESET);

        System.out.println(GOLD + LINE + RESET);
    }

    // =====================================
    // AMOUNT VALIDATION
    // =====================================

    public static double getAmount() {

        while (true) {

            System.out.print(GOLD +
                    "          Amount      : $" + RESET);

            try {

                double amount =
                        Double.parseDouble(scanner.nextLine().trim());

                if (Double.isFinite(amount) && amount > 0) {
                    return amount;
                }

                showError("Enter an amount greater than zero.");

            } catch (NumberFormatException e) {

                showError("Please enter a valid number.");
            }
        }
    }

    // =====================================
    // ERROR MESSAGE
    // =====================================

    public static void showError(String message) {

        System.out.println();

        System.out.println(AMBER + BOLD +
                "          NOTICE: " + message + RESET);

        System.out.println();
    }

    // =====================================
    // CLEAR SCREEN
    // =====================================

    public static void clearScreen() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // =====================================
    // PAUSE SCREEN
    // =====================================

    public static void pauseScreen() {

        System.out.println();

        System.out.print(GOLD +
                "          Press ENTER to continue..." + RESET);

        scanner.nextLine();
    }

    // =====================================
    // EXIT SCREEN
    // =====================================

    public static void displayExitScreen() {

        clearScreen();

        System.out.println(GOLD + BOLD);
        System.out.println(LINE);

        System.out.println();

        System.out.println("                 FINANCE TRACKER");

        System.out.println();

        System.out.println(WHITE +
                "          THANK YOU FOR USING OUR APP!");

        System.out.println();

        System.out.println(GREEN +
                "                HAVE A GREAT DAY!");

        System.out.println();

        System.out.println(LINE + RESET);

        System.out.println();

        System.out.println(GRAY +
                "             YOUR MONEY. YOUR CONTROL." + RESET);

        System.out.println();
    }
}