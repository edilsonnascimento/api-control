package com.nexti.api.control.repository.person.sql;

public class PersonSQLParts {

    public static final String INSERT =
            """
            INSERT INTO person (name,
                                enrolment,
                                email,
                                admission_date,
                                external_id,
                                customer_id)
            VALUES (:name,
                    :enrolment,
                    :email,
                    :admissionDate,
                    :externalId,
                    :customerId);
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
                          per.customer_id AS customerId
                    FROM  person AS per
                    WHERE per.customer_id = :customerId AND
                          per.id = :personId;
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
                          per.customer_id AS customerId
                    FROM  person AS per
                    WHERE per.customer_id = :customerId;
                    """;

    public static final String UPDATE =
                    """
                    UPDATE person SET name = :name,
                                      enrolment = :enrolment,
                                      email = :email,
                                      admission_date = :admissionDate,
                                      external_id = :externalId,
                                      last_update_date = :lastUpdateDate
                    WHERE id = :personId;
                    """;

    public static final String PATCH =
                    """
                    UPDATE person SET name = :name,
                                      enrolment = :enrolment,
                                      last_update_date = :lastUpdateDate
                    WHERE id = :personId;
                    """;

    public static final String DELETE =
                    """
                    DELETE
                    FROM person AS per
                    WHERE per.id = :personId
                    """;
}