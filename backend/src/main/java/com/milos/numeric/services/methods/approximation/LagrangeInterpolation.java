package com.milos.numeric.services.methods.approximation;

public class LagrangeInterpolation implements Approximation
{
    private final double[] x;
    private final double[] y;

    public LagrangeInterpolation(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String calculate(double[] x, double[] y) {
        StringBuilder polynome = new StringBuilder();

        for (int i = 0; i < x.length; ++i) {
            double denominator = 1.0;
            StringBuilder term = new StringBuilder();

            for (int k = 0; k < x.length; ++k) {
                if (i == k) continue;

                double diff = x[i] - x[k];
                denominator *= diff;

                term.append("(x ");
                term.append(x[k] >= 0 ? "- " : "+ ");
                term.append(Approximation.convert(Math.abs(x[k])));
                term.append(")");
            }

            double coefficient = y[i] / denominator;

            if (coefficient == 0) continue;

            if (coefficient > 0 && polynome.length() > 0) {
                polynome.append(" + ");
            } else if (coefficient < 0) {
                polynome.append(" - ");
                coefficient = -coefficient;
            }

            polynome.append(Approximation.convert(coefficient)).append(term);
        }

        return polynome.toString();
    }
}
