package com.upwork.camunda.workflow.task;

import com.upwork.camunda.dto.OrderInformationDTO;
import com.upwork.camunda.service.OrderInformationService;
import com.upwork.camunda.service.OrderInformationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RetrieveOrderInformationService implements JavaDelegate {
    private final OrderInformationService orderInformationService;

    public RetrieveOrderInformationService(OrderInformationServiceImpl orderInformationService) {
        this.orderInformationService = orderInformationService;
    }

    @Override
        public void execute(DelegateExecution execution) {
        log.info("retrieving order information");
        Long orderId = (Long) execution.getVariable("orderId");
        log.info("orderId: {}", orderId);
        OrderInformationDTO orderInformationDTO = orderInformationService.getOrderInformationByOrderId(orderId);
        execution.setVariable("orderInformationDTO", orderInformationDTO.toString());
        log.info("orderDTO: {}", orderInformationDTO);
    }
}
