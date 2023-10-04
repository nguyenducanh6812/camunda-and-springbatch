package com.upwork.camunda.service;

import com.upwork.camunda.dto.OrderInformationDTO;

public interface OrderInformationService {
    OrderInformationDTO getOrderInformationByOrderId(Long id);
}
