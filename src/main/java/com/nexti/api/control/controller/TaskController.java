package com.nexti.api.control.controller;

import com.nexti.api.control.dto.TaskDto;
import com.nexti.api.control.service.task.InsertTaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController extends BaseRestController {

    @Autowired
    private InsertTaskService insertTaskService;

    @PostMapping("/")
    public ResponseEntity<Void> create(@Valid @RequestBody TaskDto taskDto) {
        var uuid = insertTaskService.insert(taskDto);
        return responseCreated(uuid);
    }
}