package com.samsepiol.ai.service;

import com.samsepiol.ai.aggregator.aiservice.AiServiceClient;
import com.samsepiol.ai.aggregator.aiservice.request.AIClientRequest;
import com.samsepiol.ai.aggregator.aiservice.response.AIResponse;
import com.samsepiol.ai.service.request.AIServiceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AIService {
    private final AiServiceClient client;

    public AIResponse getResponse(AIServiceRequest request) {
        log.info("Thread: {}", Thread.currentThread());

        var clientRequest = AIClientRequest.builder()
                .person(request.getPerson())
                .message(request.getMessage())
                .mood(request.getMood())
                .build();

//        return IntStream.range(0, request.getCount())
//                .mapToObj(value -> client.getAsyncResponse(clientRequest))
//                .map(CompletableFuture::join)
//                .toList()
//                .stream()
//                .map(CompletableFuture::join)
//                .findFirst()
//                .orElse(null);

//        return client.getResponse(clientRequest);
        return client.getAsyncResponse(clientRequest).join();
    }
}
