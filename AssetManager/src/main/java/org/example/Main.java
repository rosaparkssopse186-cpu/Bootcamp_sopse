
package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Asset> myAssets = new ArrayList<>();

        // Create houses
        House myHouse = new House(
                "My house",
                "2020-05-15",
                new BigDecimal("350000"),
                "Charlotte, NC",
                1,
                2500,
                8000
        );

        House vacationHome = new House(
                "My vacation home",
                "2022-06-10",
                new BigDecimal("275000"),
                "Myrtle Beach, SC",
                2,
                1800,
                6000
        );

        // Create vehicles
        Vehicle myCar = new Vehicle(
                "My Mercedes",
                "2024-03-20",
                new BigDecimal("85000"),
                "Mercedes-Benz G-Wagon",
                2024,
                25000
        );

        Vehicle tomTruck = new Vehicle(
                "Tom's truck",
                "2019-08-12",
                new BigDecimal("45000"),
                "Toyota Tacoma",
                2019,
                120000
        );

        // Add assets to the ArrayList
        myAssets.add(myHouse);
        myAssets.add(vacationHome);
        myAssets.add(myCar);
        myAssets.add(tomTruck);

