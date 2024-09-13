package com.nexti.api.control.dto;

import helper.TestUnitHelper;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;

class PersonUpdateDtoTest extends TestUnitHelper {

    @Test
    void GIVEN_valid_values_WHEN_created_THEN_create_valid_PersonUpdateDTO() {

        // Given
        var name = "Pedro";
        var enrolment = "AA1121";
        var email = "pedro@apostulo.com.br";
        var admissionDate = LocalDate.of(2024, 10, 12);
        var externalId = "12212AAAA";
        var expected = new PersonUpdateDto(name,
                                         enrolment,
                                         email,
                                         admissionDate,
                                         externalId);

        // When
        var actual = new PersonUpdateDto(name,
                                           enrolment,
                                           email,
                                           admissionDate,
                                           externalId);
        // Then
        assertThat(actual).isEqualTo(expected);
    }
}