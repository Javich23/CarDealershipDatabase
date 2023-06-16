package com.car.dealership;

import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {
    public void saveContract(Contract contract){
        try(FileWriter fileWriter = new FileWriter("contracts.csv", true)) {
            fileWriter.write(contract.getPersistenceString());
            fileWriter.close();
            System.out.println("Contract Saved ");
        } catch (IOException e) {
            System.out.println("Unable to save contract ");
        }
    }
}
