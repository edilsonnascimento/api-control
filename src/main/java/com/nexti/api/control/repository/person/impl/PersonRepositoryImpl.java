package com.nexti.api.control.repository.person.impl;

import com.nexti.api.control.domain.Person;
import com.nexti.api.control.repository.common.BaseRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepositoryImpl extends BaseRepository<Person>{

        @Override
        protected RowMapper<Person> returnRowMapper() {
            return (result, rowNum) -> Person.builder()
                    .id(result.getLong("id"))
                    .name(result.getString("name"))
                    .enrolment(result.getString("enrolment"))
                    .admissionDate(result.getDate("admissionDate").toLocalDate())
                    .registerDate(result.getTimestamp("registerDate").toLocalDateTime())
                    .customerId(result.getLong("customerId"))
                    .build();
        }
}
