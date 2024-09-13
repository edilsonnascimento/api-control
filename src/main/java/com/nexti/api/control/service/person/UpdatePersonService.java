package com.nexti.api.control.service.person;

import com.nexti.api.control.domain.Person;
import com.nexti.api.control.dto.*;
import com.nexti.api.control.repository.person.impl.PersonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdatePersonService {

    @Autowired
    private MountQueryParameterPersonService mountQueryParameterPersonService;
    @Autowired
    private PersonRepositoryImpl personRepository;
    @Autowired
    private FindPersonService findPersonService;

    public void update(PersonUpdateDto personUpdateDto, UUID personUuid) {
        var person = getUpdatedPerson(personUpdateDto, personUuid);
        var queryParameterDto = mountQueryParameterPersonService.updatePerson(person);
        personRepository.update(queryParameterDto);
    }

    private Person getUpdatedPerson(PersonUpdateDto personUpdateDto, UUID personUuid) {
        var person = findPersonService.find(personUuid);
        person.setName(personUpdateDto.getName());
        person.setEnrolment(personUpdateDto.getEnrolment());
        person.setEmail(personUpdateDto.getEmail());
        person.setAdmissionDate(personUpdateDto.getAdmissionDate());
        person.setExternalId(personUpdateDto.getExternalId());
        return person;
    }
}