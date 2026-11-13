
package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DealershipFileManager {

    private final Path inventoryPath =
            Paths.get("src/main/resources/inventory.csv");

    // Read dealership information and inventory

    public Dealership getDealership() {

        if (!Files.exists(inventoryPath)) {
            System.out.println("Inventory file not found. Creating starter inventory.");

            Dealership dealership = createDefaultDealership();
            saveDealership(dealership);

            return dealership;
        }

        try (BufferedReader reader = Files.newBufferedReader(inventoryPath)) {

            String dealershipLine = reader.readLine();

            if (dealershipLine == null) {
                throw new IOException("Inventory file is empty.");
            }

            String[] dealershipData = dealershipLine.split("\\|", -1);

            if (dealershipData.length != 3) {
                throw new IOException("Invalid dealership information.");
            }

            Dealership dealership = new Dealership(
                    dealershipData[0],
                    dealershipData[1],
                    dealershipData[2]
            );

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                try {
                    String[] vehicleData = line.split("\\|", -1);

                    if (vehicleData.length != 8) {
                        System.out.println("Skipping invalid vehicle record.");
                        continue;
                    }

                    Vehicle vehicle = new Vehicle(
                            vehicleData[0],
                            Integer.parseInt(vehicleData[1]),
                            vehicleData[2],
                            vehicleData[3],
                            vehicleData[4],
                            vehicleData[5],
                            Integer.parseInt(vehicleData[6]),
                            Double.parseDouble(vehicleData[7])
                    );

                    dealership.addVehicle(vehicle);

                } catch (NumberFormatException e) {
                    System.out.println("Skipping vehicle with invalid numeric data.");
                }
            }

            return dealership;

        } catch (IOException e) {

            System.out.println("Error reading inventory: " + e.getMessage());

            return createDefaultDealership();
        }
    }

    // Save dealership and inventory

    public void saveDealership(Dealership dealership) {

        try {
            Path parent = inventoryPath.getParent();

            if (parent != null) {
                Files.createDirectories(parent);
            }

            try (BufferedWriter writer =
                         Files.newBufferedWriter(inventoryPath)) {

                // Save dealership information
                writer.write(
                        dealership.getName() + "|" +
                                dealership.getAddress() + "|" +
                                dealership.getPhone()
                );

                writer.newLine();

                // Save every vehicle
                for (Vehicle vehicle : dealership.getAllVehicles()) {

                    writer.write(
                            vehicle.getVin() + "|" +
                                    vehicle.getYear() + "|" +
                                    vehicle.getMake() + "|" +
                                    vehicle.getModel() + "|" +
                                    vehicle.getVehicleType() + "|" +
                                    vehicle.getColor() + "|" +
                                    vehicle.getOdometer() + "|" +
                                    vehicle.getPrice()
                    );

                    writer.newLine();
                }
            }

            System.out.println("Inventory successfully saved.");

        } catch (IOException e) {

            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    // Create starter dealership if the file does not exist

    private Dealership createDefaultDealership() {

        Dealership dealership = new Dealership(
                "Mercedes-Benz Luxury Motors",
                "Charlotte, North Carolina",
                "704-555-2026"
        );

        dealership.addVehicle(new Vehicle(
                "W1KWF8DB1MR123456", 2021, "Mercedes-Benz",
                "C300", "car", "Black", 28500, 32995.00
        ));

        dealership.addVehicle(new Vehicle(
                "W1KZF8EB2MA234567", 2022, "Mercedes-Benz",
                "E350", "car", "White", 19000, 45995.00
        ));

        dealership.addVehicle(new Vehicle(
                "W1K6G7GB3NA345678", 2023, "Mercedes-Benz",
                "S580", "car", "Black", 12000, 89995.00
        ));

        dealership.addVehicle(new Vehicle(
                "4JGFB4KB4NA456789", 2022, "Mercedes-Benz",
                "GLE350", "SUV", "Silver", 22000, 48995.00
        ));

        dealership.addVehicle(new Vehicle(
                "4JGFF5KE5PA567890", 2023, "Mercedes-Benz",
                "GLS450", "SUV", "White", 15000, 69995.00
        ));

        dealership.addVehicle(new Vehicle(
                "W1NKM4HB6PU678901", 2023, "Mercedes-Benz",
                "GLC300", "SUV", "Gray", 10500, 42995.00
        ));

        dealership.addVehicle(new Vehicle(
                "W1K5J5JB7NA789012", 2022, "Mercedes-Benz",
                "CLA250", "car", "Red", 18000, 31995.00
        ));

        dealership.addVehicle(new Vehicle(
                "W1N4N4HB8PA890123", 2023, "Mercedes-Benz",
                "GLA250", "SUV", "Blue", 9000, 36995.00
        ));

        dealership.addVehicle(new Vehicle(
                "W1K7X8KB9NA901234", 2022, "Mercedes-Benz",
                "AMG GT", "car", "Black", 8000, 99995.00
        ));

        dealership.addVehicle(new Vehicle(
                "W1NKM4HB0PU012345", 2023, "Mercedes-Benz",
                "G550", "SUV", "White", 11000, 139995.00
        ));
        dealership.addVehicle(new Vehicle(
                "W1NKM4HB6PU678902", 2024, "Mercedes-Benz",
                 "GLE350", "SUV", "Pink", 5000, 62995.00
        ));

        dealership.addVehicle(new Vehicle(
                "W1N6G7GB5RA123456", 2024, "Mercedes-Benz",
                "G550", "SUV", "Purple", 3500, 149995.00
        ));

        return dealership;
    }
}