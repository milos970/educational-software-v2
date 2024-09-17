package com.milos.numeric.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumericalMethods
{
    @GetMapping("/numerical-methods/non-linear")
    public String nonLinear(@RequestBody NonLinear)
    {

    }
}
