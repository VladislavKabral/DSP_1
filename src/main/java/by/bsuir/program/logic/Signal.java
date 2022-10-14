package by.bsuir.program.logic;

import java.util.ArrayList;
import java.util.List;

public class Signal {
    private final int n;
    private final double phase;

    public Signal(int n, double phase) {
        this.n = n;
        this.phase = phase;
    }

    public List<Double> calculateSignal(int m) {
        List<Double> values = new ArrayList<>();

        for (int i = 0; i <= m; i++) {
            double value = Math.sin(2 * Math.PI * i / n + phase);
            values.add(value);
        }

        return values;
    }
}
