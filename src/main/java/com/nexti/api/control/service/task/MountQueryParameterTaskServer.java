package com.nexti.api.control.service.task;

import com.nexti.api.control.dto.*;
import com.nexti.api.control.repository.task.sql.TaskSQLParts;
import com.nexti.api.control.service.common.MountQueryParameter;
import org.springframework.stereotype.Service;

@Service
public class MountQueryParameterTaskServer extends MountQueryParameter {

    public QueryParameterDto insertTask(TaskDto taskDto) {
        String sql = TaskSQLParts.INSERT;
        return applyFields(taskDto, sql);
    }
}