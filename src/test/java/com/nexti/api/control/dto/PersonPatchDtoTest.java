package com.nexti.api.control.dto;

import helper.TestUnitHelper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PersonPatchDtoTest extends TestUnitHelper {

    @Test
    void GIVEN_valid_values_WHEN_created_THEN_create_valid_PersonPatchDTO() {

        // Given
        var name = "Pedro";
        var enrolment = "AA1121";
        var expected = new PersonPatchDto(name, enrolment);

        // When
        var actual = new PersonPatchDto(name, enrolment);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}