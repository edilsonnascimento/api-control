package com.nexti.api.control.dto;

import com.nexti.api.control.enums.TaskStatus;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDto {
    private String description;
    private TaskStatus status;
    private UUID uuid;
}