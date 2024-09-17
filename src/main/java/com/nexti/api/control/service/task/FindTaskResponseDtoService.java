package com.nexti.api.control.service.task;

import com.nexti.api.control.domain.Task;
import com.nexti.api.control.dto.TaskResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
public class FindTaskResponseDtoService {

    @Autowired
    private FindTaskService findTaskService;

    public List<TaskResponseDto> find(Long personId) {
        var tasks = findTaskService.findPersonId(personId);
        return mapResponseDtos(tasks);
    }

    private List<TaskResponseDto> mapResponseDtos(List<Task> tasks) {
        return tasks.stream()
                .map(FindTaskResponseDtoService::applyMap)
                .collect(toList());
    }

    private static TaskResponseDto applyMap(Task task) {
        return TaskResponseDto.builder()
                .description(task.getDescription())
                .status(task.getStatus())
                .uuid(task.getUuid())
                .build();
    }
}