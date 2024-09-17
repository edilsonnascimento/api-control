package com.nexti.api.control.service.person;

import com.nexti.api.control.domain.Person;
import com.nexti.api.control.dto.PersonPatchDto;
import com.nexti.api.control.exception.BusinessException;
import com.nexti.api.control.repository.person.impl.PersonRepositoryImpl;
import com.nexti.api.control.util.ControlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatchPersonService {

    @Autowired
    private MountQueryParameterPersonService mountQueryParameterPersonService;
    @Autowired
    private PersonRepositoryImpl personRepository;
    @Autowired
    private FindPersonService findPersonService;

    public void patch(PersonPatchDto personPatchDto, UUID personUuid) {
        validFields(personPatchDto);
        var person = getPatchPerson(personPatchDto, personUuid);
        var queryParameterDto = mountQueryParameterPersonService.patchPerson(person);
        personRepository.update(queryParameterDto);
    }

    private void validFields(PersonPatchDto personPatchDto) {
        var allFieldsNull = ControlUtil.arraysIsEmptyOrNull(personPatchDto.getEnrolment(),
                                                         personPatchDto.getName());
        if(allFieldsNull)
            throw new BusinessException("SOME_FIELD_MUST_BE_INFORMED");
    }

    private Person getPatchPerson(PersonPatchDto personPatchDto, UUID personUuid) {
        var person = findPersonService.find(personUuid);

        if(Objects.nonNull(personPatchDto.getName()))
            person.setName(personPatchDto.getName());

        if(Objects.nonNull(personPatchDto.getEnrolment()))
            person.setEnrolment(personPatchDto.getEnrolment());

        return person;
    }
}