package com.milos.numeric.services.methods.builder;

import java.util.List;

public class NumericalMethodContext {
    private final String function;
    private final String derivative;
    private final List<Double> xValues;
    private final List<Double> yValues;
    private final double lowerBound;
    private final double upperBound;
    private final double initialGuess;
    private final double tolerance;
    private final int maxIterations;


    private NumericalMethodContext(Builder builder) {
        this.function = builder.function;
        this.derivative = builder.derivative;
        this.xValues = builder.xValues;
        this.yValues = builder.yValues;
        this.lowerBound = builder.lowerBound;
        this.upperBound = builder.upperBound;
        this.initialGuess = builder.initialGuess;
        this.tolerance = builder.tolerance;
        this.maxIterations = builder.maxIterations;
    }

    public String getFunction() { return function; }
    public String getDerivative() { return derivative; }
    public List<Double> getXValues() { return xValues; }
    public List<Double> getYValues() { return yValues; }
    public double getLowerBound() { return lowerBound; }
    public double getUpperBound() { return upperBound; }
    public double getInitialGuess() { return initialGuess; }
    public double getTolerance() { return tolerance; }
    public int getMaxIterations() { return maxIterations; }

    public static class Builder {
        private String function;
        private String derivative;
        private List<Double> xValues;
        private List<Double> yValues;
        private double lowerBound = Double.NaN;
        private double upperBound = Double.NaN;
        private double initialGuess = Double.NaN;
        private double tolerance = 1e-6;
        private int maxIterations = 100;

        public Builder function(String function) {
            this.function = function;
            return this;
        }

        public Builder derivative(String derivative) {
            this.derivative = derivative;
            return this;
        }

        public Builder xValues(List<Double> xValues) {
            this.xValues = xValues;
            return this;
        }

        public Builder yValues(List<Double> yValues) {
            this.yValues = yValues;
            return this;
        }

        public Builder lowerBound(double lowerBound) {
            this.lowerBound = lowerBound;
            return this;
        }

        public Builder upperBound(double upperBound) {
            this.upperBound = upperBound;
            return this;
        }

        public Builder initialGuess(double guess) {
            this.initialGuess = guess;
            return this;
        }

        public Builder tolerance(double tolerance) {
            this.tolerance = tolerance;
            return this;
        }

        public Builder maxIterations(int maxIterations) {
            this.maxIterations = maxIterations;
            return this;
        }

        public NumericalMethodContext build() {
            return new NumericalMethodContext(this);
        }
    }
}

