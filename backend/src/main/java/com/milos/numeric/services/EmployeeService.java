package com.milos.numeric.services;

import com.milos.numeric.Role;
import com.milos.numeric.entities.Employee;
import com.milos.numeric.exceptions.StudentNotFoundException;
import com.milos.numeric.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService
{
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }



    public void updateRole(Long id, Role role)
    {
        /*Employee employeeToUpdate = this.findById(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No authenticated user found");
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Employee employeeToUpdate = this.findById(id);

        return userDetails.getId();*/


    }

    public Employee findById(Long id) {
        return this.employeeRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public void deleteStudent(Long id) {
        Employee employee = this.findById(id);
        this.employeeRepository.delete(employee);
    }

    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    public void deleteAll() {
        this.employeeRepository.deleteAll();
    }




}
