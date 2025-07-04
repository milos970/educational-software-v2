package com.milos.numeric.services;

import com.milos.numeric.dtos.AbsencesUpdateDto;
import com.milos.numeric.dtos.PointsUpdateDto;
import com.milos.numeric.entities.Student;
import com.milos.numeric.exceptions.StudentNotFoundException;
import com.milos.numeric.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService
{
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> findAll()
    {
        return this.studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public void deleteStudent(Long id) {
        Student student = this.findById(id);
        this.studentRepository.delete(student);
    }

    public void deleteAll() {
        this.studentRepository.deleteAll();
    }


    public void updatePoints(Long id, PointsUpdateDto dto)
    {
        Student student = this.findById(id);
        student.setPoints(student.getPoints() + dto.getPoints());
        this.studentRepository.save(student);
    }


    public void updateAbsences(Long id, AbsencesUpdateDto dto) {
        Student student = this.findById(id);
        student.setAbsents(student.getAbsents() + dto.getAbsences());
        this.studentRepository.save(student);
    }




}
