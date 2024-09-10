package com.nexti.api.control.domain;

import lombok.*;

import java.time.LocalDateTime;

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
}