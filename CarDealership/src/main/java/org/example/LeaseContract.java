
package org.example;

public class LeaseContract extends Contract {

    private double expectedEndingValue;
    private double leaseFee;

    // Constructor to create a lease contract
    public LeaseContract(String date, String customerName,
                         String customerEmail, Vehicle vehicleSold) {

        // Call the parent Contract constructor
        super(date, customerName, customerEmail, vehicleSold);

        // Calculate expected ending value at 50% of vehicle price
        this.expectedEndingValue = vehicleSold.getPrice() * 0.50;

        // Calculate lease fee at 7% of vehicle price
        this.leaseFee = vehicleSold.getPrice() * 0.07;
    }

    // Get the expected ending value
    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    // Set the expected ending value
    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    // Get the lease fee
    public double getLeaseFee() {
        return leaseFee;
    }

    // Set the lease fee
    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    // Calculate the total lease price
    @Override
    public double getTotalPrice() {
        return expectedEndingValue + leaseFee;
    }

    // Calculate the monthly lease payment
    @Override
    public double getMonthlyPayment() {

        // Convert the annual interest rate to a monthly rate
        double monthlyRate = 0.04 / 12;

        // Set the lease term to 36 months
        int loanTerm = 36;

        // Get the total lease price
        double principal = getTotalPrice();

        // Calculate the monthly payment using the payment formula
        return principal * monthlyRate
                / (1 - Math.pow(1 + monthlyRate, -loanTerm));
    }
}