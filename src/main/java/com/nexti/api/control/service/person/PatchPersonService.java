package com.nexti.api.control.service.person;

import com.nexti.api.control.domain.Person;
import com.nexti.api.control.dto.PersonPatchDto;
import com.nexti.api.control.exception.BusinessException;
import com.nexti.api.control.repository.person.impl.PersonRepositoryImpl;
import com.nexti.api.control.util.NextiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PatchPersonService {

    @Autowired
    private MountQueryParameterPersonService mountQueryParameterPersonService;
    @Autowired
    private PersonRepositoryImpl personRepository;
    @Autowired
    private FindPersonService findPersonService;

    public void patch(PersonPatchDto personPatchDto, Long personId) {
        validFields(personPatchDto);
        var person = getPatchPerson(personPatchDto, personId);
        var queryParameterDto = mountQueryParameterPersonService.patchPerson(person, personId);
        personRepository.update(queryParameterDto);
    }

    private void validFields(PersonPatchDto personPatchDto) {
        var allFieldsNull = ! NextiUtil.nonNull(personPatchDto.getEnrolment(),
                                                         personPatchDto.getName());
        if(allFieldsNull)
            throw new BusinessException("SOME_FIELD_MUST_BE_INFORMED");
    }

    private Person getPatchPerson(PersonPatchDto personPatchDto, Long personId) {
        var person = findPersonService.find(personId);

        if(Objects.nonNull(personPatchDto.getName()))
            person.setName(personPatchDto.getName());

        if(Objects.nonNull(personPatchDto.getEnrolment()))
            person.setEnrolment(personPatchDto.getEnrolment());

        return person;
    }
}