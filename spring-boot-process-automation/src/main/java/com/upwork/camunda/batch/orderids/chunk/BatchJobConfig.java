package com.upwork.camunda.batch.orderids.chunk;

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
public class BatchJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final Step retrieveOrderIdsStepandStartOrderProcess;

    @Autowired
    public BatchJobConfig(JobBuilderFactory jobBuilderFactory, @Qualifier("retrieveListOrderId") Step retrieveOrderIdsStepandStartOrderProcess) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.retrieveOrderIdsStepandStartOrderProcess = retrieveOrderIdsStepandStartOrderProcess;
    }


    @Bean
    public Job retrieveListOrderIDandStartOrderProcess() {
        return jobBuilderFactory.get("retrieveOrderIdsStepandStartOrderProcess")
                .incrementer(new RunIdIncrementer())
                .flow(retrieveOrderIdsStepandStartOrderProcess)
                .end().build();
    }
}
