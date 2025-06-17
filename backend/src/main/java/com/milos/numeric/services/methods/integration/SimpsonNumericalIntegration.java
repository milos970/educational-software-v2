package com.milos.numeric.services.methods.integration;

public class SimpsonNumericalIntegration extends NumericalIntegration {

    public SimpsonNumericalIntegration(String expression, double a, double b, int n) {
        super(expression, a, b, n);

        if (n % 2 != 0) {
            throw new IllegalArgumentException("Simpson method requires even n");
        }
    }

    @Override
    protected double boundaryTerm(double x) {
        return f.calculate(x);
    }

    @Override
    protected int weight(int i) {
        return (i % 2 == 0) ? 2 : 4;
    }

    @Override
    protected double scale(double sum) {
        return (h / 3) * sum;
    }
}
