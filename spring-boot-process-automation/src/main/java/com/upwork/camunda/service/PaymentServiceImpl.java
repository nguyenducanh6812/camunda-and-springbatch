package com.upwork.camunda.service;

import com.upwork.camunda.dto.PaymentDTO;
import com.upwork.camunda.model.Payment;
import com.upwork.camunda.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, ModelMapper modelMapper) {
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PaymentDTO getPaymentDTOByOrderId(Long orderId) {
        Optional<Payment> paymentOptional = paymentRepository.findByOrderId(orderId);

        if (paymentOptional.isPresent()) {
            return modelMapper.map(paymentOptional.get(), PaymentDTO.class);
        } else {
            throw new EntityNotFoundException("Payment not found for Order ID: " + orderId);
        }
    }
}
