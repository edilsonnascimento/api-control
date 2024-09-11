package com.nexti.api.control.service.common;

import com.nexti.api.control.domain.Person;
import com.nexti.api.control.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.*;
import java.time.LocalDateTime;

public abstract class MountQueryParameter {

    @Autowired
    private SecurityService securityService;

    protected MapSqlParameterSource createAndApplyFilterCustomer() {
        Long customerId = securityService.getLoggedCustomerId();
        return new MapSqlParameterSource()
                .addValue("customerId", customerId, Types.INTEGER);
    }

    protected QueryParameterDto applyFields(TaskDto taskDto, String sql) {
        MapSqlParameterSource parameter = createAndApplyFilterCustomer();
        parameter.addValue("description", taskDto.getDescription(), Types.VARCHAR);
        parameter.addValue("status", taskDto.getStatus(), Types.VARCHAR);
        parameter.addValue("personId", taskDto.getPersonId(), Types.INTEGER);
        parameter.addValue("registerDate", taskDto.getRegisterDate(), Types.TIMESTAMP);
        return new QueryParameterDto(sql, parameter);
    }

    protected QueryParameterDto applyFieldsPerson(PersonDto personDto, String sql) {
        MapSqlParameterSource parameter = createAndApplyFilterCustomer();
        parameter.addValue("name", personDto.getName(), Types.VARCHAR);
        parameter.addValue("enrolment", personDto.getEnrolment(), Types.VARCHAR);
        parameter.addValue("email", personDto.getEmail(), Types.VARCHAR);
        parameter.addValue("admissionDate", Date.valueOf(personDto.getAdmissionDate()), Types.DATE);
        parameter.addValue("externalId", personDto.getExternalId(), Types.VARCHAR);
        return new QueryParameterDto(sql, parameter);
    }

    protected QueryParameterDto applyFieldsUpdatePerson(Person person, Long personId, String sql) {
        MapSqlParameterSource parameter = createAndApplyFilterCustomer();
        parameter.addValue("name", person.getName(), Types.VARCHAR);
        parameter.addValue("enrolment", person.getEnrolment(), Types.VARCHAR);
        parameter.addValue("email", person.getEmail(), Types.VARCHAR);
        parameter.addValue("admissionDate", Date.valueOf(person.getAdmissionDate()), Types.DATE);
        parameter.addValue("externalId", person.getExternalId(), Types.VARCHAR);
        parameter.addValue("lastUpdateDate", Timestamp.valueOf(person.getLastUpdateDate()), Types.TIMESTAMP);
        parameter.addValue("personId", personId, Types.INTEGER);
        return new QueryParameterDto(sql, parameter);
    }

    protected QueryParameterDto applyFieldsPathPerson(Person person, Long personId, String sql) {
        MapSqlParameterSource parameter = createAndApplyFilterCustomer();
        parameter.addValue("name", person.getName(), Types.VARCHAR);
        parameter.addValue("enrolment", person.getEnrolment(), Types.VARCHAR);
        parameter.addValue("email", person.getEmail(), Types.VARCHAR);
        parameter.addValue("lastUpdateDate", Timestamp.valueOf(LocalDateTime.now()), Types.TIMESTAMP);
        parameter.addValue("personId", personId, Types.INTEGER);
        return new QueryParameterDto(sql, parameter);
    }

    protected QueryParameterDto applyFieldsClient(CustomerDto clientDto, String sql) {
        MapSqlParameterSource parameter = createAndApplyFilterCustomer();
        parameter.addValue("name", clientDto.getName(), Types.VARCHAR);
        parameter.addValue("nationalDocument", clientDto.getNationalDocument(), Types.VARCHAR);
        parameter.addValue("email", clientDto.getEmail(), Types.VARCHAR);
        return new QueryParameterDto(sql, parameter);
    }

    protected QueryParameterDto applyFieldPersonId(Long personId, String sql) {
        MapSqlParameterSource parameter = createAndApplyFilterCustomer();
        parameter.addValue("personId", personId, Types.INTEGER);
        return new QueryParameterDto(sql, parameter);
    }
    protected QueryParameterDto applySql(String sql) {
        MapSqlParameterSource parameter = createAndApplyFilterCustomer();
        return new QueryParameterDto(sql, parameter);
    }

}