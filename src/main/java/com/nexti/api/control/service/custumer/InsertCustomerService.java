package com.nexti.api.control.service.custumer;

import com.nexti.api.control.dto.*;
import com.nexti.api.control.repository.person.impl.PersonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsertCustomerService {

    @Autowired
    private MountQueryParameterCustomerService mountQueryParameterCustomerService;
    @Autowired
    private PersonRepositoryImpl peopleRepository;

    public Long insert(CustomerDto customerDto) {
        var queryParameterDto = mountQueryParameterCustomerService.insertCustomer(customerDto);
        return peopleRepository.create(queryParameterDto);
    }
}