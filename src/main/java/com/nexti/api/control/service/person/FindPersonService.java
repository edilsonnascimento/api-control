package com.nexti.api.control.service.person;

import com.nexti.api.control.domain.Person;
import com.nexti.api.control.exception.NotFoundException;
import com.nexti.api.control.repository.person.impl.PersonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindPersonService {

    @Autowired
    private MountQueryParameterPersonService mountQueryParameterPersonService;
    @Autowired
    private PersonRepositoryImpl personRepository;

    public Person find(Long personId) {
        var queryParameterDto = mountQueryParameterPersonService.find(personId);
        return personRepository.find(queryParameterDto)
                .orElseThrow(()-> new NotFoundException("PERSON_NOT_FOUND"))
                .getFirst();
    }
}