package com.milos.numeric.controllers;

import com.milos.numeric.parameters.NonLinear;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumericalMethods
{
    @GetMapping("/numerical-methods/non-linear/newton")
    public String nonLinearNewton(@RequestBody @Valid NonLinear nonLinear)
    {
            return null;
    }

    @GetMapping("/numerical-methods/non-linear/regula-falsi")
    public String nonLinearRegulaFalsi(@RequestBody @Valid NonLinear nonLinear)
    {
        return null;
    }

    @GetMapping("/numerical-methods/non-linear/Bisection")
    public String nonLinearBisection(@RequestBody @Valid NonLinear nonLinear)
    {
        return null;
    }


}
