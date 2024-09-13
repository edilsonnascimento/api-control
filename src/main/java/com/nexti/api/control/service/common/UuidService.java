package com.nexti.api.control.service.common;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UuidService {

    public UUID random() {
        return UUID.randomUUID();
    }
}