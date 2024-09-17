package com.nexti.api.control.repository.task.sql;

public abstract class TaskSQLParts {

    public static final String INSERT =
             """
             INSERT INTO task (description,
                               status,
                               person_id,
                               customer_id,
                               uuid)
             VALUES(:description,
                    :status,
                    :personId,
                    :customerId,
                    :taskUuid)
             """;
    public static final String FIND_PERSON_TASKS =
            """
            SELECT
                  id AS id,
                  description AS description,
                  status AS status,
                  person_id AS personId,
                  register_date AS registerDate,
                  customer_id AS customerId,
                  uuid AS uuid
            FROM task AS tas
            WHERE tas.customer_id = :customerId AND
                  tas.person_id = :personId
            """;
}