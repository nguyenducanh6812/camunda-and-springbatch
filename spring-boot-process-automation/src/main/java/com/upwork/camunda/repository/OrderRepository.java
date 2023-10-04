package com.upwork.camunda.repository;

import com.upwork.camunda.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o.id FROM Order o")
    List<Long> findAllOrderIds();
}
