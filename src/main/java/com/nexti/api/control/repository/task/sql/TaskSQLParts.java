package com.nexti.api.control.repository.task.sql;

public abstract class TaskSQLParts {

    public static final String INSERT =
             """
             INSERT INTO task (description,
                               status,
                               person_id,
                               register_date,
                               customer_id)
             VALUES(description,
                    status,
                    personId,
                    registerDate,
                    customerId)
             """;
}