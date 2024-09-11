package com.nexti.api.control.controller;

import org.springframework.http.ResponseEntity;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

public class BaseRestController {

    protected ResponseEntity<Void> responseCreated(Long id) {
        var location = fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    protected ResponseEntity<Void> responseNoContent() {
        return ResponseEntity.noContent().build();
    }
}