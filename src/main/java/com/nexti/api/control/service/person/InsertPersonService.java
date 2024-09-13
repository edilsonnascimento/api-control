package com.nexti.api.control.service.person;

import com.nexti.api.control.domain.Person;
import com.nexti.api.control.dto.PersonDto;
import com.nexti.api.control.repository.person.impl.PersonRepositoryImpl;
import com.nexti.api.control.service.common.UuidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InsertPersonService {

    @Autowired
    private MountQueryParameterPersonService mountQueryParameterPersonService;
    @Autowired
    private PersonRepositoryImpl personRepository;
    @Autowired
    private UuidService uuidService;

    public UUID insert(PersonDto personDto) {
        var person = getPerson(personDto);
        var queryParameterDto = mountQueryParameterPersonService.insertPerson(person);
        personRepository.update(queryParameterDto);
        return person.getUuid();
    }
    private Person getPerson(PersonDto personDto) {
        return Person.builder()
                           .name(personDto.getName())
                           .enrolment(personDto.getEnrolment())
                           .email(personDto.getEmail())
                           .admissionDate(personDto.getAdmissionDate())
                           .externalId(personDto.getExternalId())
                           .uuid(uuidService.random())
              .build();
    }
}