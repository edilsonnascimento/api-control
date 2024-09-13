package com.nexti.api.control.controller;

import com.nexti.api.control.dto.PersonPatchDto;
import com.nexti.api.control.service.common.SecurityService;
import helper.TestIntegrationHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

import static org.mockito.Mockito.*;

class PersonControllerPatchPersonIT extends TestIntegrationHelper {

    private static final String URI_BASE = "/v1/people";

    @MockBean
    private SecurityService securityService;

    @Test
    @Sql(value = "/data/controller-person-put-patch-delete-person-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/data/controller-person-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void GIVEN_Valid_Params_WHEN_Invoked_PATCH_Endpoint_THEN_Return_Empty_Body_And_204() {

        // Given
        var name = "Cefaz";
        var enrolment = "BB11111";
        var personPatchDto = new PersonPatchDto(name, enrolment);
        when(securityService.getLoggedCustomerId()).thenReturn(1L);

        // When
        webTestClient
                .patch().uri(URI_BASE + "/adc48803-71d9-11ef-8bff-0242ac110002")
                .bodyValue(personPatchDto)
                .exchange()
        // Then
                .expectStatus().isNoContent()
                .expectBody().isEmpty();
        verify(securityService, times(2)).getLoggedCustomerId();
    }
}