package com.nexti.api.control.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonPatchDto {
        private String name;
        private String enrolment;
}