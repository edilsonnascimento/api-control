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
    private LocalDate admissionDate;
    private LocalDateTime registerDate;
    private Long customerId;
}