package com.milos.numeric.mappers;

import com.milos.numeric.dtos.NewPasswordDto;
import com.milos.numeric.entities.PersonalInfo;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-17T21:36:21+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class PersonalInfoNewPasswordDTOMapperImpl implements PersonalInfoNewPasswordDTOMapper {

    @Override
    public PersonalInfo sourceToDestination(NewPasswordDto source) {
        if ( source == null ) {
            return null;
        }

        PersonalInfo personalInfo = new PersonalInfo();

        return personalInfo;
    }

    @Override
    public NewPasswordDto destinationToSource(PersonalInfo destination) {
        if ( destination == null ) {
            return null;
        }

        NewPasswordDto newPasswordDto = new NewPasswordDto();

        return newPasswordDto;
    }
}
