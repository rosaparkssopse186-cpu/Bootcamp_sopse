
package org.example;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private final DealershipFileManager fileManager;
    private final Scanner scanner;

    // Luxury Pink Color Palette
    private static final String RESET = "\u001B[0m";
    private static final String PINK = "\u001B[38;5;213m";
    private static final String HOT_PINK = "\u001B[38;5;205m";
    private static final String WHITE = "\u001B[97m";
    private static final String SILVER = "\u001B[38;5;250m";
    private static final String BOLD = "\u001B[1m";

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

            int choice = readInt(PINK + "\n  Enter your selection: " + RESET);

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

                    System.out.println(HOT_PINK + BOLD +
                            "\n  Thank you for visiting Pink Label Luxury Motors!");
                    System.out.println(WHITE +

                default:
                    System.out.println(HOT_PINK +
                            "\n  Invalid selection. Please try again." + RESET);
            }
        }
    }

    // Welcome Screen
    private void displayWelcomeScreen() {

        System.out.println(PINK + BOLD);
        System.out.println();
        System.out.println("  ==================================================");
        System.out.println("                                                    ");
        System.out.println("              MERCEDES-BENZ                         ");
        System.out.println("                                                    ");
        System.out.println("           PINK LABEL LUXURY MOTORS                 ");
        System.out.println("                                                    ");
        System.out.println("       Pretty in Pink. Powerful in Every Drive.     ");
        System.out.println("                                                    ");
        System.out.println("  ==================================================");
        System.out.println(RESET);

        System.out.println(HOT_PINK + BOLD +
                "       Welcome to " + dealership.getName() + RESET);

        System.out.println(WHITE +
                "       " + dealership.getAddress());

        System.out.println(
                "       Phone: " + dealership.getPhone() + RESET);

        "  Where Luxury Meets Confidence.");
                    System.out.println(PINK +
                            "  Pretty in Pink. Powerful in Every Drive. ♡");
                    System.out.println(RESET);
                    break;
        System.out.println(PINK +
                "\n       Luxury Has No Lane. Drive Yours. ♡" + RESET);
    }

    // Display Main Menu
    private void displayMenu() {

        System.out.println(PINK + BOLD);
        System.out.println();
        System.out.println("  ╔════════════════════════════════════════════╗");
        System.out.println("  ║                                            ║");
        System.out.println("  ║          PINK LABEL LUXURY                 ║");
        System.out.println("  ║             MAIN MENU                      ║");
        System.out.println("  ║                                            ║");
        System.out.println("  ╠════════════════════════════════════════════╣");

        System.out.println(WHITE);
        System.out.println("  ║                                            ║");
        System.out.println("  ║   1.  Find Vehicles by Price               ║");
        System.out.println("  ║   2.  Find Vehicles by Make / Model        ║");
        System.out.println("  ║   3.  Find Vehicles by Year                ║");
        System.out.println("  ║   4.  Find Vehicles by Color               ║");
        System.out.println("  ║   5.  Find Vehicles by Mileage             ║");
        System.out.println("  ║   6.  Find Vehicles by Type                ║");
        System.out.println("  ║   7.  View All Luxury Vehicles             ║");
        System.out.println("  ║   8.  Add a Vehicle                        ║");
        System.out.println("  ║   9.  Remove a Vehicle                     ║");
        System.out.println("  ║                                            ║");

        System.out.println(PINK + BOLD);
        System.out.println("  ║   0.  Exit Dealership                      ║");
        System.out.println("  ║                                            ║");
        System.out.println("  ╠════════════════════════════════════════════╣");
        System.out.println("  ║                                            ║");
        System.out.println("  ║     WHERE LUXURY MEETS CONFIDENCE          ║");
        System.out.println("  ║                                            ║");
        System.out.println("  ╚════════════════════════════════════════════╝");

        System.out.println(RESET);
    }

    // Display Vehicle Inventory
    private void displayVehicles(List<Vehicle> vehicles) {

        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println(HOT_PINK +
                    "\n  No vehicles found." + RESET);
            return;
        }

        System.out.println(PINK + BOLD);
        System.out.println("\n  =============== PINK LABEL INVENTORY ===============");

        System.out.printf(
                "%-18s | %-5s | %-13s | %-10s | %-6s | %-10s | %-8s | %14s%n",
                "VIN", "YEAR", "MAKE", "MODEL", "TYPE",
                "COLOR", "MILEAGE", "PRICE"
        );

        System.out.println(SILVER +
                "--------------------------------------------------------------------------" +
                "--------------------------------" + RESET);

        for (Vehicle vehicle : vehicles) {
            System.out.println(WHITE + vehicle + RESET);
        }

        System.out.println(PINK + BOLD +
                "\n  Total vehicles found: " + vehicles.size() + RESET);
    }

    // 1. Search Vehicles by Price
    private void processGetVehiclesByPriceRequest() {

        System.out.println(PINK + "\n  --- SEARCH BY PRICE ---" + RESET);

        double minPrice = readDouble("  Minimum price: $");
        double maxPrice = readDouble("  Maximum price: $");

        if (minPrice < 0 || maxPrice < minPrice) {
            System.out.println(HOT_PINK + "  Invalid price range." + RESET);
            return;
        }

        List<Vehicle> vehicles =
                dealership.getVehiclesByPrice(minPrice, maxPrice);

        displayVehicles(vehicles);
    }

    // 2. Search Vehicles by Make or Model
    private void processGetVehiclesByMakeModelRequest() {

        System.out.println(PINK + "\n  --- SEARCH BY MAKE / MODEL ---" + RESET);

        String search = readText("  Enter make or model: ");

        List<Vehicle> vehicles =
                dealership.getVehiclesByMakeModel(search);

        displayVehicles(vehicles);
    }

    // 3. Search Vehicles by Year
    private void processGetVehiclesByYearRequest() {

        System.out.println(PINK + "\n  --- SEARCH BY YEAR ---" + RESET);

        int minYear = readInt("  Starting year: ");
        int maxYear = readInt("  Ending year: ");

        if (minYear > maxYear) {
            System.out.println(HOT_PINK + "  Invalid year range." + RESET);
            return;
        }

        List<Vehicle> vehicles =
                dealership.getVehiclesByYear(minYear, maxYear);

        displayVehicles(vehicles);
    }

    // 4. Search Vehicles by Color
    private void processGetVehiclesByColorRequest() {

        System.out.println(PINK + "\n  --- SEARCH BY COLOR ---" + RESET);

        String color = readText("  Enter vehicle color: ");

        List<Vehicle> vehicles =
                dealership.getVehiclesByColor(color);

        displayVehicles(vehicles);
    }

    // 5. Search Vehicles by Mileage
    private void processGetVehiclesByMileageRequest() {

        System.out.println(PINK + "\n  --- SEARCH BY MILEAGE ---" + RESET);

        int minMileage = readInt("  Minimum mileage: ");
        int maxMileage = readInt("  Maximum mileage: ");

        if (minMileage < 0 || maxMileage < minMileage) {
            System.out.println(HOT_PINK + "  Invalid mileage range." + RESET);
            return;
        }

        List<Vehicle> vehicles =
                dealership.getVehiclesByMileage(minMileage, maxMileage);

        displayVehicles(vehicles);
    }

    // 6. Search Vehicles by Type
    private void processGetVehiclesByTypeRequest() {

        System.out.println(PINK + "\n  --- SEARCH BY VEHICLE TYPE ---" + RESET);

        System.out.println(WHITE +
                "  Available types: car, truck, SUV, van");

        String type = readText("  Enter vehicle type: ");

        List<Vehicle> vehicles =
                dealership.getVehiclesByType(type);

        displayVehicles(vehicles);
    }

    // 7. Display All Vehicles
    private void processGetAllVehiclesRequest() {

        System.out.println(PINK + "\n  --- ALL LUXURY VEHICLES ---" + RESET);

        List<Vehicle> vehicles = dealership.getAllVehicles();

        displayVehicles(vehicles);
    }

    // 8. Add a Vehicle
    private void processAddVehicleRequest() {

        System.out.println(PINK + "\n  --- ADD A LUXURY VEHICLE ---" + RESET);

        String vin = readText("  Enter VIN: ");

        if (dealership.getVehicleByVin(vin) != null) {
            System.out.println(HOT_PINK +
                    "  A vehicle with this VIN already exists." + RESET);
            return;
        }

        int year = readInt("  Enter year: ");
        String make = readText("  Enter make (Mercedes-Benz): ");
        String model = readText("  Enter model (C300, GLE350, etc.): ");
        String type = readText("  Enter type (car, truck, SUV, van): ");
        String color = readText("  Enter color: ");
        int mileage = readInt("  Enter mileage: ");
        double price = readDouble("  Enter price: $");

        if (year < 1886 || year > 2100 ||
                mileage < 0 || price < 0) {

            System.out.println(HOT_PINK +
                    "  Invalid vehicle information." + RESET);
            return;
        }

        Vehicle vehicle = new Vehicle(
                vin, year, make, model, type, color, mileage, price
        );

        dealership.addVehicle(vehicle);

        fileManager.saveDealership(dealership);

        System.out.println(PINK + BOLD +
                "\n  Vehicle successfully added to Pink Label Luxury!" + RESET);

        System.out.println(WHITE + vehicle + RESET);
    }

    // 9. Remove a Vehicle
    private void processRemoveVehicleRequest() {

        System.out.println(PINK + "\n  --- REMOVE A VEHICLE ---" + RESET);

        String vin = readText("  Enter VIN of vehicle to remove: ");

        Vehicle vehicle = dealership.getVehicleByVin(vin);

        if (vehicle == null) {
            System.out.println(HOT_PINK +
                    "  Vehicle not found." + RESET);
            return;
        }

        System.out.println(PINK + "\n  Vehicle selected:" + RESET);
        System.out.println(WHITE + vehicle + RESET);

        String confirmation = readText(
                "  Are you sure you want to remove this vehicle? (Y/N): "
        );

        if (confirmation.equalsIgnoreCase("Y")) {

            dealership.removeVehicle(vehicle);

            fileManager.saveDealership(dealership);

            System.out.println(PINK +
                    "\n  Vehicle successfully removed." + RESET);

        } else {
            System.out.println(WHITE +
                    "  Removal cancelled." + RESET);
        }
    }

    // Read Text Safely
    private String readText(String message) {

        System.out.print(PINK + message + RESET);

        return scanner.nextLine().trim();
    }

    // Read Integer Safely
    private int readInt(String message) {

        while (true) {

            System.out.print(PINK + message + RESET);

            try {
                return Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println(HOT_PINK +
                        "  Please enter a valid whole number." + RESET);
            }
        }
    }

    // Read Decimal Safely
    private double readDouble(String message) {

        while (true) {

            System.out.print(PINK + message + RESET);

            try {
                return Double.parseDouble(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println(HOT_PINK +
                        "  Please enter a valid number." + RESET);
            }
        }
    }
}