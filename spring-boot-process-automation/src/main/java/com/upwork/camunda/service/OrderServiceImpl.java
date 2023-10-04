package com.upwork.camunda.service;

import com.upwork.camunda.dto.CustomerDTO;
import com.upwork.camunda.dto.OrderDTO;
import com.upwork.camunda.model.Order;
import com.upwork.camunda.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }
    public List<Long> getAllOrderIds(){
        return orderRepository.findAllOrderIds();
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
    @Override
    public OrderDTO getOrderDTOById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        orderDTO.setCustomerDTO(modelMapper.map(order.getCustomer(), CustomerDTO.class));
        return orderDTO;
    }
}
