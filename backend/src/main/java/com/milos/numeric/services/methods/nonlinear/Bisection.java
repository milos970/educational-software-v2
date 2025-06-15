package com.milos.numeric.services.methods.nonlinear;

import com.milos.numeric.services.methods.builder.NumericalMethodContext;
import org.mariuszgromada.math.mxparser.Function;

import java.util.LinkedList;
import java.util.List;

public class Bisection implements NonLinear
{
    private String equation;
    private double lowerBound;
    private double upperBound;
    private double tolerance;
    private double iterations;

    public Bisection(NumericalMethodContext builder)
    {
        this.lowerBound = builder.getLowerBound();
        this.upperBound = builder.getUpperBound();
        this.equation = builder.getFunction();
        this.tolerance = builder.getTolerance();
        this.iterations = builder.getMaxIterations();
    }

    @Override
    public List<Double[]> calculate() {
        NonLinear.prerekvizita();

        List<Double[]> data = new LinkedList<>();

        Function f = new Function("f(x) = " + this.equation);

        double funa = f.calculate(this.lowerBound);
        double funb = f.calculate(this.upperBound);


        if (funa * funb > 0)
        {
            return data;
        }


        double x = 0;

        for (int i = 0; i < this.iterations; ++i)
        {

            Double[] row = new Double[4];

            x = (this.lowerBound + this.upperBound)/ 2;

            row[0] = this.lowerBound;
            row[1] = this.upperBound;
            row[2] = x;

            double func = f.calculate(x);

            if (func == 0)
            {
                break;
            }

            if (Double.compare((Math.abs(this.upperBound - this.lowerBound) / 2),tolerance) <= 0 || Math.abs(func) < tolerance)
            {
                break;
            }

            if (funa * func < 0)
            {
                this.upperBound = x;
            } else
            {
                this.lowerBound = x;
            }

        }

        return data;
    }

}
