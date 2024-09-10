package com.nexti.api.control.controller;

import com.nexti.api.control.dto.*;
import com.nexti.api.control.service.custumer.InsertCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController extends BaseRestController {

    @Autowired
    private InsertCustomerService insertCustomerService;

    @PostMapping("/")
    public ResponseEntity<Void> create(@RequestBody CustomerDto customerDto) {
        var customerId = insertCustomerService.insert(customerDto);
        return responseCreated(customerId);
    }
}