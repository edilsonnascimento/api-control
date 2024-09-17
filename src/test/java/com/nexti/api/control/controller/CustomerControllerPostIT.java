package com.nexti.api.control.controller;

import com.nexti.api.control.dto.CustomerDto;
import com.nexti.api.control.service.common.*;
import helper.TestIntegrationHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

import static org.mockito.Mockito.*;

class CustomerControllerPostIT extends TestIntegrationHelper {

    private static final String URI_BASE = "/v1/customers";

    @LocalServerPort
    private int port;

    @MockBean
    private SecurityService securityService;
    @MockBean
    private UuidService uuidService;

    @Test
    @Sql(value = "/data/controller-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void GIVEN_Valid_Params_WHEN_Invoked_POST_Endpoint_Then_Create_And_Return_201() {

        // Given
        var customerDto = CustomerDto.builder()
                        .name("Aeroporto Numenor")
                        .nationalDocument("12445678901")
                        .email("legolas@example.com")
                        .build();
        when(securityService.getLoggedCustomerId()).thenReturn(1L);
        var uuid = "adc494fd-71d9-11ef-8bff-0242ac110002";
        var uuidRandom = UUID.fromString(uuid);
        when(uuidService.random()).thenReturn(uuidRandom);

        // When
        webTestClient
                .post().uri(URI_BASE + "/")
                .bodyValue(customerDto)
                .exchange()
        // Then
                .expectHeader().location("http://localhost:" + port + "/v1/customers/" + uuid)
                .expectStatus().isCreated()
                .expectBody().isEmpty();
        verify(securityService, times(1)).getLoggedCustomerId();
        verify(uuidService, times(1)).random();
    }
}
