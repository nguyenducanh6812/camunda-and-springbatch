package com.upwork.camunda.batch.orderids.chunk;

import com.upwork.camunda.workflow.service.StartProcessService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessProcessor implements ItemProcessor<Long, Long> {

    private final StartProcessService startProcessService;

    public OrderProcessProcessor(StartProcessService startProcessService) {
        this.startProcessService = startProcessService;
    }

    @Override
    public Long process(Long orderId) throws Exception {
        startProcessService.startProcess(orderId);
        return orderId;
    }
}
