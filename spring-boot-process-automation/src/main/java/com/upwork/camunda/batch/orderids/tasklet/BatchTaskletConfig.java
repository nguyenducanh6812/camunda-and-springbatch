package com.upwork.camunda.batch.orderids.tasklet;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchTaskletConfig {
    private final OrderIdsProcessTasklet orderIdsProcessTasklet;

    public BatchTaskletConfig(OrderIdsProcessTasklet orderIdsProcessTasklet) {
        this.orderIdsProcessTasklet = orderIdsProcessTasklet;
    }

    @Bean
    public Step retrieveListOrderIdTasklet(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("retrieveOrderIdsStepAndStartOrderProcessTasklet")
                .repository(jobRepository)
                .transactionManager(platformTransactionManager)
                .tasklet(orderIdsProcessTasklet)
                .build();
    }
}
