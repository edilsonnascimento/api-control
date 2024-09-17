package com.nexti.api.control.controller;

import com.nexti.api.control.dto.TaskDto;
import com.nexti.api.control.service.task.InsertTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController extends BaseRestController {

    @Autowired
    private InsertTaskService findTaskService;

    @PostMapping("/")
    public ResponseEntity<Void> create(@RequestBody TaskDto taskDto) {
        var uuid = findTaskService.insert(taskDto);
        return responseCreated(uuid);
    }
}