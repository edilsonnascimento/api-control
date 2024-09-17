package com.nexti.api.control.service.task;

import com.nexti.api.control.domain.Task;
import com.nexti.api.control.repository.task.impl.TaskRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FindTaskService {

    @Autowired
    private MountQueryParameterTaskService mountQueryParameterTaskService;
    @Autowired
    private TaskRepositoryImpl taskRepository;

    public List<Task> findPersonId(Long personId) {
        var queryParameterDto = mountQueryParameterTaskService.findPersonTasks(personId);
        return taskRepository.find(queryParameterDto).orElse(new ArrayList<>());
    }
}
