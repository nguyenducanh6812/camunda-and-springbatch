package com.upwork.camunda.batch.orderids.chunk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Slf4j
@Component
@EnableScheduling
public class BatchJobScheduler {
    private final JobLauncher jobLauncher;

    private final Job retrieveListOrderIDandStartOrderProcess;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    public BatchJobScheduler(JobLauncher jobLauncher, Job retrieveListOrderIDandStartOrderProcess) {
        this.jobLauncher = jobLauncher;
        this.retrieveListOrderIDandStartOrderProcess = retrieveListOrderIDandStartOrderProcess;
    }

    //@Scheduled(cron = "0 0/2 * * * ?")
    public void scheduleBatchJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder().addString("time", simpleDateFormat.format(Calendar.getInstance().getTime())).toJobParameters();
            JobExecution jobExecution = jobLauncher.run(retrieveListOrderIDandStartOrderProcess, jobParameters);
            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                log.info("Batch Job completed successfully.");
            } else {
                log.info("Batch Job failed with status: " + jobExecution.getStatus());
            }
        } catch (Exception e) {
            log.error("Batch Job failed with exception: ", e);
        }
    }
}
