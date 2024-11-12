package com.minbashi.project.service;

import com.minbashi.project.exception.ApplicationBusinessException;
import com.minbashi.project.exception.ExceptionMessage;
import com.minbashi.project.model.Person;
import com.minbashi.project.repositoy.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Person getById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person update(Long id, Person personDetails) {
        Person person = this.getById(id);
        if (person == null) {
            log.error("Person is not found with id : {}", id);
            throw new ApplicationBusinessException(ExceptionMessage.PERSON_NOT_FOUND);
        }
        person.setFirstName(personDetails.getFirstName());
        person.setLastName(personDetails.getLastName());
        person.setAge(personDetails.getAge());
        return personRepository.save(person);
    }

    @Override
    public void delete(Long id) {
        Person person = this.getById(id);
        if (person == null) {
            log.error("Person is not found with id : {}", id);
            throw new ApplicationBusinessException(ExceptionMessage.PERSON_NOT_FOUND);
        }
        personRepository.deleteById(id);
    }
}
