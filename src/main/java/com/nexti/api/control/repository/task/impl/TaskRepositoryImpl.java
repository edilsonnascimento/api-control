package com.nexti.api.control.repository.task.impl;

import com.nexti.api.control.domain.Task;
import com.nexti.api.control.enums.TaskStatus;
import com.nexti.api.control.repository.common.BaseRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.*;

@Repository
public class TaskRepositoryImpl extends BaseRepository<Task> {

    @Override
    protected RowMapper<Task> returnRowMapper() {
        return (result, rowNum) -> Task.builder()
                .id(result.getLong("id"))
                .description(result.getString("description"))
                .status(TaskStatus.valueOf(result.getString("status")))
                .personId(result.getLong("personId"))
                .registerDate(result.getTimestamp("registerDate").toLocalDateTime())
                .customerId(result.getLong("customerId"))
                .build();
    }
}
