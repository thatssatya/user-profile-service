package com.example.user.profile.config;


import com.example.user.profile.temporal.TemporalClient;
import com.example.user.profile.temporal.worker.impl.AbstractTemporalWorker;
import io.temporal.worker.WorkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class WorkerConfig {
    @Bean
    public Map<String, AbstractTemporalWorker> temporalWorkerMap(List<AbstractTemporalWorker> workers) {
        return workers.stream().collect(Collectors.toMap(AbstractTemporalWorker::getTaskQueue, worker -> worker));
    }

    @Bean
    public WorkerFactory workerFactory(TemporalClient temporalClient) {
        return WorkerFactory.newInstance(temporalClient.getInstance());
    }
}
