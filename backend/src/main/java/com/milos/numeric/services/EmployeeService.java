package com.milos.numeric.services;

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



    public void updateAuthority(Long id)
    {

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
