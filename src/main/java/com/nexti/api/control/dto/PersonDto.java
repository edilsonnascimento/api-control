package com.nexti.api.control.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
        @NotBlank
        private String name;
        @NotBlank
        private String enrolment;
        @Email
        private String email;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate admissionDate;
        private Long customerId;
}