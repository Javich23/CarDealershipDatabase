package com.car.dealership;

import java.util.*;

public class Dealership {
    private final String name;
    private final String address;
    private final String phone;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    private final ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }
    public List<Vehicle> getByPrice(double min, double max) {
        List<Vehicle> values = new ArrayList<Vehicle>();
        for(Vehicle i : inventory) {
            if(i.getPrice() >= min && i.getPrice() <= max) {
                values.add(i);
            }
        }
        Collections.sort(values, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return (int) (o1.getPrice() - o2.getPrice());
            }
        });
        return values;
    }
    public List<Vehicle> getByMakeModel(String make, String model) {
        List<Vehicle> values = new ArrayList<Vehicle>();
        for(Vehicle i : inventory) {
            if(i.getMake().equalsIgnoreCase(make) || i.getModel().equalsIgnoreCase(model)) {
                values.add(i);
            }
        }
        return values;
    }

    public List<Vehicle> getByYear(int min , int max) {
        List<Vehicle> values = new ArrayList<Vehicle>();
        for(Vehicle i : inventory) {
            if(i.getYear() >= min && i.getYear() <= max) {
                values.add(i);
            }
        }
        return values;
    }
    public List<Vehicle> getByColor(String color) {
        List<Vehicle> values = new ArrayList<Vehicle>();
        for(Vehicle i : inventory) {
            if(i.getColor().equalsIgnoreCase(color)) {
                values.add(i);
            }
        }
        return values;
    }
    public List<Vehicle> getByMileage(double min, double max) {
        List<Vehicle> values = new ArrayList<Vehicle>();
        for(Vehicle i : inventory) {
            if(i.getOdometer() >= min && i.getOdometer() <= max) {
                values.add(i);
            }
        }
        return values;    }
    public List<Vehicle> getByType(String vehicleType) {
        List<Vehicle> values = new ArrayList<Vehicle>();
        for(Vehicle i : inventory) {
            if(i.getVehicleType().equalsIgnoreCase(vehicleType)) {
                values.add(i);
            }
        }
        return values;
    }
    public  List<Vehicle> getAllVehicles() {
        return inventory;
    }
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }
    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }
}
