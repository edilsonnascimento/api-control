package com.nexti.api.control.dto;

import helper.TestUnitHelper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class PersonDtoTest extends TestUnitHelper {

    @Test
    void GIVEN_valid_values_WHEN_created_Then_create_valid_PersonDTO() {

        // Given
        var name = "Pedro";
        var enrolment = "AA1121";
        var email = "pedro@apostulo.com.br";
        var admissionDate = LocalDate.of(2024, 10, 12);
        var externalId = "12212AAAA";
        var expected = new PersonDto(name, enrolment, email, admissionDate, externalId);

        // When
        var actual = new PersonDto(name, enrolment, email, admissionDate, externalId);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}