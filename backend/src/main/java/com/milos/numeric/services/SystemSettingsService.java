package com.milos.numeric.services;

import com.milos.numeric.Authority;
import com.milos.numeric.DateParser;
import com.milos.numeric.dtos.NewAbsentsDto;
import com.milos.numeric.dtos.NewDateDto;
import com.milos.numeric.dtos.NewTeacherDto;
import com.milos.numeric.entities.Employee;
import com.milos.numeric.entities.SystemSettings;
import com.milos.numeric.repositories.SystemSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class SystemSettingsService
{
    private final SystemSettingsRepository systemSettingsRepository;
    private final EmployeeService employeeService;
    private final DateParser dateParser;


    @Autowired
    public SystemSettingsService(SystemSettingsRepository systemSettingsRepository, EmployeeService employeeService, DateParser dateParser)
    {
        this.systemSettingsRepository = systemSettingsRepository;
        this.employeeService = employeeService;
        this.dateParser = dateParser;
    }




    public Optional<SystemSettings> findFirst() {
        return this.systemSettingsRepository.findFirst();
    }

    public boolean successfullUploaded(boolean value)
    {
        Optional<SystemSettings> optional = this.systemSettingsRepository.findFirst();

        if (optional.isEmpty())
        {
            return false;
        }

        SystemSettings systemSettings = optional.get();
        systemSettings.setSuccessfull(value);
        this.systemSettingsRepository.save(systemSettings);

        return true;
    }



    public boolean updateAbsents(NewAbsentsDto newAbsentsDto)
    {
        Optional<SystemSettings> optional = this.systemSettingsRepository.findFirst();

        if (optional.isEmpty())
        {
            return false;
        }

        SystemSettings systemSettings = optional.get();
        int value = newAbsentsDto.getAbsents();
        systemSettings.setAllowedAbsents(value);
        this.systemSettingsRepository.save(systemSettings);

        return true;
    }



    public boolean updateTeacher(NewTeacherDto newTeacherDto)
    {

        Optional<SystemSettings> optionalSystemSettings = this.systemSettingsRepository.findFirst();

        if (optionalSystemSettings.isEmpty())
        {

            return false;
        }

        SystemSettings systemSettings = optionalSystemSettings.get();

        String username = newTeacherDto.getUsername();

        Optional<Employee> s = this.employeeService.findByUsername(username);

        if (s.isEmpty()) {

        }




        Optional<Employee> optionalEmployee = this.employeeService.findByUsername(username);
        Employee newTeacher = optionalEmployee.get();


        if (this.employeeService.count() == 1)
        {

            systemSettings.setEmployee(newTeacher);
        } else
        {
            Employee oldTeacher = systemSettings.getEmployee();
            oldTeacher.getPersonalInfo().setAuthority(Authority.EMPLOYEE);
            newTeacher.getPersonalInfo().setAuthority(Authority.TEACHER);
            systemSettings.setEmployee(newTeacher);

            this.employeeService.save(oldTeacher);

        }



        this.systemSettingsRepository.save(systemSettings);
        return true;
    }






}
