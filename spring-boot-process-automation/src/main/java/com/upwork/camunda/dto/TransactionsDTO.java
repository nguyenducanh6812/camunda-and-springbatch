package com.upwork.camunda.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionsDTO {
    private Long id;
    private PaymentDTO payment;
    private Date date;
    private Boolean moneyLaundering;
}