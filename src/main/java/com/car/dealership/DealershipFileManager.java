package com.car.dealership;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class DealershipFileManager {
    public Dealership getDealership() {
        Dealership dealership = null;
        try {
            FileReader fileReader = new FileReader("inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input;

            input = bufferedReader.readLine();
            String[] details;
            details = input.split("\\|");
            String name = details[0];
            String address = details[1];
            String phone = details[2];
            dealership = new Dealership(name, address, phone);

            while ((input = bufferedReader.readLine()) != null) {
                if (!input.isEmpty()) {
                    details = input.split("\\|");
                    int vin = Integer.parseInt(details[0]);
                    int year = Integer.parseInt(details[1]);
                    String make = details[2];
                    String model = details[3];
                    String vehicleType = details[4];
                    String color = details[5];
                    int odometer = Integer.parseInt(details[6]);
                    double price = Double.parseDouble(details[7]);
                    Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color,odometer,price);
                    dealership.addVehicle(vehicle);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("FILE NOT AVAILABLE ");
            System.exit(0);
        }
        return dealership;
    }
    public void saveDealership(Dealership dealership) {
        String d = dealership.getAddress();
        String p = dealership.getName();
        String a = dealership.getPhone();
        String dealer = String.format("%s|%s|%s\n", p, d, a);

        try {
            FileWriter fileWriter = new FileWriter("inventory.csv");
            fileWriter.write(dealer);
            for (Vehicle v: dealership.getAllVehicles()) {
                String vehicle = String.format("%d|%d|%s|%s|%s|%s|%d|%.2f\n",
                        v.getVin(),
                        v.getYear(),
                        v.getMake(),
                        v.getModel(),
                        v.getVehicleType(),
                        v.getColor(),
                        v.getOdometer(),
                        v.getPrice());
                fileWriter.write(vehicle);
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

