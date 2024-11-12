package com.minbashi.project.service;

import com.minbashi.project.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAll();

    Person getById(Long id);

    Person save(Person person);

    Person update(Long id, Person personDetails);

    void delete(Long id);
}
