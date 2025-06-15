package com.milos.numeric.controllers;

import com.milos.numeric.email.EmailServiceImpl;
import com.milos.numeric.entities.*;
import com.milos.numeric.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Optional;

@Controller
public class PageController {

    private final PersonalInfoService personalInfoService;
    private final StudentService studentService;
    private final EmployeeService employeeService;
    private final EmailServiceImpl emailService;
    private final MaterialService materialService;
    private final SystemSettingsService systemSettingsService;


    @Autowired
    public PageController(PersonalInfoService personalInfoService, StudentService studentService, EmployeeService employeeService, EmailServiceImpl emailService, MaterialService materialService, SystemSettingsService systemSettingsService) {
        this.personalInfoService = personalInfoService;
        this.studentService = studentService;
        this.employeeService = employeeService;
        this.emailService = emailService;
        this.materialService = materialService;
        this.systemSettingsService = systemSettingsService;
    }


    @GetMapping("/methods")
    public String methods(@AuthenticationPrincipal UserDetails myUserDetails, Model model)
    {
        String username = myUserDetails.getUsername();
        Optional<PersonalInfo> optionalPersonalInfo = this.personalInfoService.findByUsername(username);
        PersonalInfo personalInfo = optionalPersonalInfo.get();

        model.addAttribute("personalInfo", personalInfo);

        return "index";
    }

    @GetMapping("/profile")
    public String studentProfilePage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        Optional<Student> optionalStudent = this.studentService.findByUsername(username);

        Student student = optionalStudent.get();

        model.addAttribute("student", student);


        return "pages/main/profile";
    }


    @GetMapping("system")
    public String systemPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();

        Optional<PersonalInfo> optionalPersonalInfo = this.personalInfoService.findByUsername(username);
        PersonalInfo personalInfo = optionalPersonalInfo.get();
        model.addAttribute("personalInfo", personalInfo);

        Optional<SystemSettings> optional = this.systemSettingsService.findFirst();

        if (optional.isEmpty()) {

        }

        SystemSettings systemSettings = optional.get();


        model.addAttribute("systemSettings", systemSettings);
        model.addAttribute("students", this.studentService.findAll());

        return "pages/main/system";
    }


    @GetMapping("students")
    public String studentsPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        Optional<PersonalInfo> optionalPersonalInfo = this.personalInfoService.findByUsername(username);

        if (optionalPersonalInfo.isEmpty()) {
            return "redirect:admin/material/page/error";
        }


        PersonalInfo personalInfo = optionalPersonalInfo.get();
        model.addAttribute("personalInfo", personalInfo);

        List<Student> students = this.studentService.findAllByPointsAsc();
        model.addAttribute("students", students);


        return "pages/main/students";
    }

    @GetMapping("employees")
    public String employeesPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        Optional<PersonalInfo> optionalPersonalInfo = this.personalInfoService.findByUsername(username);

        if (optionalPersonalInfo.isEmpty()) {
            return "redirect:admin/material/page/error";
        }


        PersonalInfo personalInfo = optionalPersonalInfo.get();
        model.addAttribute("personalInfo", personalInfo);

        List<Employee> employees = this.employeeService.findAll();

        model.addAttribute("employees", employees);

        return "pages/main/employees";
    }


    @GetMapping("materials")
    public String materialsPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List<Material> materials = this.materialService.findAll();

        if (materials.isEmpty()) {

        }

        model.addAttribute("materials", materials);

        String username = userDetails.getUsername();
        Optional<PersonalInfo> optionalPersonalInfo = this.personalInfoService.findByUsername(username);

        if (optionalPersonalInfo.isEmpty()) {
            return "redirect:admin/material/page/error";
        }

        PersonalInfo personalInfo = optionalPersonalInfo.get();

        model.addAttribute("personalInfo", personalInfo);



        return "pages/main/materials";
    }


    @GetMapping("/login")
    public String loginPage()
    {
         return "pages/alt/login";
    }




}
