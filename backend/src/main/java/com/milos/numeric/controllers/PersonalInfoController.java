package com.milos.numeric.controllers;

import com.milos.numeric.services.*;
import com.milos.numeric.validators.CsvValidator;
import com.milos.numeric.validators.EmployeeEmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PersonalInfoController {
    private final PersonalInfoService personalInfoService;

    private final StudentService studentService;

    private final EmployeeService employeeService;


    private final EmployeeEmailValidator employeeEmailValidator;

    private final CsvValidator csvValidator;

    private final SystemSettingsService systemSettingsService;


    @Autowired
    public PersonalInfoController(PersonalInfoService personalInfoService, StudentService studentService, EmployeeService employeeService,  EmployeeEmailValidator employeeEmailValidator, CsvValidator csvValidator, SystemSettingsService systemSettingsService) {
        this.personalInfoService = personalInfoService;
        this.studentService = studentService;
        this.employeeService = employeeService;
        this.employeeEmailValidator = employeeEmailValidator;
        this.csvValidator = csvValidator;
        this.systemSettingsService = systemSettingsService;
    }






}
