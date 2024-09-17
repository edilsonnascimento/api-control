package com.nexti.api.control.service.task;

import com.nexti.api.control.domain.Task;
import com.nexti.api.control.dto.*;
import com.nexti.api.control.enums.TaskStatus;
import com.nexti.api.control.repository.task.impl.TaskRepositoryImpl;
import com.nexti.api.control.service.common.UuidService;
import com.nexti.api.control.service.person.FindPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InsertTaskService {

    @Autowired
    private MountQueryParameterTaskService mountQueryParameterTaskServer;
    @Autowired
    private TaskRepositoryImpl taskRepository;
    @Autowired
    private UuidService uuidService;
    @Autowired
    private FindPersonService findPersonService;

    public UUID insert(TaskDto taskDto) {
        var task = getTask(taskDto);
        var queryParameterDto = mountQueryParameterTaskServer.insertTask(task);
        taskRepository.update(queryParameterDto);
        return task.getUuid();
    }

    private Task getTask(TaskDto taskDto) {
        var person = findPersonService.find(taskDto.getPersonUuid());
        return Task.builder()
                .description(taskDto.getDescription())
                .personId(person.getId())
                .status(TaskStatus.ACTIVE)
                .uuid(uuidService.random())
                .build();

    }
}