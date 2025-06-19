package com.milos.numeric.services.methods.approximation;

import org.decimal4j.util.DoubleRounder;

import java.text.DecimalFormat;

public interface Approximation
{
    static final int ROUND = 3;
    static final DecimalFormat format = new DecimalFormat("0.#");

    public String calculate(double[] x, double[] y);

     static String convert(double value)
    {
        return ((int)value == value ? format.format(value) : String.valueOf(DoubleRounder.round(value, ROUND)));
    }
}
