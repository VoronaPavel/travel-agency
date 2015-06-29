package by.pavel.aspects.internal.async;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public abstract aspect AsyncAspect {

    private Executor executor = Executors.newCachedThreadPool();

    private pointcut async(): call(@by.pavel.aspects.async.Async void *(..));

    void around (): async() {
        executor.execute(() -> proceed());
    }
}
