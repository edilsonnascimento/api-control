package com.nexti.api.control.service.common;

import com.nexti.api.control.domain.*;
import com.nexti.api.control.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.UUID;

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

    protected QueryParameterDto applyFieldsPerson(Person person, String sql) {
        MapSqlParameterSource parameter = createAndApplyFilterCustomer();
        parameter.addValue("name", person.getName(), Types.VARCHAR);
        parameter.addValue("enrolment", person.getEnrolment(), Types.VARCHAR);
        parameter.addValue("email", person.getEmail(), Types.VARCHAR);
        parameter.addValue("admissionDate", Date.valueOf(person.getAdmissionDate()), Types.DATE);
        parameter.addValue("externalId", person.getExternalId(), Types.VARCHAR);
        parameter.addValue("personUuid", person.getUuid(), Types.VARCHAR);
        return new QueryParameterDto(sql, parameter);
    }

    protected QueryParameterDto applyFieldsUpdatePerson(Person person, String sql) {
        MapSqlParameterSource parameter = createAndApplyFilterCustomer();
        parameter.addValue("name", person.getName(), Types.VARCHAR);
        parameter.addValue("enrolment", person.getEnrolment(), Types.VARCHAR);
        parameter.addValue("email", person.getEmail(), Types.VARCHAR);
        parameter.addValue("admissionDate", Date.valueOf(person.getAdmissionDate()), Types.DATE);
        parameter.addValue("externalId", person.getExternalId(), Types.VARCHAR);
        parameter.addValue("lastUpdateDate", Timestamp.valueOf(LocalDateTime.now()), Types.TIMESTAMP);
        parameter.addValue("personUuid", person.getUuid(), Types.VARCHAR);
        return new QueryParameterDto(sql, parameter);
    }

    protected QueryParameterDto applyFieldsPathPerson(Person person, String sql) {
        MapSqlParameterSource parameter = createAndApplyFilterCustomer();
        parameter.addValue("name", person.getName(), Types.VARCHAR);
        parameter.addValue("enrolment", person.getEnrolment(), Types.VARCHAR);
        parameter.addValue("email", person.getEmail(), Types.VARCHAR);
        parameter.addValue("lastUpdateDate", Timestamp.valueOf(LocalDateTime.now()), Types.TIMESTAMP);
        parameter.addValue("personUuid", person.getUuid(), Types.VARCHAR);
        return new QueryParameterDto(sql, parameter);
    }

    protected QueryParameterDto applyFieldsCustomer(Customer customer, String sql) {
        MapSqlParameterSource parameter = createAndApplyFilterCustomer();
        parameter.addValue("name", customer.getName(), Types.VARCHAR);
        parameter.addValue("nationalDocument", customer.getNationalDocument(), Types.VARCHAR);
        parameter.addValue("email", customer.getEmail(), Types.VARCHAR);
        parameter.addValue("customerUuid", customer.getUuid(), Types.VARCHAR);
        return new QueryParameterDto(sql, parameter);
    }

    protected QueryParameterDto applyFieldPersonUuid(UUID personUuid, String sql) {
        MapSqlParameterSource parameter = createAndApplyFilterCustomer();
        parameter.addValue("personUuid", personUuid, Types.VARCHAR);
        return new QueryParameterDto(sql, parameter);
    }
    protected QueryParameterDto applySql(String sql) {
        MapSqlParameterSource parameter = createAndApplyFilterCustomer();
        return new QueryParameterDto(sql, parameter);
    }

}