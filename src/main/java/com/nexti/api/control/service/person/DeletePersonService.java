package com.nexti.api.control.service.person;

import com.nexti.api.control.repository.person.impl.PersonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePersonService {

    @Autowired
    private MountQueryParameterPersonService mountQueryParameterPersonService;
    @Autowired
    private PersonRepositoryImpl personRepository;
    @Autowired
    private FindPersonService findPersonService;

    public void delete(Long personId) {
        var queryParameterDto = mountQueryParameterPersonService.delete(personId);
        personRepository.delete(queryParameterDto);
    }
}
