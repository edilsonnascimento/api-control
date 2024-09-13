package com.nexti.api.control.service.custumer;

import com.nexti.api.control.domain.Customer;
import com.nexti.api.control.dto.QueryParameterDto;
import com.nexti.api.control.repository.customer.sql.CustomerSQLParts;
import com.nexti.api.control.service.common.MountQueryParameter;
import org.springframework.stereotype.Service;

@Service
public class MountQueryParameterCustomerService extends MountQueryParameter {

    public QueryParameterDto insertCustomer(Customer customer) {
        String sql = CustomerSQLParts.INSERT;
        return applyFieldsCustomer(customer, sql);
    }
}