package com.nexti.api.control.repository.common;

import com.nexti.api.control.dto.QueryParameterDto;
import com.nexti.api.control.exception.*;
import com.nexti.api.control.util.NextiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.util.*;

public abstract class BaseRepository<T> {

    @Autowired
    protected NamedParameterJdbcTemplate jdbcTemplate;

    public Long create(QueryParameterDto queryParameterDto) {
        var keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(queryParameterDto.sql(), queryParameterDto.parameter(), keyHolder, new String[]{"id"});
        if (Objects.isNull(keyHolder.getKey().longValue()))
            throw new BusinessException("ERROR_INSERT_OBJECT");
        return keyHolder.getKey().longValue();
    }

    public void update(QueryParameterDto queryParameterDto) {
        jdbcTemplate.update(queryParameterDto.sql(), queryParameterDto.parameter());
    }

    public Optional<List<T>> find(QueryParameterDto queryParameterDto) {
        List<T> result = jdbcTemplate.query(queryParameterDto.sql(),
                                            queryParameterDto.parameter(),
                                            returnRowMapper());
        return NextiUtil.arrayIsEmpty(result) ? Optional.empty() : Optional.of(result);
    }

    protected abstract RowMapper<T> returnRowMapper();

    public Long findByTotalRecord(QueryParameterDto queryParameterDto) {
        return jdbcTemplate.queryForObject(queryParameterDto.sql(), queryParameterDto.parameter(), Long.class);
    }

    public void delete(QueryParameterDto queryParameterDto) {
        int rowsAffected = jdbcTemplate.update(queryParameterDto.sql(), queryParameterDto.parameter());
        if (rowsAffected == 0)
            throw new NotFoundException("ID_PERSON_NOT_FOUND");
    }
}