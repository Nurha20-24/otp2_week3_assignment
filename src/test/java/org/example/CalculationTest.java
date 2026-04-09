package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculationTest {

    private FuelCalculator calculator;
    private CalculationRecord calculationRecord;

    @BeforeEach
    void setUp() {
        calculator = new FuelCalculator();
        calculationRecord = new CalculationRecord(180, 6.5, 2.05, 11.7, 23.985, "en");
    }

    // FuelCalculator tests
    @Test
    void testTotalFuel() {
        assertEquals(11.7, calculator.calculateTotalFuel(180, 6.5), 0.001);
    }

    @Test
    void testTotalCost() {
        assertEquals(23.985, calculator.calculateTotalCost(11.7, 2.05), 0.001);
    }

    @Test
    void testZeroDistance() {
        assertEquals(0.0, calculator.calculateTotalFuel(0, 6.5), 0.001);
    }

    @Test
    void testZeroConsumption() {
        assertEquals(0.0, calculator.calculateTotalFuel(180, 0), 0.001);
    }

    @Test
    void testHighDistance() {
        assertEquals(80.0, calculator.calculateTotalFuel(1000, 8.0), 0.001);
    }

    @Test
    void testNegativeDistance() {
        assertEquals(-11.7, calculator.calculateTotalFuel(-180, 6.5), 0.001);
    }

    // CalculationRecord tests
    @Test
    void testRecordGetDistance() {
        assertEquals(180, calculationRecord.getDistance(), 0.001);
    }

    @Test
    void testRecordGetConsumption() {
        assertEquals(6.5, calculationRecord.getConsumption(), 0.001);
    }

    @Test
    void testRecordGetPrice() {
        assertEquals(2.05, calculationRecord.getPrice(), 0.001);
    }

    @Test
    void testRecordGetTotalFuel() {
        assertEquals(11.7, calculationRecord.getTotalFuel(), 0.001);
    }

    @Test
    void testRecordGetTotalCost() {
        assertEquals(23.985, calculationRecord.getTotalCost(), 0.001);
    }

    @Test
    void testRecordGetLanguage() {
        assertEquals("en", calculationRecord.getLanguage());
    }
}