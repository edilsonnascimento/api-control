package com.nexti.api.control.service.task;

import com.nexti.api.control.dto.*;
import com.nexti.api.control.repository.task.impl.TaskRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindTaskService {

    @Autowired
    private MountQueryParameterTaskServer mountQueryParameterTaskServer;
    @Autowired
    private TaskRepositoryImpl taskRepository;

    public Long insert(TaskDto taskDto) {
        var queryParameterDto = mountQueryParameterTaskServer.insertTask(taskDto);
        return taskRepository.create(queryParameterDto);
    }
}