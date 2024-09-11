package com.nexti.api.control.service.person;

import com.nexti.api.control.domain.Person;
import com.nexti.api.control.dto.*;
import com.nexti.api.control.repository.person.sql.PersonSQLParts;
import com.nexti.api.control.service.common.MountQueryParameter;
import org.springframework.stereotype.Service;

@Service
public class MountQueryParameterPersonService extends MountQueryParameter {

    public QueryParameterDto insertPerson(PersonDto personDto) {
        return applyFieldsPerson(personDto, PersonSQLParts.INSERT);
    }

    public QueryParameterDto find(Long personId) {
        return applyFieldPersonId(personId, PersonSQLParts.FIND);
    }

    public QueryParameterDto findAll() {
        return applySql(PersonSQLParts.FIND_ALL);
    }

    public QueryParameterDto updatePerson(Person person, Long personId) {
        return applyFieldsUpdatePerson(person, personId, PersonSQLParts.UPDATE);
    }

    public QueryParameterDto patchPerson(Person person, Long personId) {
        return applyFieldsPathPerson(person, personId, PersonSQLParts.PATCH);
    }

    public QueryParameterDto delete(Long personId) {
        return applyFieldPersonId(personId, PersonSQLParts.DELETE);
    }
}