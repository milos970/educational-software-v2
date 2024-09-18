package com.milos.numeric.parameters;

import com.milos.numeric.validators.DecimalValid;
import com.milos.numeric.validators.FunctionValid;
import com.milos.numeric.validators.ToleranceValid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class NonLinear
{
    @FunctionValid
    private final String expression;

    @DecimalMin(value="-10")
    @DecimalValid(decimalPlaces = 3)
    private final double a;


    @DecimalMax(value="10")
    @DecimalValid(decimalPlaces = 3)
    private final double b;

    @ToleranceValid
    private final double tolerance;

    @DecimalValid(decimalPlaces = 3)
    @DecimalMin(value="-10")
    @DecimalMax(value="10")
    private final double initialValue;



}
