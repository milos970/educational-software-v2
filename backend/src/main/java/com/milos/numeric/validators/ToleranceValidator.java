package com.milos.numeric.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class ToleranceValidator implements ConstraintValidator<ToleranceValid, Double>
{
    private static final Set<Double> SET = Set.of(0.1,0.01,0.001,0.0001,0.00001,0.000001);

    @Override
    public boolean isValid(Double tolerance, ConstraintValidatorContext constraintValidatorContext)
    {

        return SET.contains(tolerance);
    }

    @Override
    public void initialize(ToleranceValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
