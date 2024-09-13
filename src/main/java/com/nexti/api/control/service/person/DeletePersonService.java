package com.nexti.api.control.service.person;

import com.nexti.api.control.repository.person.impl.PersonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeletePersonService {

    @Autowired
    private MountQueryParameterPersonService mountQueryParameterPersonService;
    @Autowired
    private PersonRepositoryImpl personRepository;
    @Autowired
    private FindPersonService findPersonService;

    public void delete(UUID personUuid) {
        var queryParameterDto = mountQueryParameterPersonService.delete(personUuid);
        personRepository.delete(queryParameterDto);
    }
}
