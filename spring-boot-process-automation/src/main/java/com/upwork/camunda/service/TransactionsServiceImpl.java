package com.upwork.camunda.service;

import com.upwork.camunda.dto.TransactionsDTO;
import com.upwork.camunda.model.Transactions;
import com.upwork.camunda.repository.TransactionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class TransactionsServiceImpl implements TransactionsService {
    private final TransactionsRepository transactionsRepository;
    private final ModelMapper modelMapper;

    public TransactionsServiceImpl(TransactionsRepository transactionsRepository, ModelMapper modelMapper) {
        this.transactionsRepository = transactionsRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public TransactionsDTO getTransactionsDTOByPaymentId(Long paymentId) {
        Optional<Transactions> transactions = transactionsRepository.findByPaymentId(paymentId);
        if (transactions.isPresent()) {
            return modelMapper.map(transactions, TransactionsDTO.class);
        } else {
            throw new EntityNotFoundException("Transactions not found for Payment ID: " + paymentId);
        }
    }
}
