package com.nexti.api.control.controller;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

public class BaseRestController {

    protected ResponseEntity<Void> responseCreated(UUID uuid) {
        var location = fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(uuid)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    protected ResponseEntity<Void> responseNoContent() {
        return ResponseEntity.noContent().build();
    }
}