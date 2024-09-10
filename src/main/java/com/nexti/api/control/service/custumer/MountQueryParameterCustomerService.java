package com.nexti.api.control.service.custumer;

import com.nexti.api.control.dto.*;
import com.nexti.api.control.repository.customer.sql.CustomerSQLParts;
import com.nexti.api.control.service.common.MountQueryParameter;
import org.springframework.stereotype.Service;

@Service
public class MountQueryParameterCustomerService extends MountQueryParameter {

    public QueryParameterDto insertCustomer(CustomerDto customerDto) {
        String sql = CustomerSQLParts.INSERT;
        return applyFieldsClient(customerDto, sql);
    }
}