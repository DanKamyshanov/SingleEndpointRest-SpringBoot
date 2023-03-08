package com.testtask.task;

import com.testtask.task.entity.Person;

import com.testtask.task.service.PersonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class PersonServiceTests {

    PersonServiceImpl personService = new PersonServiceImpl();

    @Test
    void shouldCalculateAgeCorrectly(){
        Person person = new Person();
        person.setDateOfBirth(Date.valueOf("1972-07-11"));
        Date dateOfBirth = person.getDateOfBirth();
        int expectedAge = Period.between(dateOfBirth.toLocalDate(), LocalDate.now()).getYears();

        Assertions.assertEquals(expectedAge, personService.getPersonAge(person));
    }

    @Test
    void shouldThrowRuntimeExceptionIfDateOfBirthIsGreaterThenCurrentDate(){
        Person person = new Person();
        person.setDateOfBirth(Date.valueOf(LocalDate.now().plusYears(5)));
        Assertions.assertThrows(RuntimeException.class, () -> personService.getPersonAge(person));
    }
}
