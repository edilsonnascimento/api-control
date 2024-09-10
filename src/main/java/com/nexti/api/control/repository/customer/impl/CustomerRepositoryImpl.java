package com.nexti.api.control.repository.customer.impl;

import com.nexti.api.control.domain.Customer;
import com.nexti.api.control.repository.common.BaseRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl extends BaseRepository<Customer>{

        @Override
        protected RowMapper<Customer> returnRowMapper() {
            return (result, rowNum) -> Customer.builder()
                    .id(result.getLong("id"))
                    .name(result.getString("name"))
                    .nationalDocument(result.getString("nationalDocument"))
                    .email(result.getString("email"))
                    .build();
        }
}
