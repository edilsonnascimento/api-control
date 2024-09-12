package com.nexti.api.control.service.person;

import com.nexti.api.control.domain.Person;
import com.nexti.api.control.dto.*;
import com.nexti.api.control.repository.person.impl.PersonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePersonService {

    @Autowired
    private MountQueryParameterPersonService mountQueryParameterPersonService;
    @Autowired
    private PersonRepositoryImpl personRepository;
    @Autowired
    private FindPersonService findPersonService;

    public void update(PersonUpdateDto personUpdateDto, Long personId) {
        var person = getUpdatedPerson(personUpdateDto, personId);
        var queryParameterDto = mountQueryParameterPersonService.updatePerson(person, personId);
        personRepository.update(queryParameterDto);
    }

    private Person getUpdatedPerson(PersonUpdateDto personUpdateDto, Long personId) {
        var person = findPersonService.find(personId);
        person.setName(personUpdateDto.getName());
        person.setEnrolment(personUpdateDto.getEnrolment());
        person.setEmail(personUpdateDto.getEmail());
        person.setAdmissionDate(personUpdateDto.getAdmissionDate());
        person.setExternalId(personUpdateDto.getExternalId());
        return person;
    }
}