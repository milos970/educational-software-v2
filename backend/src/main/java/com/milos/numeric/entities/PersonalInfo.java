package com.milos.numeric.entities;

import com.milos.numeric.Authority;
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
    @Size(min = 1, message = "PersonalInfo name is less than 1 character long!")
    @Size(max = 50, message = "PersonalInfo name is more than 50 characters long!")
    private String name;

    @NotBlank
    @Size(min = 1, message = "PersonalInfo surname is less than 1 character long!")
    @Size(max = 50, message = "PersonalInfo surname is more than 50 characters long!")
    private String surname;

    @NotBlank
    @Column(unique = true)
    @Size(min = 1, message = "PersonalInfo username is less than 1 character long!")
    @Size(max = 50, message = "PersonalInfo username is more than 50 characters long!")
    private String username;

    @Email
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    private boolean enabled;


}
