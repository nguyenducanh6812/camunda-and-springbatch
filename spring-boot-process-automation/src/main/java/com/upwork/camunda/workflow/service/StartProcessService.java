package com.upwork.camunda.workflow.service;

import com.upwork.camunda.config.constant.OrderProcess;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StartProcessService {
    private final RuntimeService runtimeService;
    @Autowired
    public StartProcessService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public void startProcess(Long orderId) {
        log.info("Start process for order id: " + orderId);
        VariableMap variables = Variables.putValue("orderId", orderId);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(OrderProcess.ORDER_PROCESS_DEF_KEY, variables);
        String processInstanceId = processInstance.getProcessInstanceId();
        log.info("Start order process with orderId-{} and processInstanceId-{}", orderId, processInstanceId);
    }
}