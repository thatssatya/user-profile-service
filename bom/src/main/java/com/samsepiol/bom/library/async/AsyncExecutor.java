package com.samsepiol.bom.library.async;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public interface AsyncExecutor {
    <T> CompletableFuture<T> supply(Supplier<T> supplier);

    <T> void run(Supplier<T> supplier);
}
