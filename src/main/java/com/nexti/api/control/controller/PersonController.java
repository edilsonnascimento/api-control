package com.nexti.api.control.controller;

import com.nexti.api.control.dto.PersonDto;
import com.nexti.api.control.service.person.InsertPersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/people")
public class PersonController extends BaseRestController {

    @Autowired
    private InsertPersonService insertPersonService;

    @PostMapping("/")
    public ResponseEntity<Void> create(@Valid @RequestBody PersonDto personDto) {
        var personId = insertPersonService.insert(personDto);
        return responseCreated(personId);
    }
}