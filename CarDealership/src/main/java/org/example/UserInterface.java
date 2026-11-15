
package org.example;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private final DealershipFileManager fileManager;
    private final Scanner scanner;

    // Soft Neutral Pastel Color Palette
    private static final String RESET = "\u001B[0m";
    private static final String CHAMPAGNE = "\u001B[38;5;223m";
    private static final String SAGE = "\u001B[38;5;151m";
    private static final String LAVENDER = "\u001B[38;5;183m";
    private static final String TEAL = "\u001B[38;5;152m";
    private static final String IVORY = "\u001B[38;5;230m";
    private static final String TAUPE = "\u001B[38;5;180m";
    private static final String BOLD = "\u001B[1m";

    // Constructor
    public UserInterface() {
        this.fileManager = new DealershipFileManager();
        this.scanner = new Scanner(System.in);
    }

    // Load Dealership
    private void init() {
        dealership = fileManager.getDealership();
    }

    // Main Application
    public void display() {

        init();
        displayWelcomeScreen();

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = readInt(
                    CHAMPAGNE + "\nEnter your selection: " + RESET
            );

            switch (choice) {

                case 1:
                    processGetVehiclesByPriceRequest();
                    break;

                case 2:
                    processGetVehiclesByMakeModelRequest();
                    break;

                case 3:
                    processGetVehiclesByYearRequest();
                    break;

                case 4:
                    processGetVehiclesByColorRequest();
                    break;

                case 5:
                    processGetVehiclesByMileageRequest();
                    break;

                case 6:
                    processGetVehiclesByTypeRequest();
                    break;

                case 7:
                    processGetAllVehiclesRequest();
                    break;

                case 8:
                    processAddVehicleRequest();
                    break;

                case 9:
                    processRemoveVehicleRequest();
                    break;

                case 0:
                    running = false;

                    System.out.println(LAVENDER + BOLD +
                            "\nThank you for visiting Mercedes-Benz!"
                            + RESET);

                    System.out.println(TAUPE +
                            "The Best or Nothing." + RESET);
                    break;

                default:
                    System.out.println(LAVENDER +
                            "\nInvalid selection. Please try again."
                            + RESET);
            }
        }
    }

    // Welcome Screen
    private void displayWelcomeScreen() {

        System.out.println();

        System.out.println(CHAMPAGNE + BOLD +
                "          MERCEDES-BENZ" + RESET);

        System.out.println(TAUPE +
                "          LUXURY MOTORS" + RESET);

        System.out.println(SAGE +
                "       Engineered for Excellence" + RESET);

        System.out.println();

        System.out.println(CHAMPAGNE + BOLD +
                "Welcome to " + dealership.getName() + RESET);

        System.out.println(IVORY +
                dealership.getAddress() + RESET);

        System.out.println(IVORY +
                "Phone: " + dealership.getPhone() + RESET);

        System.out.println(LAVENDER +
                "\nThe Best or Nothing." + RESET);

        System.out.println();
    }

    // Display Main Menu
    private void displayMenu() {

        System.out.println();

        System.out.println(CHAMPAGNE + BOLD +
                "       MERCEDES-BENZ MAIN MENU" + RESET);

        System.out.println();

        System.out.println(TEAL +
                "1.  Find Vehicles by Price" + RESET);

        System.out.println(SAGE +
                "2.  Find Vehicles by Make / Model" + RESET);

        System.out.println(LAVENDER +
                "3.  Find Vehicles by Year" + RESET);

        System.out.println(CHAMPAGNE +
                "4.  Find Vehicles by Color" + RESET);

        System.out.println(TEAL +
                "5.  Find Vehicles by Mileage" + RESET);

        System.out.println(SAGE +
                "6.  Find Vehicles by Type" + RESET);

        System.out.println(LAVENDER +
                "7.  List All Vehicles" + RESET);

        System.out.println(CHAMPAGNE +
                "8.  Add a Vehicle" + RESET);

        System.out.println(TEAL +
                "9.  Remove a Vehicle" + RESET);

        System.out.println();

        System.out.println(TAUPE +
                "0.  Exit Dealership" + RESET);

        System.out.println();

        System.out.println(CHAMPAGNE + BOLD +
                "       THE BEST OR NOTHING." + RESET);
    }

    // Display Vehicle Inventory
    private void displayVehicles(List<Vehicle> vehicles) {

        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println(LAVENDER +
                    "\nNo vehicles found." + RESET);
            return;
        }

        System.out.println();

        System.out.println(CHAMPAGNE + BOLD +
                "MERCEDES-BENZ INVENTORY" + RESET);

        System.out.printf(
                "%-18s | %-5s | %-13s | %-10s | %-6s | %-10s | %-8s | %14s%n",
                "VIN", "YEAR", "MAKE", "MODEL", "TYPE",
                "COLOR", "MILEAGE", "PRICE"
        );

        System.out.println(TAUPE +
                "--------------------------------------------------------------------------"
                + "--------------------------------" + RESET);

        for (Vehicle vehicle : vehicles) {
            System.out.println(IVORY + vehicle + RESET);
        }

        System.out.println(SAGE + BOLD +
                "\nTotal vehicles found: " + vehicles.size() + RESET);
    }

    // 1. Search Vehicles by Price
    private void processGetVehiclesByPriceRequest() {

        System.out.println(CHAMPAGNE +
                "\n--- SEARCH BY PRICE ---" + RESET);

        double minPrice = readDouble("Minimum price: $");
        double maxPrice = readDouble("Maximum price: $");

        if (minPrice < 0 || maxPrice < minPrice) {
            System.out.println(LAVENDER +
                    "Invalid price range." + RESET);
            return;
        }

        List<Vehicle> vehicles =
                dealership.getVehiclesByPrice(minPrice, maxPrice);

        displayVehicles(vehicles);
    }

    // 2. Search Vehicles by Make or Model
    private void processGetVehiclesByMakeModelRequest() {

        System.out.println(CHAMPAGNE +
                "\n--- SEARCH BY MAKE / MODEL ---" + RESET);

        String search = readText("Enter make or model: ");

        List<Vehicle> vehicles =
                dealership.getVehiclesByMakeModel(search);

        displayVehicles(vehicles);
    }

    // 3. Search Vehicles by Year
    private void processGetVehiclesByYearRequest() {

        System.out.println(CHAMPAGNE +
                "\n--- SEARCH BY YEAR ---" + RESET);

        int minYear = readInt("Starting year: ");
        int maxYear = readInt("Ending year: ");

        if (minYear > maxYear) {
            System.out.println(LAVENDER +
                    "Invalid year range." + RESET);
            return;
        }

        List<Vehicle> vehicles =
                dealership.getVehiclesByYear(minYear, maxYear);

        displayVehicles(vehicles);
    }

    // 4. Search Vehicles by Color
    private void processGetVehiclesByColorRequest() {

        System.out.println(CHAMPAGNE +
                "\n--- SEARCH BY COLOR ---" + RESET);

        String color = readText("Enter vehicle color: ");

        List<Vehicle> vehicles =
                dealership.getVehiclesByColor(color);

        displayVehicles(vehicles);
    }

    // 5. Search Vehicles by Mileage
    private void processGetVehiclesByMileageRequest() {

        System.out.println(CHAMPAGNE +
                "\n--- SEARCH BY MILEAGE ---" + RESET);

        int minMileage = readInt("Minimum mileage: ");
        int maxMileage = readInt("Maximum mileage: ");

        if (minMileage < 0 || maxMileage < minMileage) {
            System.out.println(LAVENDER +
                    "Invalid mileage range." + RESET);
            return;
        }

        List<Vehicle> vehicles =
                dealership.getVehiclesByMileage(minMileage, maxMileage);

        displayVehicles(vehicles);
    }

    // 6. Search Vehicles by Type
    private void processGetVehiclesByTypeRequest() {

        System.out.println(CHAMPAGNE +
                "\n--- SEARCH BY VEHICLE TYPE ---" + RESET);

        System.out.println(IVORY +
                "Available types: car, truck, SUV, van" + RESET);

        String type = readText("Enter vehicle type: ");

        List<Vehicle> vehicles =
                dealership.getVehiclesByType(type);

        displayVehicles(vehicles);
    }

    // 7. Display All Vehicles
    private void processGetAllVehiclesRequest() {

        System.out.println(CHAMPAGNE +
                "\n--- ALL MERCEDES-BENZ VEHICLES ---" + RESET);

        List<Vehicle> vehicles = dealership.getAllVehicles();

        displayVehicles(vehicles);
    }

    // 8. Add a Vehicle
    private void processAddVehicleRequest() {

        System.out.println(CHAMPAGNE +
                "\n--- ADD A VEHICLE ---" + RESET);

        String vin = readText("Enter VIN: ");

        if (dealership.getVehicleByVin(vin) != null) {
            System.out.println(LAVENDER +
                    "A vehicle with this VIN already exists." + RESET);
            return;
        }

        int year = readInt("Enter year: ");
        String make = readText("Enter make (Mercedes-Benz): ");
        String model = readText("Enter model (C300, GLE350, G63, etc.): ");
        String type = readText("Enter type (car, truck, SUV, van): ");
        String color = readText("Enter color: ");
        int mileage = readInt("Enter mileage: ");
        double price = readDouble("Enter price: $");

        if (year < 1886 || year > 2100 ||
                mileage < 0 || price < 0) {

            System.out.println(LAVENDER +
                    "Invalid vehicle information." + RESET);
            return;
        }

        Vehicle vehicle = new Vehicle(
                vin, year, make, model, type, color, mileage, price
        );

        dealership.addVehicle(vehicle);

        fileManager.saveDealership(dealership);

        System.out.println(SAGE + BOLD +
                "\nVehicle successfully added!" + RESET);

        System.out.println(IVORY + vehicle + RESET);
    }

    // 9. Remove a Vehicle
    private void processRemoveVehicleRequest() {

        System.out.println(CHAMPAGNE +
                "\n--- REMOVE A VEHICLE ---" + RESET);

        String vin = readText("Enter VIN of vehicle to remove: ");

        Vehicle vehicle = dealership.getVehicleByVin(vin);

        if (vehicle == null) {
            System.out.println(LAVENDER +
                    "Vehicle not found." + RESET);
            return;
        }

        System.out.println(CHAMPAGNE +
                "\nVehicle selected:" + RESET);

        System.out.println(IVORY + vehicle + RESET);

        String confirmation = readText(
                "Are you sure you want to remove this vehicle? (Y/N): "
        );

        if (confirmation.equalsIgnoreCase("Y")) {

            dealership.removeVehicle(vehicle);

            fileManager.saveDealership(dealership);

            System.out.println(SAGE +
                    "\nVehicle successfully removed." + RESET);

        } else {
            System.out.println(TAUPE +
                    "Removal cancelled." + RESET);
        }
    }

    // Read Text Safely
    private String readText(String message) {

        System.out.print(CHAMPAGNE + message + RESET);

        return scanner.nextLine().trim();
    }

    // Read Integer Safely
    private int readInt(String message) {

        while (true) {

            System.out.print(CHAMPAGNE + message + RESET);

            try {
                return Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println(LAVENDER +
                        "Please enter a valid whole number." + RESET);
            }
        }
    }

    // Read Decimal Safely
    private double readDouble(String message) {

        while (true) {

            System.out.print(CHAMPAGNE + message + RESET);

            try {
                return Double.parseDouble(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println(LAVENDER +
                        "Please enter a valid number." + RESET);
            }
        }
    }
}
