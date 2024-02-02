package com.example.user.profile.temporal.worker.factory;

import com.example.user.profile.temporal.worker.Worker;
import com.example.user.profile.temporal.worker.impl.AbstractTemporalWorker;
import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class TemporalWorkerFactory {
    private final Map<String, AbstractTemporalWorker> temporalWorkerMap;

    public TemporalWorkerFactory(Map<String, AbstractTemporalWorker> temporalWorkerMap, WorkerFactory factory) {
        this.temporalWorkerMap = temporalWorkerMap;
        addWorkersToFactory(temporalWorkerMap, factory);
        startFactory(factory);
    }

    private static void addWorkersToFactory(Map<String, AbstractTemporalWorker> temporalWorkerMap, WorkerFactory factory) {
        temporalWorkerMap.values().forEach(worker -> {
            addWorkerToFactory(factory, worker);
        });
    }

    private static void addWorkerToFactory(WorkerFactory factory, AbstractTemporalWorker worker) {
        var newWorker = factory.newWorker(worker.getTaskQueue(), WorkerOptions.getDefaultInstance());

        worker.getWorkflowImplementationTypes().forEach(newWorker::registerWorkflowImplementationTypes);
        worker.getActivityImplementations().forEach(newWorker::registerActivitiesImplementations);

        log.info("{} added to factory", worker.getClass().getName());
    }

    private static void startFactory(WorkerFactory factory) {
        factory.start();
        log.info("Factory started!");
    }

    public Worker getWorker(String taskQueueName) {
        return temporalWorkerMap.get(taskQueueName);
    }
}
