package com.milos.numeric.exceptions;

public class StudentNotFoundException extends RuntimeException
{
    private final Long id;

    public StudentNotFoundException(Long id)
    {
        super(String.format("Student with ID %d not found!", id));
        this.id = id;

    }

    public Long getId() {
        return id;
    }
}
