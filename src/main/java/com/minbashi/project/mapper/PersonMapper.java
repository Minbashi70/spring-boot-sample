package com.minbashi.project.mapper;

import com.minbashi.project.dto.request.PersonRequest;
import com.minbashi.project.dto.response.PersonResponse;
import com.minbashi.project.model.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    Person toEntity(PersonRequest request);

    PersonResponse toResponse(Person person);

    List<PersonResponse> toResponse(List<Person> person);
}
