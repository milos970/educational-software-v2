package com.milos.numeric.services.methods.approximation;

import org.decimal4j.util.DoubleRounder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public final class ApproximationMethods
{
    private static final int ROUND = 3;
    private static final DecimalFormat format = new DecimalFormat("0.#");


    private ApproximationMethods() {
        throw new AssertionError();
    }

    public static String lagrange(double[] x, double[] y) {
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
                term.append(convert(Math.abs(x[k])));
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

            polynome.append(convert(coefficient)).append(term);
        }

        return polynome.toString();
    }



    public static String newton(double[][] nodes)
    {
            String equation = "";

            for (int i = 0; i < nodes.length; ++i)
            {
                double cislo = dividedDifference(nodes, 0,i);

                if (i == 0)
                {
                    if (cislo < 0)
                    {
                        equation += convert(cislo);
                    } else {
                        equation += convert(cislo);

                    }
                    continue;
                }

                if (cislo > 0)
                {
                    equation += "+";
                }
                equation += convert(cislo);

                for (int j = 0; j < i; ++j)
                {
                    if (nodes[j][0] < 0)
                    {
                        equation += "(x + " + convert((-1 * nodes[j][0])) + ")";
                    } else {
                        equation += "(x - " + convert(nodes[j][0]) + ")";
                    }
                }
            }

            return equation;

    }


    private static double dividedDifference(double[][] nodes, int i, int j)
    {

        if (j == 0) {
            return nodes[i][1];
        } else {
            return (dividedDifference(nodes,i + 1, j - 1) - dividedDifference(nodes,i, j - 1)) / (nodes[i + j][0] - nodes[i][0]);
        }
    }


    public static String leastSquares(double[][] nodes)
    {
        List<Double> coeficients = new ArrayList<>();

        coeficients.add((double)nodes.length);

        double sum_x = 0;
        for(int i = 0; i < nodes.length; ++i)
        {
            sum_x += nodes[i][0];
        }



        coeficients.add(sum_x);


        double sum_y = 0;
        for(int i = 0; i < nodes.length; ++i)
        {
            sum_y += nodes[i][1];
        }



        coeficients.add(sum_y);

        double sum_x_2 = 0;
        for(int i = 0; i < nodes.length; ++i)
        {
            sum_x_2 += nodes[i][0] * nodes[i][0];

        }

        coeficients.add(sum_x_2);

        double sum_x_y = 0;
        for(int i = 0; i < nodes.length; ++i)
        {
            sum_x_y += nodes[i][0] * nodes[i][1];
        }

        coeficients.add(sum_x_y);




        return cramer(1,coeficients);

    }

    private static String convert(double value)
    {
        return ((int)value == value ? format.format(value) : String.valueOf(DoubleRounder.round(value, ROUND)));
    }


    private static String cramer(int option, List<Double> coeficients)
    {
        double A = coeficients.get(0) * coeficients.get(3) -  coeficients.get(1) * coeficients.get(1);
        double A1 = coeficients.get(2) * coeficients.get(3) -  coeficients.get(4) * coeficients.get(1);
        double A2 = coeficients.get(0) * coeficients.get(4) -  coeficients.get(2) * coeficients.get(1);

        switch (option)
        {
            case 1:
                return "" + convert((A1 / A)) + " +" + convert((A2 / A)) + "x";

            case 2:
                return "" +convert((A1 / A)) + " +" + convert((A2 / A)) + "log(x)";

        }

        return "" + convert((A1 / A)) + " +" + convert((A2 / A)) + "x";

    }

}
