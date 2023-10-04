package com.upwork.camunda.service;

import com.upwork.camunda.dto.TransactionsDTO;
public interface TransactionsService {
    TransactionsDTO getTransactionsDTOByPaymentId(Long id);
}
