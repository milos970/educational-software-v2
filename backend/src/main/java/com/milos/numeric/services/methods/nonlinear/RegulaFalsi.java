package com.milos.numeric.services.methods.nonlinear;

import com.milos.numeric.services.methods.builder.NumericalMethodContext;
import org.mariuszgromada.math.mxparser.Function;

import java.util.LinkedList;
import java.util.List;

public class RegulaFalsi implements NonLinear
{
    private String equation;
    private double lowerBound;
    private double upperBound;
    private double tolerance;
    private double iterations;

    public RegulaFalsi(NumericalMethodContext builder)
    {
        this.lowerBound = builder.getLowerBound();
        this.upperBound = builder.getUpperBound();
        this.equation = builder.getFunction();
        this.tolerance = builder.getTolerance();
        this.iterations = builder.getMaxIterations();
    }

    @Override
    public List<Double[]> calculate()
    {
        List<Double[]> data = new LinkedList<>();

        NonLinear.prerekvizita();

        Function f = new Function("f(x) = " + this.equation);

        double funa = f.calculate(this.lowerBound);
        double funb = f.calculate(this.upperBound);


        if (funa * funb >= 0)
        {
            return data;
        }

        double x = this.lowerBound;

        double prev = x;

        for (int i = 0; i < this.iterations; ++i)
        {

            Double[] row = new Double[4];

            row[0] = this.lowerBound;
            row[1] = this.upperBound;

            funa = f.calculate(this.lowerBound);
            funb = f.calculate(this.upperBound);

            if (Double.isNaN(funa) || Double.isNaN(funb))
            {
               throw new ArithmeticException();
            }



            x = (this.lowerBound * funb -this.upperBound * funa) / (funb - funa);
            row[2] = x;

            double funx = f.calculate(x);

            if (Double.isNaN(funx)) {
                throw new ArithmeticException();
            }

            if (funx == 0)
            {
                break;
            }

            double error = Math.abs(x - prev);
            row[3] = error;

            if (Double.compare(error,tolerance) <= 0) {
                break;
            }

            if (funx * funa < 0)
            {
                this.upperBound = x;
            } else {
                this.lowerBound = x;
            }

            prev = x;


        }



        return data;
    }
}
