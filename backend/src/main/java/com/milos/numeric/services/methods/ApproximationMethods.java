package com.milos.numeric.services.methods;

import org.decimal4j.util.DoubleRounder;
import org.hibernate.AssertionFailure;

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
    public static String lagrange(double[] nodes)
    {


        String equation = "";
        boolean first = true;
        for (int i = 0; i < nodes.length; i+=2)
        {
            String part = "";
            double menovatel = 1;
            for (int k = 0; k < nodes.length; k+=2)
            {

                if (i == k)
                {
                    continue;
                }




                if (nodes[k] > 0)
                {
                    part += "(x - " + convert(nodes[k]) + ")";
                } else
                {
                    part += "(x + " + convert(-1 * nodes[k]) + ")";
                }


                menovatel *= (nodes[i] - nodes[k]);

            }

            menovatel = 1/ menovatel;

            if (first)
            {

                part = convert(nodes[i + 1] * menovatel)  + part;
                equation += part;
                first = false;
            } else
            {
                if (nodes[i + 1] * menovatel > 0)
                {
                    part = "+" + convert(nodes[i + 1] * menovatel) + part;
                } else
                {
                    part = convert(nodes[i + 1] * menovatel) + part;
                }

                equation += part;
            }


        }


        return equation;




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
