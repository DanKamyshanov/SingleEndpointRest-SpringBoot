package com.testtask.task.controller;

import com.testtask.task.entity.Person;
import com.testtask.task.entity.PersonRest;
import com.testtask.task.repository.PersonRepository;
import com.testtask.task.service.PersonServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonRestController {

    private static final Logger logger = Logger.getLogger(PersonRestController.class);

    PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    PersonServiceImpl personService;

    @Autowired
    public void setPersonService(PersonServiceImpl personService){
        this.personService = personService;
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Object> getPersonById(@PathVariable("id")int id){
        Person person = personRepository.findById(id);
        if(person == null){
            logger.warn("Person with id " + id + " does not exist");
            return new ResponseEntity<>("Person with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }
        PersonRest personWithAge = new PersonRest(person.getId(), person.getFirstName(), person.getLastName(),
                personService.getPersonAge(person));
        logger.info("In controller. Person (id: " + id + ") retrieved");
        return new ResponseEntity<>(personWithAge, HttpStatus.OK);
    }
}
