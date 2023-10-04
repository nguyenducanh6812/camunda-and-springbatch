package com.upwork.camunda.service;

import com.upwork.camunda.dto.*;
import com.upwork.camunda.model.Order;
import com.upwork.camunda.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class OrderInformationServiceImpl implements OrderInformationService {
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final CustomerService customerService;
    private final PaymentService paymentService;
    private final TransactionsService transactionsService;
    private final ModelMapper modelMapper;

public OrderInformationServiceImpl(OrderRepository orderRepository, OrderService orderService, CustomerService customerService, PaymentService paymentService, TransactionsService transactionsService, ModelMapper modelMapper) {
    this.orderRepository = orderRepository;
    this.orderService = orderService;
        this.customerService = customerService;
        this.paymentService = paymentService;
        this.transactionsService = transactionsService;
    this.modelMapper = modelMapper;
}

    @Override
    public OrderInformationDTO getOrderInformationByOrderId(Long orderId) {
        OrderDTO orderDTO = orderService.getOrderDTOById(orderId);
        CustomerDTO customerDTO = customerService.getCustomerDTOById(orderDTO.getCustomerId());
        PaymentDTO paymentDTO = paymentService.getPaymentDTOByOrderId(orderId);
        TransactionsDTO transactionsDTO = transactionsService.getTransactionsDTOByPaymentId(paymentDTO.getId());
        return new OrderInformationDTO(orderDTO, customerDTO, paymentDTO, transactionsDTO);
    }

    private OrderDTO getOrderDetails(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            return modelMapper.map(orderOptional.get(), OrderDTO.class);
        } else {
            throw new EntityNotFoundException("Order not found with ID: " + orderId);
        }
    }
}
