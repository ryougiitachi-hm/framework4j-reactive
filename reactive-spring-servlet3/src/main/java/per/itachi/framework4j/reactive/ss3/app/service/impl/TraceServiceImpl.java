package per.itachi.framework4j.reactive.ss3.app.service.impl;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import per.itachi.framework4j.reactive.ss3.app.service.TraceService;

@Slf4j
@Service
public class TraceServiceImpl implements TraceService {

    @Resource
    private ExecutorService defaultAsyncExecutor;

    @Override
    public String retrieveTraces() {
        try {
            Thread.sleep(3000L);
            log.info("Time-consuming operation completed");
            return UUID.randomUUID().toString();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Callable<String> retrieveTracesCallable() {
        return this::retrieveTraces;
    }

    @Override
    public CompletableFuture<String> retrieveTracesCompletable() {
        return CompletableFuture.supplyAsync(this::retrieveTraces, defaultAsyncExecutor);
    }
}
