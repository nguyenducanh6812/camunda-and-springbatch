package com.upwork.camunda.batch.orderids.tasklet;

import com.upwork.camunda.repository.OrderRepository;
import com.upwork.camunda.workflow.service.StartProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Component
@Slf4j
public class OrderIdsProcessTasklet implements Tasklet {
    private final OrderRepository orderRepository;
    private final StartProcessService startProcessService;
    public OrderIdsProcessTasklet(OrderRepository orderRepository, StartProcessService startProcessService) {
        this.orderRepository = orderRepository;
        this.startProcessService = startProcessService;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        log.info("OrderIdsProcessTasklet started...");
        List<Long> orderIds = orderRepository.findAllOrderIds();
        Flux.fromIterable(orderIds)
                .parallel()
                .runOn(Schedulers.boundedElastic())
                .flatMap(this::startProcessAsync)
                .sequential()
                .blockLast();

        return RepeatStatus.FINISHED;
    }

    private Mono<Void> startProcessAsync(Long orderId) {
        return Mono.fromRunnable(() -> {
            try {
                startProcessService.startProcess(orderId);
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());
            }
        });
    }
}
