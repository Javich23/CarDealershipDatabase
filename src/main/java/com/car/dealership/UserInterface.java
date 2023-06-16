package com.car.dealership;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    static Scanner consoleInput = new Scanner(System.in);
    Dealership dealership;
    ContractDataManager contractDataManager;
    private DealershipFileManager fileManager;

    private void init() {
        fileManager = new DealershipFileManager();
        contractDataManager = new ContractDataManager();
        dealership = fileManager.getDealership();
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        System.out.printf("%-15s %-15s %-25s %-20s %15S %15S %15s %15s \n", "VIN", "YEAR", "MAKE", "MODEL", "VEHICLE TYPE", "COLOR", "ODOMETER(mi)", "PRICE");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
        for (Vehicle i : vehicles) {
            System.out.printf("%-15s %-15s %-25s %-20s %15s %15s %15d %15.2f \n",
                    i.getVin(),
                    i.getYear(),
                    i.getMake(),
                    i.getModel(),
                    i.getVehicleType(),
                    i.getColor(),
                    i.getOdometer(),
                    i.getPrice());

        }
    }

    public void display() {
        init();

        boolean exit = false;

        while (!exit) {
            System.out.println("\u001B[36m" + """

                    ===== LUXURY AUTOS DEALERSHIP =====
                                       
                    [1] Get by Price
                    [2] Get by Make and Model
                    [3] Get by Year
                    [4] Get by Color
                    [5] Get by Mileage
                    [6] Get by Vehicle Type
                    [7] Get All Vehicles
                    [8] Add Vehicle
                    [9] Remove Vehicle
                    [10] Buy or Lease Vehicle
                    [0] Exit""");

            System.out.print("\nEnter desired number in []: ");
            int choice = consoleInput.nextInt();
            consoleInput.nextLine();
            switch (choice) {
                case 1 -> processGetByPrice();
                case 2 -> processGetByMakeModel();
                case 3 -> processGetByYear();
                case 4 -> processGetByColor();
                case 5 -> processGetByMileage();
                case 6 -> processGetByVehicleType();
                case 7 -> processGetAllVehicles();
                case 8 -> processAddVehicle();
                case 9 -> processRemoveVehicle();
                case 10 -> processContractInfo();
                case 0 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public void processGetByPrice() {
        System.out.print("Enter minimum price: ");
        String input = consoleInput.nextLine();
        double min = Double.parseDouble(input);
        System.out.print("Enter your maximum price: ");
        input = consoleInput.nextLine();
        double max = Double.parseDouble(input);
        System.out.println("\n --------------------------------------------------------SELECTION BY PRICE-------------------------------------------------------------------\n");
        displayVehicles(dealership.getByPrice(min, max));
        returnHome();
    }

    public void processGetByMakeModel() {
        System.out.print("Enter make: ");
        String make = consoleInput.nextLine();
        System.out.print("Enter model: ");
        String model = consoleInput.nextLine();
        System.out.println("\n ----------------------------------------------------SELECTION BY MAKE AND MODEL--------------------------------------------------------------\n");
        displayVehicles(dealership.getByMakeModel(make, model));
        returnHome();
    }

    public void processGetByYear() {
        System.out.print("Enter minimum year: ");
        String input = consoleInput.nextLine();
        int min = LocalDate.now().getYear();
        try {
            min = Integer.parseInt(input);
        } catch (NumberFormatException ignored) {

        }
        System.out.print("Enter maximum year: ");
        input = consoleInput.nextLine();
        int max = LocalDate.now().getYear();
        try {
            max = Integer.parseInt(input);
        } catch (NumberFormatException ignored) {
        }
        System.out.println("\n --------------------------------------------------------SELECTION BY YEAR-------------------------------------------------------------------\n");
        displayVehicles(dealership.getByYear(min, max));
        returnHome();
    }

    public void processGetByColor() {
        System.out.print("Enter your color: ");
        String color = consoleInput.nextLine();
        System.out.println("\n --------------------------------------------------------SELECTION BY COLOR------------------------------------------------------------------\n");
        displayVehicles(dealership.getByColor(color));
        returnHome();
    }

    public void processGetByMileage() {
        System.out.print("Enter minimum mileage");
        String input = consoleInput.nextLine();
        double min = Double.parseDouble(input);
        System.out.print("Enter maximum mileage: ");
        input = consoleInput.nextLine();
        double max = Double.parseDouble(input);
        System.out.println("\n --------------------------------------------------------SELECTION BY ODOMETER-------------------------------------------------------------------\n");
        displayVehicles(dealership.getByMileage(min, max));
        returnHome();
    }

    public void processGetByVehicleType() {
        System.out.print("Enter desired vehicle type: ");
        String vehicleType = consoleInput.nextLine();
        System.out.println("\n ----------------------------------------------------SELECTION BY VEHICLE TYPE----------------------------------------------------------------\n");
        displayVehicles(dealership.getByType(vehicleType));
        returnHome();
    }

    public void processGetAllVehicles() {
        System.out.println("\n --------------------------------------------------------ALL VEHICLES IN INVENTORY------------------------------------------------------------\n");
        displayVehicles(dealership.getAllVehicles());
        returnHome();
    }

    public void processAddVehicle() {
        System.out.print("Enter vin of vehicle you would like to add: ");
        int vin = consoleInput.nextInt();
        System.out.print("Enter year: ");
        int year = consoleInput.nextInt();
        System.out.print("Enter make: ");
        String make = consoleInput.next();
        System.out.print("Enter model: ");
        String model = consoleInput.next();
        System.out.print("Enter vehicle type: ");
        String vehicleType = consoleInput.next();
        System.out.print("Enter color: ");
        String color = consoleInput.next();
        System.out.print("Enter mileage: ");
        int odometer = consoleInput.nextInt();
        System.out.print("Enter price: ");
        double price = consoleInput.nextDouble();
        System.out.println("\nVehicle successfully added ");
        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

        dealership.addVehicle(vehicle);
        fileManager.saveDealership(dealership);
        returnHome();
    }

    public void processRemoveVehicle() {
        Vehicle v = null;
        System.out.print("Enter vin of vehicle you would like to remove: ");
        int vin = consoleInput.nextInt();
        for (Vehicle i : dealership.getAllVehicles()) {
            if (i.getVin() == vin) {
                System.out.println("Vehicle has been removed");
                v = i;
                break;
            }
        }
        dealership.removeVehicle(v);
        fileManager.saveDealership(dealership);
        returnHome();
    }

    public void processContractInfo() {
        LocalDate date = LocalDate.now();
        System.out.print("Please enter your full name: ");
        String name = consoleInput.nextLine();
        System.out.print("Please enter your email address: ");
        String email = consoleInput.nextLine();
        System.out.print("Please enter Vin of desire vehicle: ");
        int vin = consoleInput.nextInt();
        consoleInput.nextLine();
        Vehicle vehicle = null;
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                vehicle = v;
                break;
            }
        }
        System.out.println("""
                Purchase Options Available
                                           
                [1] Buy A Vehicle
                [2] Lease A Vehicle
                [3] Return to Main Screen
                """);
        System.out.print("Enter your choice: ");
        int choice = consoleInput.nextInt();
        consoleInput.nextLine();
        boolean done = true;
        while (done) {
            switch (choice) {
                case 1 -> {
                    System.out.println("Do you wish to finance the vehicle? (Y/N)");
                    String yOrN = consoleInput.nextLine().toUpperCase();
                    boolean finance = yOrN.equals("Y");

                    if (yOrN.equals("Y")) {
                        SalesContract salesContract = new SalesContract(date, name, email, vehicle, finance);
                        contractDataManager.saveContract(salesContract);
                        dealership.removeVehicle(vehicle);
                    } else {
                        System.out.println("Total will be " + vehicle.getPrice());
                    }
                    dealership.removeVehicle(vehicle);
                    fileManager.saveDealership(dealership);
                    done = false;
                }
                case 2 -> {
                    LeaseContract leaseContract = new LeaseContract(date, name, email, vehicle);
                    double monthlyPayment = leaseContract.getMonthlyPayment();
                    System.out.println("Vehicle successfully leased for 3 years");
                    System.out.println("Monthly Payment: " + monthlyPayment);
                    contractDataManager.saveContract(leaseContract);
                    dealership.removeVehicle(vehicle);
                    fileManager.saveDealership(dealership);
                    done = false;

                }
                case 3 -> {
                    returnHome();
                    done = false;
                }
                default -> System.out.println("Invalid option try again ");
            }
        }
    }

    public void returnHome() {
        boolean done = false;
        while (!done) {
            System.out.print("\nEnter 0 to return to menu: ");
            int input = consoleInput.nextInt();
            if (input == 0) {
                System.out.println("\nReturning to menu... ");
                done = true;
                display();
            } else {
                System.out.println("\nWrong input try again");

            }
        }
    }
}



