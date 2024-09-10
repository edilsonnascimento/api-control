package com.nexti.api.control.service.common;

import com.nexti.api.control.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.*;

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
        return new QueryParameterDto(sql, parameter);
    }

    protected QueryParameterDto applyFieldsClient(CustomerDto clientDto, String sql) {
        MapSqlParameterSource parameter = createAndApplyFilterCustomer();
        parameter.addValue("name", clientDto.getName(), Types.VARCHAR);
        parameter.addValue("nationalDocument", clientDto.getNationalDocument(), Types.VARCHAR);
        parameter.addValue("email", clientDto.getEmail(), Types.VARCHAR);
        return new QueryParameterDto(sql, parameter);
    }

}