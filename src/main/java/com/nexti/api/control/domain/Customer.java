package com.nexti.api.control.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private String nationalDocument;
    private String email;
    private LocalDateTime registerDate;
    private UUID uuid;
}