package com.upwork.camunda.service;

import com.upwork.camunda.dto.CustomerDTO;

public interface CustomerService {
    CustomerDTO getCustomerDTOById(Long id);
}
