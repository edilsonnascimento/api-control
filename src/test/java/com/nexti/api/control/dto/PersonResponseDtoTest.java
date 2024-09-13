package com.nexti.api.control.dto;

import helper.TestUnitHelper;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PersonResponseDtoTest extends TestUnitHelper {

    @Test
    void GIVEN_valid_values_WHEN_created_THEN_create_valid_PersonResponseDTO() {

        // Given
        var name = "Pedro";
        var enrolment = "AA1121";
        var email = "pedro@apostulo.com.br";
        var admissionDate = LocalDate.of(2024, 10, 12);
        var externalId = "12212AAAA";
        var registerDate = LocalDateTime.of(2024, 10, 12, 13, 50, 50);
        var lastUpdateDate = LocalDateTime.of(2024, 11, 12, 11, 29, 10);
        var uuid = UUID.randomUUID();
        var expected = new PersonResponseDto(name,
                                             enrolment,
                                             email,
                                             admissionDate,
                                             externalId,
                                             registerDate,
                                             lastUpdateDate,
                                             uuid);
        // When
        var actual = new PersonResponseDto(name,
                                             enrolment,
                                             email,
                                             admissionDate,
                                             externalId,
                                             registerDate,
                                             lastUpdateDate,
                                             uuid);
        // Then
        assertThat(actual).isEqualTo(expected);
    }
}