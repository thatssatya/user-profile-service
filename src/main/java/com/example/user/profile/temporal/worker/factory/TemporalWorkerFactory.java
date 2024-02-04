package com.example.user.profile.temporal.worker.factory;

import com.example.user.profile.temporal.worker.Worker;
import com.example.user.profile.temporal.worker.impl.AbstractTemporalWorker;
import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerOptions;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class TemporalWorkerFactory {
    private final Map<String, AbstractTemporalWorker> temporalWorkerMap;
    private final WorkerFactory factory;

    @PostConstruct
    private void init() {
        addWorkersToFactory(temporalWorkerMap, factory);
    }

    private static void addWorkersToFactory(Map<String, AbstractTemporalWorker> temporalWorkerMap, WorkerFactory factory) {
        temporalWorkerMap.values().forEach(worker -> addWorkerToFactory(factory, worker));
    }

    private static void addWorkerToFactory(WorkerFactory factory, AbstractTemporalWorker worker) {
        var newWorker = factory.newWorker(worker.getTaskQueue(), WorkerOptions.getDefaultInstance());

        worker.getWorkflowImplementationTypes().forEach(newWorker::registerWorkflowImplementationTypes);
        worker.getActivityImplementations().forEach(newWorker::registerActivitiesImplementations);

        log.info("{} added to factory", worker.getClass().getName());
    }

    private static synchronized void startFactoryIfNot(WorkerFactory factory) {
        if (!factory.isStarted()) {
            factory.start();
            log.info("Temporal Worker Factory started!");
        }
    }

    public Worker getWorker(String taskQueueName) {
        startFactoryIfNot(factory);
        return temporalWorkerMap.get(taskQueueName);
    }
}
