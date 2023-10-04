package com.upwork.camunda.batch.orderids.tasklet;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchTaskletJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final Step retrieveOrderIdsStepAndStartOrderProcessTasklet;

    @Autowired
    public BatchTaskletJobConfig(JobBuilderFactory jobBuilderFactory, @Qualifier("retrieveListOrderIdTasklet") Step retrieveOrderIdsStepAndStartOrderProcessTasklet) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.retrieveOrderIdsStepAndStartOrderProcessTasklet = retrieveOrderIdsStepAndStartOrderProcessTasklet;
    }
    @Bean
    public Job retrieveListOrderIDandStartOrderProcessTasklet() {
        return jobBuilderFactory.get("retrieveOrderIdsStepAndStartOrderProcessTasklet")
                .incrementer(new RunIdIncrementer())
                .flow(retrieveOrderIdsStepAndStartOrderProcessTasklet)
                .end().build();
    }
}
