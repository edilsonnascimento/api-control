package com.nexti.api.control.repository.common;

import com.nexti.api.control.dto.QueryParameterDto;
import com.nexti.api.control.exception.NotFoundException;
import com.nexti.api.control.util.ControlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.*;

public abstract class BaseRepository<T> {

    @Autowired
    protected NamedParameterJdbcTemplate jdbcTemplate;

    public void update(QueryParameterDto queryParameterDto) {
        jdbcTemplate.update(queryParameterDto.sql(), queryParameterDto.parameter());
    }

    public Optional<List<T>> find(QueryParameterDto queryParameterDto) {
        List<T> result = jdbcTemplate.query(queryParameterDto.sql(),
                                            queryParameterDto.parameter(),
                                            returnRowMapper());
        return ControlUtil.arrayIsEmpty(result) ? Optional.empty() : Optional.of(result);
    }

    protected abstract RowMapper<T> returnRowMapper();

    public void delete(QueryParameterDto queryParameterDto) {
        int rowsAffected = jdbcTemplate.update(queryParameterDto.sql(), queryParameterDto.parameter());
        if (rowsAffected == 0)
            throw new NotFoundException("ID_PERSON_NOT_FOUND");
    }
}