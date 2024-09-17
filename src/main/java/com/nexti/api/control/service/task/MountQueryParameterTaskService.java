package com.nexti.api.control.service.task;

import com.nexti.api.control.domain.Task;
import com.nexti.api.control.dto.*;
import com.nexti.api.control.repository.task.sql.TaskSQLParts;
import com.nexti.api.control.service.common.MountQueryParameter;
import org.springframework.stereotype.Service;

@Service
public class MountQueryParameterTaskService extends MountQueryParameter {

    public QueryParameterDto insertTask(Task task) {
        return applyFields(task, TaskSQLParts.INSERT);
    }

    public QueryParameterDto findPersonTasks(Long personId) {
        return applyPersonId(personId, TaskSQLParts.FIND_PERSON_TASKS);
    }
}