package org.example;

public class SalesContract extends Contract {

    // Sales contract information
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean financeOption;

    // Constructor
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

    // Getters and Setters

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

    // Calculate total price
    @Override
    public double getTotalPrice() {

        return getVehicleSold().getPrice()
                + salesTaxAmount
                + recordingFee
                + processingFee;
    }

    // Calculate monthly payment
    @Override
    public double getMonthlyPayment() {

        if (!financeOption) {
            return 0.0;
        }

        double interestRate;
        int loanTerm;

        if (getVehicleSold().getPrice() >= 10000) {
            interestRate = 0.0425;
            loanTerm = 48;
        } else {
            interestRate = 0.0525;
            loanTerm = 24;
        }

        double monthlyRate = interestRate / 12;
        double principal = getTotalPrice();

        return principal * monthlyRate
                / (1 - Math.pow(1 + monthlyRate, -loanTerm));
    }
}