package com.upwork.camunda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentDTO {
    private Long id;
    private OrderDTO orderDTO;
    private BigDecimal amount;
}
