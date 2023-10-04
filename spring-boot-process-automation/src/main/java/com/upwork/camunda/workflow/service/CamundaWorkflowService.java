package com.upwork.camunda.workflow.service;

import com.upwork.camunda.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.camunda.bpm.engine.variable.Variables.*;

@Service
@Slf4j
public class CamundaWorkflowService {

    private final RuntimeService runtimeService;

    public static final String ORDER_PROCESS_NAME = "orderProcess";

    public static final String ORDER_TRANSACTION_ID = "orderId";
    public static final String DATE = "date";
    public static final String AMOUNT = "orderAmount";
    public static final String CURRENCY = "currency";

    @Autowired
    public CamundaWorkflowService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public void startWorkflow(OrderDTO orderDTO) {
        try {
            runtimeService.startProcessInstanceByKey(ORDER_PROCESS_NAME, getWorkflowVariables(orderDTO));
            log.info("Camunda Start Workflow Instance with Order ID: {}", orderDTO.getId());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private VariableMap getWorkflowVariables(OrderDTO order) {
        VariableMap variables = createVariables()
                        .putValueTyped(ORDER_TRANSACTION_ID, longValue(order.getId()))
                        .putValueTyped(DATE, dateValue(order.getDate()))
                        .putValue(AMOUNT, objectValue((order.getCustomerDTO())));
        log.info("Workflow variables for order DTO: {}", order.getId());
        variables.forEach(
                (s, o) -> {
                    log.info("{}:{}", s, o);
                });
        return variables;
    }
}
