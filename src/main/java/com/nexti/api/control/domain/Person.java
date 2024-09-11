package com.nexti.api.control.domain;

import lombok.*;

import java.time.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Long id;
    private String name;
    private String enrolment;
    private String email;
    private LocalDate admissionDate;
    private String externalId;
    private Long customerId;
    private LocalDateTime registerDate;
    private LocalDateTime lastUpdateDate;
}