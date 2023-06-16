package com.car.dealership;

import java.time.LocalDate;

public class LeaseContract extends Contract {
    private double expectedEndValue;
    private double leaseFee;

    public LeaseContract(LocalDate date, String name, String email, Vehicle vehicleSold) {
        super(date, name, email, vehicleSold);
    }

    public double getExpectedEndValue() {
        return getVehicleSold().getPrice() * 0.5;
    }

    public void setExpectedEndValue(double expectedEndValue) {
        this.expectedEndValue = expectedEndValue;
    }

    public double getLeaseFee() {
        return getVehicleSold().getPrice() * 0.07;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice = getVehicleSold().getPrice();
        return vehiclePrice + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double vehiclePrice = getVehicleSold().getPrice();
        int loanTerm = 36;
        double interestRate = 0.04;
        double monthlyInterestRate = interestRate / 12;
        double loanAmount = vehiclePrice - expectedEndValue;
        return (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -loanTerm));
    }

    @Override
    public String getPersistenceString() {
        return "LEASE" + "|" +
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
                getExpectedEndValue() + "|" +
                getLeaseFee() + "|" +
                getTotalPrice() + "|" +
                getMonthlyPayment() + "|\n";
    }
}
