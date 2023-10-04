package com.upwork.camunda.batch.orderids.chunk;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {
    private final StepBuilderFactory stepBuilderFactory;

    private final OrderIdItemReader orderIdItemReader;
    private final OrderProcessProcessor orderProcessProcessor;

    private final OrderProcessWriter orderProcessWriter;

    @Autowired
    public BatchConfig(StepBuilderFactory stepBuilderFactory, OrderIdItemReader orderIdItemReader, OrderProcessProcessor orderProcessProcessor, OrderProcessWriter orderProcessWriter) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.orderIdItemReader = orderIdItemReader;
        this.orderProcessProcessor = orderProcessProcessor;
        this.orderProcessWriter = orderProcessWriter;
    }

    @Bean
    public Step retrieveListOrderId() {
        return stepBuilderFactory.get("retrieveListOrderIdandStartOrderProcess")
                .<Long, Long>chunk(10)
                .reader(orderIdItemReader)
                .processor(orderProcessProcessor)
                .writer(orderProcessWriter)
                .build();
    }
}
