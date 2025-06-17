package com.milos.numeric.services.methods.integration;

public class TrapezoidalNumericalIntegration extends NumericalIntegration {

    public TrapezoidalNumericalIntegration(String expression, double a, double b, int n) {
        super(expression, a, b, n);
    }

    @Override
    protected double boundaryTerm(double x) {
        return f.calculate(x);
    }

    @Override
    protected int weight(int i) {
        return 2;
    }

    @Override
    protected double scale(double sum) {
        return (h / 2) * sum;
    }
}
