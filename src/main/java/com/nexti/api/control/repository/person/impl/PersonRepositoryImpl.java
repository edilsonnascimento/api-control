package com.nexti.api.control.repository.person.impl;

import com.nexti.api.control.domain.Person;
import com.nexti.api.control.repository.common.BaseRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class PersonRepositoryImpl extends BaseRepository<Person>{

        @Override
        protected RowMapper<Person> returnRowMapper() {
            return (result, rowNum) -> Person.builder()
                    .id(result.getLong("id"))
                    .name(result.getString("name"))
                    .enrolment(result.getString("enrolment"))
                    .email(result.getString("email"))
                    .admissionDate(result.getDate("admissionDate").toLocalDate())
                    .externalId(result.getString("externalId"))
                    .registerDate(result.getTimestamp("registerDate").toLocalDateTime())
                    .lastUpdateDate(Objects.isNull(result.getTimestamp("lastUpdateDate")) ? null :
                                            result.getTimestamp("lastUpdateDate").toLocalDateTime())
                    .customerId(result.getLong("customerId"))
                    .build();
        }
}
