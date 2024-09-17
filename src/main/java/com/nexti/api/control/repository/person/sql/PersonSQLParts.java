package com.nexti.api.control.repository.person.sql;

public class PersonSQLParts {

    public static final String INSERT =
            """
            INSERT INTO person (name,
                                enrolment,
                                email,
                                admission_date,
                                external_id,
                                customer_id,
                                uuid)
            VALUES (:name,
                    :enrolment,
                    :email,
                    :admissionDate,
                    :externalId,
                    :customerId,
                    :personUuid);
            """;

    public static final String FIND =
            """
                    SELECT
                          per.id AS id,
                          per.name AS name,
                          per.enrolment AS enrolment,
                          per.email AS email,
                          per.admission_date AS admissionDate,
                          per.external_id AS externalId,
                          per.register_date AS registerDate,
                          per.last_update_date AS lastUpdateDate,
                          per.customer_id AS customerId,
                          per.uuid
                    FROM  person AS per
                    WHERE per.customer_id = :customerId AND
                          per.uuid = :personUuid;
                    """;

    public static final String FIND_ALL =
                    """
                    SELECT
                          per.id AS id,
                          per.name AS name,
                          per.enrolment AS enrolment,
                          per.email AS email,
                          per.admission_date AS admissionDate,
                          per.external_id AS externalId,
                          per.register_date AS registerDate,
                          per.last_update_date AS lastUpdateDate,
                          per.customer_id AS customerId,
                          per.uuid
                    FROM  person AS per
                    WHERE per.customer_id = :customerId;
                    """;

    public static final String UPDATE =
                    """
                    UPDATE person SET name = :name,
                                      enrolment = :enrolment,
                                      email = :email,
                                      admission_date = :admissionDate,
                                      external_id = :externalId
                    WHERE uuid = :personUuid;
                    """;

    public static final String PATCH =
                    """
                    UPDATE person SET name = :name,
                                      enrolment = :enrolment,
                                      last_update_date = :lastUpdateDate
                    WHERE uuid = :personUuid;
                    """;

    public static final String DELETE =
                    """
                    DELETE
                    FROM person
                    WHERE uuid = :personUuid
                    """;

    public static final String FIND_TASKS =
            """
            SELECT
                  per.name AS name,
                  per.enrolment AS enrolment,
                  tas.description,
                  tas.status,
                  tas.uuid
            FROM person AS per
                JOIN task AS tas ON tas.person_id = per.id
            WHERE per.customer_id = :customerId AND
                  per.uuid = :personUuid
            """;
}