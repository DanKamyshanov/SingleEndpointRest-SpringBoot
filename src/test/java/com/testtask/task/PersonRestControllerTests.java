package com.testtask.task;

import com.testtask.task.entity.Person;
import com.testtask.task.repository.PersonRepository;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.sql.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonRestControllerTests {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	WebTestClient testClient;

	Person testPerson;

	@BeforeEach
	public void createTestPerson(){
		testPerson = new Person();
		testPerson.setFirstName("John");
		testPerson.setLastName("Smith");
		testPerson.setDateOfBirth(Date.valueOf("2004-02-13"));
		testPerson.setId(personRepository.save(testPerson).getId());
	}

	@Test
	public void shouldReturnUserInfoForValidId(){
		this.testClient.get().uri("/person/{id}", testPerson.getId()).
				header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).exchange().expectStatus().
				is2xxSuccessful().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody()
				.jsonPath("id").isEqualTo(testPerson.getId())
				.jsonPath("firstName").isEqualTo("John")
				.jsonPath("lastName").isEqualTo("Smith")
				.jsonPath("age").isEqualTo(19);
	}

	@AfterEach
	public void deleteTestPerson(){
		personRepository.delete(testPerson);
	}

	@Test
	public void shouldReturnNotFoundForNonExistingId(){
		this.testClient.get().uri("/person/{id}", personRepository.findAll().size() + 1)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).exchange().expectStatus()
				.isEqualTo(HttpStatus.NOT_FOUND);
	}
}
