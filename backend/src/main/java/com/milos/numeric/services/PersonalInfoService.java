package com.milos.numeric.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.milos.numeric.Authority;
import com.milos.numeric.Domain;
import com.milos.numeric.Gender;
import com.milos.numeric.dtos.PersonalInfoDto;
import com.milos.numeric.email.EmailServiceImpl;
import com.milos.numeric.entities.PersonalInfo;
import com.milos.numeric.repositories.PersonalInfoRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonalInfoService
{
    private final PersonalInfoRepository personalInfoRepository;

    private final SystemSettingsService systemSettingsService;

    private final Validator validator;

    private final EmailServiceImpl emailService;









    @Autowired
    public PersonalInfoService(PersonalInfoRepository personalInfoRepository, SystemSettingsService systemSettingsService, Validator validator, EmailServiceImpl emailService) {
        this.personalInfoRepository = personalInfoRepository;
        this.systemSettingsService = systemSettingsService;
        this.validator = validator;
        this.emailService = emailService;
    }

    public Optional<String> findUsernameByAuthorityTeacher()//OK
    {
        return this.personalInfoRepository.findUsernameByAuthority(Authority.TEACHER.name());
    }

    public Optional<Authority> findAuthorityByUsername(String username)//OK
    {
        Optional<String> optional = this.personalInfoRepository.findAuthorityByUsername(username);
        return Optional.of(Authority.valueOf(optional.get()));
    }


    public Optional<PersonalInfo> findByAuthority(Authority authority)
    {
        return this.personalInfoRepository.findByAuthority(authority.name());
    }

    public Optional<PersonalInfo> findByUsername(String username)
    {
        return this.personalInfoRepository.findByUsername(username);
    }

    public Optional<PersonalInfo> findByEmail(String email)
    {
        return this.personalInfoRepository.findByEmail(email);
    }


    /*
    Zisti na zaklade pravdepodobnosti podla mena pohlavie.
     */
    private String determineGender(@PathVariable String name) {
        String uri = "https://api.genderize.io?name=" + name;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode newNode = null;
        try {
            newNode = mapper.readTree(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return newNode.get("gender").asText();
    }

    public void activate(PersonalInfo personalInfo)
    {
        personalInfo.setEnabled(true);
        this.personalInfoRepository.save(personalInfo);

    }

    public void delete(PersonalInfo personalInfo)
    {
        this.personalInfoRepository.delete(personalInfo);
    }


    public Optional<PersonalInfo> createPerson(PersonalInfoDto personalInfoDTO)
    {

        Set<ConstraintViolation<PersonalInfoDto>> violations = validator.validate(personalInfoDTO);
        if (!violations.isEmpty())
        {
            return Optional.empty();
        }

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName(personalInfoDTO.getName());
        personalInfo.setSurname(personalInfoDTO.getSurname());
        personalInfo.setEmail(personalInfoDTO.getEmail());

        String email = personalInfoDTO.getEmail();
        String emailDomain = email.substring(email.indexOf("@") + 1);

        String gender = this.determineGender(personalInfo.getName());

        if (Gender.MALE.name().toLowerCase().equals(gender)) {
            personalInfo.setGender(Gender.MALE);
        }

        if (Gender.FEMALE.name().toLowerCase().equals(gender)) {
            personalInfo.setGender(Gender.FEMALE);
        }

        String username = personalInfoDTO.getEmail().substring(0,personalInfo.getEmail().indexOf("@"));
        personalInfo.setUsername(username);
        personalInfo.setEnabled(false);

        if (emailDomain.equals("gmail.com"))
        {

            if (this.personalInfoRepository.count() == 0)
            {
                personalInfo.setAuthority(Authority.TEACHER);

            } else {
                personalInfo.setAuthority(Authority.EMPLOYEE);
            }
        }

        if (emailDomain.equals(Domain.STUDENT_DOMAIN.getDomain()))
        {

            personalInfo.setAuthority(Authority.STUDENT);
        }



        return Optional.of(this.personalInfoRepository.save(personalInfo));
    }


    public void deleteByAuthority(String authority) {
        this.personalInfoRepository.deleteByAuthority(authority);
    }









    public boolean createMultiplePersonsFromFile(MultipartFile file)
    {
        List<PersonalInfoDto> list = new LinkedList<>();

        Reader reader = null;
        try {
            reader = new InputStreamReader(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();


        String[] values = null;
        while (true)
        {
            try {
                if ((values = csvReader.readNext()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            PersonalInfoDto person = new PersonalInfoDto();

            String[] rec = values[0].split(";");
            String personalNumber = rec[2];
            String name = rec[1];
            String surname = rec[0];
            String email = rec[3];


            person.setPersonalNumber(personalNumber);
            person.setName(name);
            person.setSurname(surname);
            person.setEmail(email);
            this.createPerson(person);
        }


        return true;
    }

}
