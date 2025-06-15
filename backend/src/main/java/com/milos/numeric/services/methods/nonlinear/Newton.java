package com.milos.numeric.services.methods.nonlinear;

import com.milos.numeric.services.methods.builder.NumericalMethodContext;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import java.util.LinkedList;
import java.util.List;

public class Newton implements NonLinear
{
    private String equation;
    private double initialGuess;
    private double tolerance;
    private double iterations;

    public Newton(NumericalMethodContext builder) {
        this.initialGuess = builder.getInitialGuess();
        this.equation = builder.getFunction();
        this.tolerance = builder.getTolerance();
        this.iterations = builder.getMaxIterations();
    }

    @Override
    public List<Double[]> calculate() {
        NonLinear.prerekvizita();

        Function f = new Function("f(x) = " + this.equation);

        List<Double[]> data = new LinkedList<>();

        double current = this.initialGuess;

        for (int i = 0; i < this.iterations; ++i)
        {
            Double[] row = new Double[2];
            row[0] = current;
            double fx = f.calculate(current);

            if (Double.isNaN(fx))
            {
                throw new ArithmeticException();
            }


            Argument x = new Argument("x = " + current);
            Expression e = new Expression("der(" + this.equation + ",x)", x);
            double defx = e.calculate();

            if (Double.isNaN(defx))
            {
                throw new ArithmeticException();
            }

            double next = current - (fx / defx);
            double error = Math.abs(next - current);
            row[1] = error;

            data.add(row);

            if (Double.compare(error, tolerance) <= 0)
            {
                break;
            }

            current = next;
        }



        return data;
    }
}
