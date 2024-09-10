package com.nexti.api.control.service.person;

import com.nexti.api.control.dto.PersonDto;
import com.nexti.api.control.repository.person.impl.PersonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsertPersonService {

    @Autowired
    private MountQueryParameterPeopleService mountQueryParameterPeopleService;
    @Autowired
    private PersonRepositoryImpl peopleRepository;

    public Long insert(PersonDto personDto) {
        var queryParameterDto = mountQueryParameterPeopleService.insertPerson(personDto);
        return peopleRepository.insert(queryParameterDto);
    }
}