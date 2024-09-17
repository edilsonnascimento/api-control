package com.nexti.api.control.controller;

import com.nexti.api.control.service.common.SecurityService;
import helper.TestIntegrationHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import static org.mockito.Mockito.*;

class PersonControllerGetPersonIT extends TestIntegrationHelper {

    private static final String URI_BASE = "/v1/people";

    @LocalServerPort
    private int port;

    @MockBean
    private SecurityService securityService;

    @Test
    @Sql(value = "/data/controller-person-get-person-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/data/controller-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void GIVEN_Valid_Params_WHEN_Invoked_GET_Endpoint_THEN_Return_Person_And_200() {

        // Given
        when(securityService.getLoggedCustomerId()).thenReturn(1L);

        // When
        webTestClient
                .get().uri(URI_BASE + "/adc494fd-71d9-11ef-8bff-0242ac110002")
                .exchange()
        // Then
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.name").isEqualTo("PEDRO")
                .jsonPath("$.enrolment").isEqualTo("1234ABC")
                .jsonPath("$.email").isEqualTo("pedro@apostolo.com")
                .jsonPath("$.admissionDate").isEqualTo("1980-10-10")
                .jsonPath("$.externalId").isEqualTo("UURSL9009")
                .jsonPath("$.registerDate").isNotEmpty()
                .jsonPath("$.lastUpdateDate").isNotEmpty();
        verify(securityService, times(1)).getLoggedCustomerId();
    }

    @Test
    @Sql(value = "/data/controller-person-get-people-task-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/data/controller-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void GIVEN_Valid_Request_WHEN_Invoked_GET_Endpoint_THEN_Return_People_Tasks_And_200() {

        // Given
        when(securityService.getLoggedCustomerId()).thenReturn(1L);

        // When
        webTestClient
                .get().uri(URI_BASE + "/adc48803-71d9-11ef-8bff-0242ac110002/tasks")
                .exchange()
                // Then
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.name").isEqualTo("PEDRO")
                .jsonPath("$.enrolment").isEqualTo("1234ABC")
                .jsonPath("$.tasks[0].description").isEqualTo("Faith Helle")
                .jsonPath("$.tasks[0].status").isEqualTo("ACTIVE")
                .jsonPath("$.tasks[0].uuid").isEqualTo("ebfc9ffa-4997-436f-8733-08b7d08f67ae");

        verify(securityService, times(2)).getLoggedCustomerId();
    }


    @Test
    @Sql(value = "/data/controller-person-get-people-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/data/controller-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void GIVEN_Valid_Request_WHEN_Invoked_GET_Endpoint_THEN_Return_People_And_200() {

        // Given
        when(securityService.getLoggedCustomerId()).thenReturn(1L);
        var expectedSize = 4;

        // When
        webTestClient
                .get().uri(URI_BASE + "/")
                .exchange()
        // Then
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.content.length()").isEqualTo(expectedSize)
                .jsonPath("$.content[0].name").isEqualTo("PEDRO")
                .jsonPath("$.content[0].enrolment").isEqualTo("1ABC")
                .jsonPath("$.content[0].email").isEqualTo("pedro@apostolo.com")
                .jsonPath("$.content[0].admissionDate").isEqualTo("1980-10-10")
                .jsonPath("$.content[0].externalId").isEqualTo("UURSL9009")
                .jsonPath("$.content[0].registerDate").isNotEmpty()
                .jsonPath("$.content[0].lastUpdateDate").isNotEmpty();
        verify(securityService, times(1)).getLoggedCustomerId();
    }

    @Test
    void SHOULD_Throw_Exception_WHEN_Invoked_GET_Endpoint_With_Invalid_Param_THEN_Return_Message_Error_And_404() {

        // Given
        when(securityService.getLoggedCustomerId()).thenReturn(1L);
        var request = URI_BASE + "/" + "adc48803-71d9-11ef-8bff-0242ac110002";

        // When
        webTestClient
                .get().uri(request)
                .exchange()
        // Then
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.type").isEqualTo("about:blank")
                .jsonPath("$.title").isEqualTo("Not Found")
                .jsonPath("$.status").isEqualTo(404)
                .jsonPath("$.detail").isEqualTo("PERSON_NOT_FOUND")
                .jsonPath("$.instance").isEqualTo(request)
                .jsonPath("$.timestamp").isNotEmpty();
        verify(securityService, times(1)).getLoggedCustomerId();
    }
}