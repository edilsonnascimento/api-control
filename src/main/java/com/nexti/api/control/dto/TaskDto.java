package com.nexti.api.control.dto;

import com.nexti.api.control.enums.TaskStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private String description;
    private TaskStatus status;
    private Long personId;
    private LocalDateTime registerDate;
}