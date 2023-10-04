package com.upwork.camunda.repository;

import com.upwork.camunda.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    Optional<Transactions> findByPaymentId(Long id);
}