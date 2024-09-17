package com.nexti.api.control.dto;


import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonTasksResponseDto {
    private String name;
    private String enrolment;
    private List<TaskResponseDto> tasks;
}