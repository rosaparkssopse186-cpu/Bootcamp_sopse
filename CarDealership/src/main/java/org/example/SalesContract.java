
package org.example;

public class SalesContract extends Contract {

    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean financeOption;

    public SalesContract(String date, String customerName,
                         String customerEmail, Vehicle vehicleSold,
                         boolean financeOption) {

        super(date, customerName, customerEmail, vehicleSold);

        this.salesTaxAmount = vehicleSold.getPrice() * 0.05;
        this.recordingFee = 100.00;

        if (vehicleSold.getPrice() < 10000) {
            this.processingFee = 295.00;
        } else {
            this.processingFee = 495.00;
        }

        this.financeOption = financeOption;
    }

    // Getters and setters

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinanceOption() {
        return financeOption;
    }

    public void setFinanceOption(boolean financeOption) {
        this.financeOption = financeOption;
    }

    // Calculate total sales price

    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice()
                + salesTaxAmount
                + recordingFee
                + processingFee;
    }

    // Get annual interest rate based on vehicle price

    private double getInterestRate() {

        if (getVehicleSold().getPrice() >= 10000) {
            return 0.0425;
        } else {
            return 0.0525;
        }
    }

    // Get loan term based on vehicle price

    private int getLoanTerm() {

        if (getVehicleSold().getPrice() >= 10000) {
            return 48;
        } else {
            return 24;
        }
    }

    // Calculate monthly payment

    @Override
    public double getMonthlyPayment() {

        if (!financeOption) {
            return 0.0;
        }

        double monthlyRate = getInterestRate() / 12;
        int loanTerm = getLoanTerm();
        double principal = getTotalPrice();

        return principal * monthlyRate
                / (1 - Math.pow(1 + monthlyRate, -loanTerm));
    }
}