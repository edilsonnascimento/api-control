package com.nexti.api.control.domain;

import com.nexti.api.control.enums.TaskStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private Long id;
    private String description;
    private TaskStatus status;
    private Long personId;
    private LocalDateTime registerDate;
    private Long customerId;
}