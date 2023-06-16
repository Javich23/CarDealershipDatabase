package com.car.dealership;

import java.time.LocalDate;

public class SalesContract extends Contract {
    private final double salesTax = 0.05;
    private final double recordingFee = 100.00;
    private double processingFee = 495;
    private boolean finance ;

    public SalesContract(LocalDate date, String name, String email, Vehicle vehicleSold, boolean finance) {
        super(date, name, email, vehicleSold);
        this.finance = finance;
    }

    public double getSalesTax() {
        return getVehicleSold().getPrice() * salesTax;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice = getVehicleSold().getPrice();
        return vehiclePrice + getSalesTax() + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (finance) {
            double vehiclePrice = getVehicleSold().getPrice();
            double interestRate = 0.0425;
            double monthlyInterestRate = interestRate / 12;
            int loanTerm = 48;
            return (vehiclePrice * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -loanTerm));
        } else {
            return 0.0;
        }
    }
    @Override
    public String getPersistenceString() {
        return "SALE" + "|" +
                getDate() + "|" +
                getName() + "|" +
                getEmail() + "|" +
                getVehicleSold().getVin() + "|" +
                getVehicleSold().getYear() + "|" +
                getVehicleSold().getMake() + "|" +
                getVehicleSold().getModel() + "|" +
                getVehicleSold().getVehicleType() + "|" +
                getVehicleSold().getColor() + "|" +
                getVehicleSold().getOdometer() + "|" +
                getVehicleSold().getPrice() + "|" +
                getSalesTax() + "|" +
                getRecordingFee() + "|" +
                getProcessingFee() + "|" +
                getTotalPrice() + "|" +
                (isFinance() ? "YES" : "NO") + "|" +
                getMonthlyPayment() + "|\n" ;
    }
}
