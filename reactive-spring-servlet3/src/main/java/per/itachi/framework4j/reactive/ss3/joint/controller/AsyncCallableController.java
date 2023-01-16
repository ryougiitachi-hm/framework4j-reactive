package per.itachi.framework4j.reactive.ss3.joint.controller;

import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.itachi.framework4j.reactive.ss3.app.service.TraceService;

/**
 * By default, using SimpleAsyncTaskExecutor, not suitable for production env.
 * If not configured, message will be displayed when invoked at the first time.
 *
 * */
@Slf4j
@RestController
@RequestMapping("/async/callable")
public class AsyncCallableController {

    @Autowired
    private TraceService traceService;

    @GetMapping
    public Callable<String> testCallable() {
        log.info("Executing async callable");
        Callable<String> callable = traceService.retrieveTracesCallable();
        log.info("Servlet thread released");
        return callable;
    }
}
