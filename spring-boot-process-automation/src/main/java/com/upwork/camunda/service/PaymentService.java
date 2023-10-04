package com.upwork.camunda.service;

import com.upwork.camunda.dto.PaymentDTO;

public interface PaymentService {
    PaymentDTO getPaymentDTOByOrderId(Long id);
}
