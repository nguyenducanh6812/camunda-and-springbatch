package com.upwork.camunda.batch.orderids.chunk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Slf4j
@Component
public class OrderIdItemReader extends JpaPagingItemReader<Long> {
    @Autowired
    public OrderIdItemReader(EntityManagerFactory entityManagerFactory) {
        this.setName("orderIdsItemReader");
        this.setEntityManagerFactory(entityManagerFactory);
        this.setQueryString("SELECT id FROM Order");
        this.setPageSize(2);
        try {
            this.afterPropertiesSet();
        } catch (Exception e) {
            log.error("Failed to initialize OrderIdItemReader: ", e);
            throw new IllegalStateException("Failed to initialize OrderIdItemReader", e);
        }
    }
}
