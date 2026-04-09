package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculationTest {

    @Test
    void testTotalFuelCalculation() {
        double distance = 180;
        double consumption = 6.5;
        double expectedFuel = (consumption / 100) * distance;
        assertEquals(11.7, expectedFuel, 0.001);
    }

    @Test
    void testTotalCostCalculation() {
        double totalFuel = 11.7;
        double price = 2.05;
        double expectedCost = totalFuel * price;
        assertEquals(23.985, expectedCost, 0.001);
    }

    @Test
    void testZeroDistance() {
        double distance = 0;
        double consumption = 6.5;
        double totalFuel = (consumption / 100) * distance;
        assertEquals(0.0, totalFuel, 0.001);
    }

    @Test
    void testZeroConsumption() {
        double consumption = 0;
        double distance = 180;
        double totalFuel = (consumption / 100) * distance;
        assertEquals(0.0, totalFuel, 0.001);
    }

    @Test
    void testHighDistance() {
        double distance = 1000;
        double consumption = 8.0;
        double totalFuel = (consumption / 100) * distance;
        assertEquals(80.0, totalFuel, 0.001);
    }
}