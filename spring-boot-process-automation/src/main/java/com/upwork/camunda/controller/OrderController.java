package com.upwork.camunda.controller;

import com.upwork.camunda.dto.OrderDTO;
import com.upwork.camunda.service.OrderServiceImpl;
import com.upwork.camunda.workflow.service.CamundaWorkflowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    private final CamundaWorkflowService service;
    private final OrderServiceImpl orderServiceImpl;

    @Autowired
    public OrderController(CamundaWorkflowService service, OrderServiceImpl orderServiceImpl) {
        this.service = service;
        this.orderServiceImpl = orderServiceImpl;
    }

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderDTO orderDTO) {
        service.startWorkflow(orderDTO);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public List<Long> getListOrderIds() {
        return orderServiceImpl.getAllOrderIds();
    }
}
