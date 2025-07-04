package com.milos.numeric.controllers;

import com.milos.numeric.dtos.AbsencesUpdateDto;
import com.milos.numeric.dtos.PointsUpdateDto;
import com.milos.numeric.entities.Student;
import com.milos.numeric.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return this.studentService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        this.studentService.deleteStudent(id);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllStudents() {
        this.studentService.deleteAll();
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public List<Student> getAllStudents() {
        return this.studentService.findAll();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateStudentPoints(@PathVariable Long id, @RequestBody PointsUpdateDto dto) {
        this.studentService.updatePoints(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateStudentAbsences(@PathVariable Long id, @RequestBody AbsencesUpdateDto dto) {
        this.studentService.updateAbsences(id, dto);
        return ResponseEntity.noContent().build();
    }
}
