package com.milos.numeric.services.methods.integration;

import org.mariuszgromada.math.mxparser.Function;

public final class IntegrationMethods
{
    private IntegrationMethods() {
        throw new AssertionError();
    }

    public static double trapezoidal(String expression, double a, double b, int n)
    {
        Methods.prerekvizita();
        final double h = (b - a) / n;


            double sum = 0;
            double part = 1 * a;

            Function f = new Function("f(x) = " + expression);

            for (int i = 0; i <= n; ++i)
            {

                if (i == 0)
                {
                    double fx = f.calculate(a);
                    sum += fx;
                    continue;
                }

                if (i == n)
                {
                    double fx = f.calculate(b);
                    sum += fx;
                    continue;
                }

                part += 1 * h;

                double fx = 2 * f.calculate(part);
                sum = sum + fx;


            }


         return sum * h/2;
    }


    public static double simpson(String expression, double a, double b, int n)
    {
        Methods.prerekvizita();
        final double h = (b - a) / n;

        Function f = new Function("f(x) = " + expression);


        double sum = 0;
        double part = 1 * a;

        for (int i = 0; i <= n; ++i)
        {

            if (i == 0)
            {
                double fx = f.calculate(a);
                sum += fx;
                continue;
            }

            if (i == n)
            {
                double fx = f.calculate(b);
                sum += fx;
                continue;
            }

            part += 1 * h;

            if (i % 2 != 0)
            {
                sum = sum + (4 * f.calculate(part));
            }

            if (i % 2 == 0)
            {
                sum = sum + (2 * f.calculate(part));
            }

        }

        return sum * h/3;
    }
}
