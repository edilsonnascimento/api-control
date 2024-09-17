package com.nexti.api.control.service.person;

import com.nexti.api.control.domain.Person;
import com.nexti.api.control.repository.person.impl.PersonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FindPeopleService {

    @Autowired
    private MountQueryParameterPersonService mountQueryParameterPersonService;
    @Autowired
    private PersonRepositoryImpl personRepository;

    public List<Person> find() {
        var queryParameterDto = mountQueryParameterPersonService.findAll();
        return personRepository.find(queryParameterDto).orElse(new ArrayList<>());
    }
}