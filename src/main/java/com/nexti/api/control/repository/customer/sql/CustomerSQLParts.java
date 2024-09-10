package com.nexti.api.control.repository.customer.sql;

public class CustomerSQLParts {

    public static final String INSERT =
            """
            INSERT INTO customer (name,
                                  national_document,
                                  email)
            VALUES (:name,
                    :nationalDocument,
                    :email)
            """;
}