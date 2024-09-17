package com.nexti.api.control.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
        @NotBlank
        private String name;
        @NotBlank
        private String nationalDocument;
        @NotBlank
        private String email;
}