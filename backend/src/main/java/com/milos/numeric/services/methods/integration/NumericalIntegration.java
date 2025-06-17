package com.milos.numeric.services.methods.integration;

import org.mariuszgromada.math.mxparser.Function;
import org.mariuszgromada.math.mxparser.License;
import org.mariuszgromada.math.mxparser.mXparser;

public abstract class NumericalIntegration {

    protected final Function f;
    protected final double a, b;
    protected final int n;
    protected final double h;

    public NumericalIntegration(String expression, double a, double b, int n) {
        prerekvizita();
        this.f = new Function("f(x) = " + expression);
        this.a = a;
        this.b = b;
        this.n = n;
        this.h = (b - a) / n;
    }

    private static void prerekvizita() {
        boolean isCallSuccessful = License.iConfirmNonCommercialUse("John Doe");

        /* Verification if use type has been already confirmed */
        boolean isConfirmed = License.checkIfUseTypeConfirmed();

        /* Checking use type confirmation message */
        String message = License.getUseTypeConfirmationMessage();

        /* ----------- */
        mXparser.consolePrintln("isCallSuccessful = " + isCallSuccessful);
        mXparser.consolePrintln("isConfirmed = " + isConfirmed);
        mXparser.consolePrintln("message = " + message);
    }

    public final double integrate() {
        double sum = boundaryTerm(a) + boundaryTerm(b);

        double x = a;

        for (int i = 1; i < n; i++) {
            x += h;
            sum += weight(i) * f.calculate(x);
        }

        return scale(sum);
    }

    protected abstract double boundaryTerm(double x);

    protected abstract int weight(int i);

    protected abstract double scale(double sum);
}

