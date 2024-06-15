package com.samsepiol.ai.aggregator.aiservice.async;


import com.samsepiol.bom.library.async.impl.AbstractAsyncExecutor;

import java.util.concurrent.Executors;

//@Component(AsyncExecutorPool.AI_CLIENT)
public class AIServiceExecutor extends AbstractAsyncExecutor {

    protected AIServiceExecutor() {
        super(Executors.newVirtualThreadPerTaskExecutor());
    }
}
