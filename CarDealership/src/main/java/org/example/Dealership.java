
package org.example;

import java.util.ArrayList;
import java.util.List;

public class Dealership {

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {

        this.name = name;
        this.address = address;
        this.phone = phone;

        // Initialize the inventory
        this.inventory = new ArrayList<>();
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Add a vehicle to inventory

    public void addVehicle(Vehicle vehicle) {
        if (vehicle != null && getVehicleByVin(vehicle.getVin()) == null) {
            inventory.add(vehicle);
        }
    }

    // Return all vehicles

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(inventory);
    }

    // Search by price range

    public List<Vehicle> getVehiclesByPrice(double minPrice, double maxPrice) {

        List<Vehicle> results = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice() >= minPrice &&
                    vehicle.getPrice() <= maxPrice) {
                results.add(vehicle);
            }
        }

        return results;
    }

    // Search by make or model

    public List<Vehicle> getVehiclesByMakeModel(String search) {

        List<Vehicle> results = new ArrayList<>();

        for (Vehicle vehicle : inventory) {

            if (vehicle.getMake().equalsIgnoreCase(search) ||
                    vehicle.getModel().equalsIgnoreCase(search)) {
                results.add(vehicle);
            }
        }

        return results;
    }

    // Search by year range

    public List<Vehicle> getVehiclesByYear(int minYear, int maxYear) {

        List<Vehicle> results = new ArrayList<>();

        for (Vehicle vehicle : inventory) {

            if (vehicle.getYear() >= minYear &&
                    vehicle.getYear() <= maxYear) {
                results.add(vehicle);
            }
        }

        return results;
    }

    // Search by color

    public List<Vehicle> getVehiclesByColor(String color) {

        List<Vehicle> results = new ArrayList<>();

        for (Vehicle vehicle : inventory) {

            if (vehicle.getColor().equalsIgnoreCase(color)) {
                results.add(vehicle);
            }
        }

        return results;
    }

    // Search by mileage range

    public List<Vehicle> getVehiclesByMileage(int minMileage, int maxMileage) {

        List<Vehicle> results = new ArrayList<>();

        for (Vehicle vehicle : inventory) {

            if (vehicle.getOdometer() >= minMileage &&
                    vehicle.getOdometer() <= maxMileage) {
                results.add(vehicle);
            }
        }

        return results;
    }

    // Search by vehicle type

    public List<Vehicle> getVehiclesByType(String type) {

        List<Vehicle> results = new ArrayList<>();

        for (Vehicle vehicle : inventory) {

            if (vehicle.getVehicleType().equalsIgnoreCase(type)) {
                results.add(vehicle);
            }
        }

        return results;
    }

    // Find a vehicle using its VIN

    public Vehicle getVehicleByVin(String vin) {

        for (Vehicle vehicle : inventory) {

            if (vehicle.getVin().equalsIgnoreCase(vin)) {
                return vehicle;
            }
        }

        return null;
    }

    // Remove vehicle

    public void removeVehicle(Vehicle vehicle) {

        if (vehicle != null) {
            inventory.remove(vehicle);
        }
    }
}
