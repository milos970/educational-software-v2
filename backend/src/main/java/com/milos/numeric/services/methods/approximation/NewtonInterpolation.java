package com.milos.numeric.services.methods.approximation;

public class NewtonInterpolation implements Approximation
{
    private final double[] x;
    private final double[] y;

    public NewtonInterpolation(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String calculate(double[] x, double[] y) {
        StringBuilder polynome = new StringBuilder();

        double[] coeffs = dividedDifferencesTable(x, y);

        for (int i = 0; i < x.length; ++i)
        {
            double coefficient = coeffs[i];

            if (coefficient == 0) continue;

            if (polynome.length() > 0 && coefficient > 0) {
                polynome.append(" + ");
            } else if (coefficient < 0) {
                polynome.append(" - ");
                coefficient = -coefficient;
            }

            polynome.append(Approximation.convert(coefficient));

            for (int j = 0; j < i; ++j) {
                polynome.append("(x ");
                polynome.append(x[j] < 0 ? "+ " : "- ");
                polynome.append(Approximation.convert(Math.abs(x[j])));
                polynome.append(")");
            }
        }

        return polynome.toString();
    }

    private static double[] dividedDifferencesTable(double[] x, double[] y) {
        int n = x.length;
        double[][] table = new double[n][n];

        for (int i = 0; i < n; ++i) {
            table[i][0] = y[i];
        }

        for (int j = 1; j < n; ++j) {
            for (int i = 0; i < n - j; ++i) {
                table[i][j] = (table[i + 1][j - 1] - table[i][j - 1]) / (x[i + j] - x[i]);
            }
        }

        double[] coefficients = new double[n];
        for (int i = 0; i < n; ++i) {
            coefficients[i] = table[0][i];
        }

        return coefficients;
    }
}
