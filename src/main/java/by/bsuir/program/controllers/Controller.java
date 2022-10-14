package by.bsuir.program.controllers;

import by.bsuir.program.logic.Calculator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class Controller {

    private final ObservableList<Integer> variantsOfN = FXCollections.observableArrayList(64, 128, 256, 512,
            1024, 2048, 4096);

    private final ObservableList<String> phases = FXCollections.observableArrayList("0", "PI / 32");

    @FXML
    private LineChart<String, Double> lineChart;

    @FXML
    private ComboBox<Integer> nComboBox;

    @FXML
    private ComboBox<String> phaseComboBox;

    @FXML
    private Button runButton;

    @FXML
    void initialize() {
        nComboBox.setItems(variantsOfN);
        phaseComboBox.setItems(phases);
        nComboBox.setValue(64);
        phaseComboBox.setValue("0");

        XYChart.Series<String, Double> firstWaySeries = new XYChart.Series<>();
        firstWaySeries.setName("First way");
        XYChart.Series<String, Double> secondWaySeries = new XYChart.Series<>();
        secondWaySeries.setName("Second way");
        XYChart.Series<String, Double> amplitudeSeries = new XYChart.Series<>();
        amplitudeSeries.setName("Amplitude");

        runButton.setOnAction(event -> {
            int n = nComboBox.getValue();
            String phaseInput = phaseComboBox.getValue();

            double phase = 0.0;
            switch (phaseInput) {
                case "0" -> phase = 0.0d;
                case "PI / 32" -> phase = Math.PI / 32;
            }

            Calculator calculator = new Calculator(n, phase);
            firstWaySeries.setData(calculator.calculateMeanSquareValuesByFirstWay());
            secondWaySeries.setData(calculator.calculateMeanSquareValuesBySecondWay());
            amplitudeSeries.setData(calculator.calculateAmplitudes());
            lineChart.getData().add(firstWaySeries);
            lineChart.getData().add(secondWaySeries);
            lineChart.getData().add(amplitudeSeries);
        });
    }
}