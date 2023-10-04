package com.upwork.camunda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {
    private Long id;
    private Date date;
    private int status;
    private CustomerDTO customerDTO;

    public Long getCustomerId() {
        return customerDTO.getId();
    }
}
