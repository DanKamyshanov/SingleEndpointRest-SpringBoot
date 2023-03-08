package com.testtask.task.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonRest {

    private int id;

    private String firstName;

    private String lastName;

    private int age;

    public PersonRest(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
