package com.nexti.api.control.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    @NotBlank
    private String description;
    @NotNull
    private UUID personUuid;
}