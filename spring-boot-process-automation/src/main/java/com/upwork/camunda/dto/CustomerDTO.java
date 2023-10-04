package com.upwork.camunda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDTO {
    private Long id;
    private String name;
    private String address;
    private String credit;
}
