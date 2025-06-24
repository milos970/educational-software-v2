package com.milos.numeric.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController
{
    @GetMapping("/student")
    public String studentResource()
    {
        return "Student's page";
    }

    @GetMapping("/teacher")
    public String teacherResource()
    {
        return "Teacher's page";
    }


    @GetMapping("/public")
    public String pub()
    {
        return "Public";
    }
}
