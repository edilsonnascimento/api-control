package com.nexti.api.control.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
        private String name;
        private String nationalDocument;
        private String email;
}