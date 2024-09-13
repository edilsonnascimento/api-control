package com.nexti.api.control.service.custumer;

import com.nexti.api.control.domain.Customer;
import com.nexti.api.control.dto.CustomerDto;
import com.nexti.api.control.repository.person.impl.PersonRepositoryImpl;
import com.nexti.api.control.service.common.UuidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InsertCustomerService {

    @Autowired
    private MountQueryParameterCustomerService mountQueryParameterCustomerService;
    @Autowired
    private PersonRepositoryImpl peopleRepository;
    @Autowired
    private UuidService uuidService;

    public UUID insert(CustomerDto customerDto) {
        var customer = getCustomer(customerDto);
        var queryParameterDto = mountQueryParameterCustomerService.insertCustomer(customer);
        peopleRepository.update(queryParameterDto);
        return customer.getUuid();
    }

    private Customer getCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .name(customerDto.getName())
                .nationalDocument(customerDto.getNationalDocument())
                .email(customerDto.getEmail())
                .uuid(uuidService.random())
                .build();
    }
}