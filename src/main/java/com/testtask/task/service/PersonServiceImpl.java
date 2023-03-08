package com.testtask.task.service;

import com.testtask.task.entity.Person;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class PersonServiceImpl implements PersonService{

    private static final Logger logger = Logger.getLogger(PersonServiceImpl.class);

    @Override
    public int getPersonAge(Person person) {
        LocalDate dateOfBirth = person.getDateOfBirth().toLocalDate();
        LocalDate currentDate = LocalDate.now();
        if(dateOfBirth.isAfter(currentDate)){
            logger.error("Date of birth cannot exceed current date.");
            throw new RuntimeException("Date of birth cannot exceed current date.");
        }
        int age = Period.between(dateOfBirth, currentDate).getYears();
        logger.info("Person (id: " + person.getId() + ") age: " + age);
        return age;
    }
}
