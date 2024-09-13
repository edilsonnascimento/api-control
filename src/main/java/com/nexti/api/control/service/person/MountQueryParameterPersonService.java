package com.nexti.api.control.service.person;

import com.nexti.api.control.domain.Person;
import com.nexti.api.control.dto.*;
import com.nexti.api.control.repository.person.sql.PersonSQLParts;
import com.nexti.api.control.service.common.MountQueryParameter;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MountQueryParameterPersonService extends MountQueryParameter {

    public QueryParameterDto insertPerson(Person person) {
        return applyFieldsPerson(person, PersonSQLParts.INSERT);
    }

    public QueryParameterDto find(UUID personUuid) {
        return applyFieldPersonUuid(personUuid, PersonSQLParts.FIND);
    }

    public QueryParameterDto findAll() {
        return applySql(PersonSQLParts.FIND_ALL);
    }

    public QueryParameterDto updatePerson(Person person) {
        return applyFieldsUpdatePerson(person, PersonSQLParts.UPDATE);
    }

    public QueryParameterDto patchPerson(Person person) {
        return applyFieldsPathPerson(person, PersonSQLParts.PATCH);
    }

    public QueryParameterDto delete(UUID personUuid) {
        return applyFieldPersonUuid(personUuid, PersonSQLParts.DELETE);
    }
}