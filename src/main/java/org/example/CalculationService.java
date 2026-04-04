package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CalculationService {

    public void saveCalculation(CalculationRecord record) {
        String sql = "INSERT INTO calculation_records " + "(distance, consumption, price, total_fuel, total_cost, language)"  + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, record.getDistance());
            ps.setDouble(2, record.getConsumption());
            ps.setDouble(3, record.getPrice());
            ps.setDouble(4, record.getTotalCost());
            ps.setDouble(5, record.getTotalCost());
            ps.setString(6, record.getLanguage());
            ps.executeUpdate();

            System.out.println("Calculation saved to database");

        } catch (SQLException e) {
            System.err.println("Failed to save calculation: " + e.getMessage());
        }
    }
}
