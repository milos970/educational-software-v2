package com.milos.numeric.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.mariuszgromada.math.mxparser.Function;

public class FunctionValidator  implements ConstraintValidator<FunctionValid, String>
{


    @Override
    public void initialize(FunctionValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
    {
        Function f = new Function("f(x) = " + s);
        return f.checkSyntax();
    }
}
