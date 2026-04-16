package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.util.Map;

public class FuelCalculatorController {
    @FXML
    private Label lblDistance;
    @FXML
    private Label lblConsumption;
    @FXML
    private Label lblPrice;
    @FXML
    private Label lblResult;
    @FXML
    private Button btnCalculate;
    @FXML
    private TextField txtDistance;
    @FXML
    private TextField txtConsumption;
    @FXML
    private TextField txtPrice;

    private LocalizationService localizationService = new LocalizationService();
    private CalculationService calculationService = new CalculationService();
    private FuelCalculator fuelCalculator = new FuelCalculator();
    private String currentlanguage = "en";

    @FXML
    public void initialize() {
        setLanguage("en");
    }

    public void setEnglish() {
        setLanguage("en");
    }

    public void setFrench() {
        setLanguage("fr");
    }


    public void setJapanese() {
        setLanguage("ja");
    }

    public void setPersian() {
        setLanguage("fa");
    }

    private void setLanguage(String language) {
        currentlanguage = language;
        try {
            Map<String, String> strings = localizationService.loadStrings(language);

            if (strings.isEmpty()) {
                lblResult.setText("Error: No strings found for language: " + language);
                return;
            }

            lblDistance.setText(strings.getOrDefault("distance.label", "Distance (km)"));
            lblConsumption.setText(strings.getOrDefault("consumption.label", "Fuel Consumption"));
            lblPrice.setText(strings.getOrDefault("price.label", "Fuel Price"));
            btnCalculate.setText(strings.getOrDefault("calculate.button", "Calculate"));
        } catch (Exception e) {
            lblResult.setText("Error loading language: " + e.getMessage());
        }
    }

    private String formatNumber(double number, String language) {
        String formatted = String.format(java.util.Locale.US, "%.2f", number);
        if (language.equals("fa")) {
            // Convert western numerals to Persian numerals
            formatted = formatted
                    .replace("0", "۰")
                    .replace("1", "۱")
                    .replace("2", "۲")
                    .replace("3", "۳")
                    .replace("4", "۴")
                    .replace("5", "۵")
                    .replace("6", "۶")
                    .replace("7", "۷")
                    .replace("8", "۸")
                    .replace("9", "۹");
        }
        return formatted;
    }

    @FXML
    private void calculate(){
        try {
            double distance = Double.parseDouble(txtDistance.getText());
            double consumption = Double.parseDouble(txtConsumption.getText());
            double price = Double.parseDouble(txtPrice.getText());

            double totalFuel = fuelCalculator.calculateTotalFuel(distance, consumption);
            double totalCost = fuelCalculator.calculateTotalCost(totalFuel, price);

            String fuel = formatNumber(totalFuel, currentlanguage);
            String cost =formatNumber(totalCost, currentlanguage);

            String resultTemplate = localizationService.getString("result.label");
            lblResult.setText(resultTemplate.
                    replace("{0}", fuel)
                    .replace("{1}", cost));

            CalculationRecord record = new CalculationRecord(
                    distance, consumption, price,
                    totalFuel, totalCost, currentlanguage
            );

            calculationService.saveCalculation(record);

        } catch (NumberFormatException e) {
            lblResult.setText(localizationService.getString("invalid.input"));
        }
    }
}
