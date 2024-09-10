package com.nexti.api.control.dto;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public record QueryParameterDto(
        String sql,
        MapSqlParameterSource parameter) {
}