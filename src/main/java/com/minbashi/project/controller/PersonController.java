package com.minbashi.project.controller;

import com.minbashi.project.dto.request.PersonRequest;
import com.minbashi.project.dto.response.PersonResponse;
import com.minbashi.project.mapper.PersonMapper;
import com.minbashi.project.model.Person;
import com.minbashi.project.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @GetMapping
    public ResponseEntity<List<PersonResponse>> getAll() {
        List<PersonResponse> persons = personMapper.toResponse(personService.getAll());
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(personMapper.toResponse(personService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody PersonRequest person) {
        Person createdPerson = personService.save(personMapper.toEntity(person));
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody PersonRequest personDetails) {
        Person person = personMapper.toEntity(personDetails);
        Person updatedPerson = personService.update(id, person);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
