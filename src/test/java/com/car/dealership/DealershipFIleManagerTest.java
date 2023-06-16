package com.car.dealership;

import org.junit.jupiter.api.Test;

class DealershipFIleManagerTest {

    @Test
    public void dealershipFileManager_dealershipInitialize() {
        DealershipFileManager d = new DealershipFileManager();

        Dealership dealership = d.getDealership();

    }
}