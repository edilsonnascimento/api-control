package com.nexti.api.control.service.person;

import com.nexti.api.control.dto.*;
import com.nexti.api.control.repository.person.sql.PersonSQLParts;
import com.nexti.api.control.service.common.MountQueryParameter;
import org.springframework.stereotype.Service;

@Service
public class MountQueryParameterPeopleService extends MountQueryParameter {

    public QueryParameterDto insertPerson(PersonDto personDto) {
        String sql = PersonSQLParts.INSERT;
        return applyFieldsPerson(personDto, sql);
    }
}
