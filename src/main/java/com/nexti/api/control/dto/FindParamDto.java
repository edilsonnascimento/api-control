package com.nexti.api.control.dto;

import lombok.*;
import org.springframework.data.domain.Pageable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindParamDto {

    private Long id;
    private Pageable pageable;
}