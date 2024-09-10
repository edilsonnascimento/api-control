package com.nexti.api.control.repository.person.sql;

public class PersonSQLParts {

    public static final String INSERT =
            """
            INSERT INTO person (name,
                                enrolment,
                                email,
                                admission_date,
                                customer_id)
            VALUES (:name,
                    :enrolment,
                    :email,
                    :admissionDate,
                    :customerId);
            """;
}
