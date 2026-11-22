package org.example;

public class LeaseContract extends Contract {

    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName,
                         String customerEmail, Vehicle vehicleSold) {

        super(date, customerName, customerEmail, vehicleSold);

        // Calculate expected ending value at 50% of vehicle price
        this.expectedEndingValue = vehicleSold.getPrice() * 0.50;

        // Calculate lease fee at 7% of vehicle price
        this.leaseFee = vehicleSold.getPrice() * 0.07;
    }

    // Getters and setters

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    // Calculate total lease price

    @Override
    public double getTotalPrice() {
        return expectedEndingValue + leaseFee;
    }

    // Calculate monthly lease payment

    @Override
    public double getMonthlyPayment() {

        double monthlyRate = 0.04 / 12;
        int loanTerm = 36;

        double principal = getTotalPrice();

        return principal * monthlyRate
                / (1 - Math.pow(1 + monthlyRate, -loanTerm));
    }
}
