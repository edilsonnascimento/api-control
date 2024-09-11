package com.nexti.api.control.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonPatchDto {
        private String name;
        private String enrolment;
        @Email
        private String email;
}