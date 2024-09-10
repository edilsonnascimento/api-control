package com.nexti.api.control.controller;

import com.nexti.api.control.domain.Person;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

public class BaseRestController {

    protected PageImpl<Person> responseOk(Pageable pageable, List<Person> people) {
        return new PageImpl<>(people, pageable, people.size());
    }

    protected ResponseEntity<Void> responseCreated(Long id) {
        var location = fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }
}