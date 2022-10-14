package by.bsuir.program.logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.List;

public class Calculator {
    private final int n;
    private final int k;
    private final int inc;
    private final Signal signal;

    public Calculator(int n, double phase) {
        this.n = n;
        this.k = 3 * n / 4;
        this.inc = n / 32;
        signal = new Signal(n, phase);
    }

    private double calculateMeanSquareValueByFirstWay(int m, List<Double> values) {
        double sum = 0.0;
        for (double value: values) {
            sum += Math.pow(value, 2);
        }

        return Math.sqrt(sum / (m + 1));
    }

    private double calculateMeanSquareValueBySecondWay(int m, List<Double> values) {
        double firstSum = 0.0;
        double secondSum = 0.0;

        for (double value: values) {
            firstSum += Math.pow(value, 2);
            secondSum += value;
        }

        return Math.sqrt((firstSum / (m + 1)) - Math.pow(secondSum / (m + 1), 2));
    }

    private double calculateAmplitude(int m, List<Double> values) {
        double a = 0;
        double b = 0;
        for (int i = 0; i <= m; i++) {
            a += values.get(i) * Math.cos(2 * Math.PI * i / m);
            b += values.get(i) * Math.sin(2 * Math.PI * i / m);
        }
        a = a * 2 / m;
        b = b * 2 / m;
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

    public ObservableList<XYChart.Data<String, Double>> calculateMeanSquareValuesByFirstWay() {
        ObservableList<XYChart.Data<String, Double>> chartValues = FXCollections.observableArrayList();

        for (int i = k - 1 + inc; i < 2 * n; i += inc) {
            List<Double> values = signal.calculateSignal(i);
            chartValues.add(new XYChart.Data<>(String.valueOf(i),
                    0.707 - calculateMeanSquareValueByFirstWay(i, values)));
        }

        return chartValues;
    }

    public ObservableList<XYChart.Data<String, Double>> calculateMeanSquareValuesBySecondWay() {
        ObservableList<XYChart.Data<String, Double>> chartValues = FXCollections.observableArrayList();

        for (int i = k - 1 + inc; i < 2 * n; i += inc) {
            List<Double> values = signal.calculateSignal(i);
            chartValues.add(new XYChart.Data<>(String.valueOf(i),
                    0.707 - calculateMeanSquareValueBySecondWay(i, values)));
        }

        return chartValues;
    }

    public ObservableList<XYChart.Data<String, Double>> calculateAmplitudes() {
        ObservableList<XYChart.Data<String, Double>> chartValues = FXCollections.observableArrayList();

        for (int i = k - 1 + inc; i < 2 * n; i += inc) {
            List<Double> values = signal.calculateSignal(i);
            chartValues.add(new XYChart.Data<>(String.valueOf(i),
                    1 - calculateAmplitude(i, values)));
        }

        return chartValues;
    }
}
