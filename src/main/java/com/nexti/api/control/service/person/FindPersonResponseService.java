package com.nexti.api.control.service.person;

import com.nexti.api.control.domain.Person;
import com.nexti.api.control.dto.PersonResponseDto;
import com.nexti.api.control.exception.*;
import com.nexti.api.control.repository.person.impl.PersonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FindPersonResponseService {

    @Autowired
    private MountQueryParameterPersonService mountQueryParameterPersonService;
    @Autowired
    private PersonRepositoryImpl personRepository;

    public PersonResponseDto find(UUID personUuid) {
        var queryParameterDto = mountQueryParameterPersonService.find(personUuid);
        var people = personRepository.find(queryParameterDto)
                .orElseThrow(() ->new NotFoundException("PERSON_NOT_FOUND"));
        return mapPersonResponse(people);
    }

    private PersonResponseDto mapPersonResponse(List<Person> people) {
        var person = people.getFirst();
        return new PersonResponseDto().builder()
                .name(person.getName())
                .enrolment(person.getEnrolment())
                .email(person.getEmail())
                .admissionDate(person.getAdmissionDate())
                .externalId(person.getExternalId())
                .registerDate(person.getRegisterDate())
                .lastUpdateDate(person.getLastUpdateDate())
                .uuid(person.getUuid())
                .build();
    }
}
