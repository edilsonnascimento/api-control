package com.nexti.api.control.controller;

import com.nexti.api.control.dto.PersonDto;
import com.nexti.api.control.service.common.SecurityService;
import helper.TestIntegrationHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class PersonControllerPostIT extends TestIntegrationHelper {

    private static final String URI_BASE = "/v1/people";

    @LocalServerPort
    private int port;

    @MockBean
    private SecurityService securityService;

    @Test
    @Sql(value = "/data/controller-person-post-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/data/controller-person-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void GIVEN_Valid_Params_WHEN_Invoked_POST_Endpoint_Then_Create_And_Return_201() {

        // Given
        var name = "Pedro";
        var enrolment = "AA11111";
        var email = "pedro@apostolo.com.br";
        var admissionDate = LocalDate.of(2024, 10, 12);
        var externalId = "aaa";
        var personDto = new PersonDto(name, enrolment, email, admissionDate, externalId);
        when(securityService.getLoggedCustomerId()).thenReturn(1L);

        // When
        webTestClient
                .post().uri(URI_BASE + "/")
                .bodyValue(personDto)
                .exchange()
        // Then
                .expectHeader().location("http://localhost:" + port + "/v1/people/1")
                .expectStatus().isCreated()
                .expectBody().isEmpty();
        verify(securityService, times(1)).getLoggedCustomerId();
    }
}