package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LocalizationService {

    private Map<String, String> cachedStrings = new HashMap<>();
    private String currentLanguage = "";

    public Map<String, String> loadStrings(String language) {
        if (language.equals(currentLanguage) && !cachedStrings.isEmpty()) {
            return cachedStrings;
        }

        cachedStrings.clear();
        currentLanguage = language;

        String sql = "SELECT `key`, value FROM localization_strings WHERE language = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, language);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cachedStrings.put(rs.getString("key"), rs.getString("value"));
            }

            System.out.println("Loaded " + cachedStrings.size() + " strings for language: " + language);

        } catch (SQLException e) {
            System.out.println("Failed to load strings: " + e.getMessage());
        }

        return cachedStrings;
    }

    public String getString(String key) {
        return cachedStrings.getOrDefault(key, "Missing: " + key);
    }

    public Map<String, String> getAllKeys() {
        return cachedStrings;
    }
}
