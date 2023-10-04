package com.upwork.camunda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderInformationDTO {
    private OrderDTO order;
    private CustomerDTO customer;
    private PaymentDTO payment;
    private TransactionsDTO transactions;
}
