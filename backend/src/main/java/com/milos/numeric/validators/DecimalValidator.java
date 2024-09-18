package com.milos.numeric.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DecimalValidator  implements ConstraintValidator<DecimalValid, Double>
{
    private int decimalPlaces;

    @Override
    public void initialize(DecimalValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.decimalPlaces = constraintAnnotation.decimalPlaces();
    }

    @Override
    public boolean isValid(Double aDouble, ConstraintValidatorContext constraintValidatorContext)
    {
        String[] ar = String.valueOf(aDouble).split("\\.");
        System.out.println(String.valueOf(aDouble));

        return ar[1].length() <= decimalPlaces;
    }
}
