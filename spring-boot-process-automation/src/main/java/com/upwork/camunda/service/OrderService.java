package com.upwork.camunda.service;

import com.upwork.camunda.dto.OrderDTO;

public interface OrderService {
    OrderDTO getOrderDTOById(Long id);
}
