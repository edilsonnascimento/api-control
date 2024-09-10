package com.nexti.api.control.repository.common;

import com.nexti.api.control.dto.QueryParameterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public abstract class BaseRepository<T> {

    @Autowired
    protected NamedParameterJdbcTemplate jdbcTemplate;

    public Long insert(QueryParameterDto queryParameterDto) {
        var id = jdbcTemplate.update(queryParameterDto.sql(), queryParameterDto.parameter());
        return Long.valueOf(id);
    }

    public List<T> find(QueryParameterDto queryParameterDto) {
        return jdbcTemplate.query(
                queryParameterDto.sql(),
                queryParameterDto.parameter(),
                returnRowMapper());
    }

    protected abstract RowMapper<T> returnRowMapper();

    public Long findByTotalRecord(QueryParameterDto queryParameterDto) {
        return jdbcTemplate.queryForObject(queryParameterDto.sql(), queryParameterDto.parameter(), Long.class);
    }
}