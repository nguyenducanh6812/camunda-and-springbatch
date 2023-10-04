package com.upwork.camunda.workflow.task;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CheckOrderService implements JavaDelegate {


  @Override
  public void execute(DelegateExecution execution) {
    log.info("check order service");
    execution.getVariables().forEach(
            (s, o) -> {
              log.info("{}:{}", s, o);
            });
  }

}
