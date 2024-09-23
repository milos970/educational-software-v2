package com.milos.numeric.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashSet;
import java.util.Set;

public class NodesValidator implements ConstraintValidator<NodesValid, String>
{
    private static final DecimalValidator DECIMAL_VALIDATOR = new DecimalValidator();

    @Override
    public void initialize(NodesValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String nodes, ConstraintValidatorContext constraintValidatorContext)
    {
        String pairs[] = nodes.split(";");
        Set<Double> set = new HashSet<>();

        for (String pair : pairs)
        {
            pair = pair.replaceAll("[()]", "");
            String[] keyValue = pair.split(",");

            if (!NumberUtils.isParsable(keyValue[0].trim()) || !NumberUtils.isParsable(keyValue[1].trim()))
            {
                return false;
            }

            double x = Double.parseDouble(keyValue[0].trim());
            double y = Double.parseDouble(keyValue[1].trim());

            if (!DECIMAL_VALIDATOR.isValid(x,constraintValidatorContext) || !DECIMAL_VALIDATOR.isValid(y,constraintValidatorContext))
            {
                return false;
            }

            if (set.contains(x))
            {
                return false;
            }

            set.add(x);


        }

        return true;
    }
}
