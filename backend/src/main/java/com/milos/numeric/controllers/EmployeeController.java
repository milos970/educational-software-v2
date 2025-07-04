package com.milos.numeric.controllers;

import com.milos.numeric.Role;
import com.milos.numeric.entities.Employee;
import com.milos.numeric.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PatchMapping("/{id}")
    public void updateRole(@RequestBody Role role, @PathVariable Long id) {
        this.employeeService.updateRole(id, role);
    }


    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return this.employeeService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        this.employeeService.deleteStudent(id);
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteAllEmployees() {
        this.employeeService.deleteAll();
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public List<Employee> getAllEmployees() {
        return this.employeeService.findAll();
    }
}
