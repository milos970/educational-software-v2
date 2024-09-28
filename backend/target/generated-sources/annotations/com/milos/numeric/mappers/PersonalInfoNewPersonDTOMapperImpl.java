package com.milos.numeric.mappers;

import com.milos.numeric.dtos.PersonalInfoDto;
import com.milos.numeric.entities.PersonalInfo;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-28T23:37:05+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class PersonalInfoNewPersonDTOMapperImpl implements PersonalInfoNewPersonDTOMapper {

    @Override
    public PersonalInfo sourceToDestination(PersonalInfoDto source) {
        if ( source == null ) {
            return null;
        }

        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setName( source.getName() );
        personalInfo.setSurname( source.getSurname() );
        personalInfo.setPersonalNumber( source.getPersonalNumber() );
        personalInfo.setEmail( source.getEmail() );
        personalInfo.setPassword( source.getPassword() );

        return personalInfo;
    }

    @Override
    public PersonalInfoDto destinationToSource(PersonalInfo destination) {
        if ( destination == null ) {
            return null;
        }

        PersonalInfoDto personalInfoDto = new PersonalInfoDto();

        personalInfoDto.setName( destination.getName() );
        personalInfoDto.setSurname( destination.getSurname() );
        personalInfoDto.setPersonalNumber( destination.getPersonalNumber() );
        personalInfoDto.setEmail( destination.getEmail() );
        personalInfoDto.setPassword( destination.getPassword() );

        return personalInfoDto;
    }
}
