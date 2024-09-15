package com.milos.numeric.repositories;

import com.milos.numeric.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>
{
    public List<Student> findAllByOrderByPointsDesc();

    @Query(value = "SELECT s.* FROM student s JOIN personal_info p ON s.person_id = p.id WHERE p.username = :username", nativeQuery = true)
    public Optional<Student> findByUsername(String username);





}
