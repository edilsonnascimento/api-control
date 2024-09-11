package com.nexti.api.control.dto;

import lombok.*;

import java.time.*;

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
}