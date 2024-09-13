package com.nexti.api.control.dto;

import lombok.*;

import java.time.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseDto {
    private String name;
    private String enrolment;
    private String email;
    private LocalDate admissionDate;
    private String externalId;
    private LocalDateTime registerDate;
    private LocalDateTime lastUpdateDate;
    private UUID uuid;
}