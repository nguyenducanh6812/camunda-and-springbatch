package com.upwork.camunda.batch.orderids.chunk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class OrderProcessWriter implements ItemWriter<Long> {
    @Override
    public void write(List<? extends Long> orderIds) throws Exception {
        orderIds.forEach(orderId -> log.info("orderId: {}", orderId));
    }
}
