package com.milos.numeric.entities;

import com.milos.numeric.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personal_info")
public class PersonalInfo
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 1, message = "Name is less than 1 character long!")
    @Size(max = 50, message = "Name is more than 50 characters long!")
    private String name;

    @NotBlank
    @Size(min = 1, message = "Surname is less than 1 character long!")
    @Size(max = 50, message = "Surname is more than 50 characters long!")
    private String surname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

}
