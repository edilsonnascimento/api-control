package com.nexti.api.control.controller;

import com.nexti.api.control.service.common.SecurityService;
import helper.TestIntegrationHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

import static org.mockito.Mockito.*;

class PersonControllerDeletePersonIT extends TestIntegrationHelper {

    private static final String URI_BASE = "/v1/people";

    @MockBean
    private SecurityService securityService;

    @Test
    @Sql(value = "/data/controller-person-put-patch-delete-person-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/data/controller-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void GIVEN_Valid_Params_WHEN_Invoked_DELETE_Endpoint_THEN_Return_Body_empty_And_204() {

        // Given
        when(securityService.getLoggedCustomerId()).thenReturn(1L);

        // When
        webTestClient
                .delete().uri(URI_BASE + "/adc48803-71d9-11ef-8bff-0242ac110002")
                .exchange()
        // Then
                .expectStatus().isNoContent()
                .expectBody().isEmpty();
        verify(securityService, times(1)).getLoggedCustomerId();
    }
}