package com.milos.numeric.services.methods.approximation;

import java.util.Arrays;
import java.util.List;

public class LeastSquares implements Approximation
{
    private final double[] x;
    private final double[] y;

    public LeastSquares(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String calculate(double[] x, double[] y) {
        double sumX = 0;
        double sumY = 0;
        double sumX2 = 0;
        double sumXY = 0;

        for (int i = 0; i < x.length; ++i) {
            sumX += x[i];
            sumY += y[i];
            sumX2 += x[i] * x[i];
            sumXY += x[i] * y[i];
        }

        List<Double> coefficients = Arrays.asList((double) x.length, sumX, sumY, sumX2, sumXY);


        return cramer(1,coefficients);
    }

    private static String cramer(int option, List<Double> coefficients) {
        double n = coefficients.get(0);
        double sumX = coefficients.get(1);
        double sumY = coefficients.get(2);
        double sumX2 = coefficients.get(3);
        double sumXY = coefficients.get(4);

        double A = n * sumX2 - sumX * sumX;
        double A1 = sumY * sumX2 - sumXY * sumX;
        double A2 = n * sumXY - sumY * sumX;

        double a = A1 / A;
        double b = A2 / A;

        String aStr = Approximation.convert(a);
        String bStr = Approximation.convert(b);

        return switch (option) {
            case 1 -> aStr + " + " + bStr + "x";
            case 2 -> aStr + " + " + bStr + "log(x)";
            default -> aStr + " + " + bStr + "x";
        };
    }
}
