package org.example;

public class FuelCalculator {

    public double calculateTotalFuel(double distance, double consumption) {
        return (consumption / 100) * distance;
    }

    public double calculateTotalCost(double totalFuel, double price) {
        return totalFuel * price;
    }
}