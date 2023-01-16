package per.itachi.framework4j.reactive.ss3.app.service;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public interface TraceService {

    String retrieveTraces();

    Callable<String> retrieveTracesCallable();

    CompletableFuture<String> retrieveTracesCompletable();
}