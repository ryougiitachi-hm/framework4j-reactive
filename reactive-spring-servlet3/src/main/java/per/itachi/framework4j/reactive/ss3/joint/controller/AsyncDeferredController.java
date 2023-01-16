package per.itachi.framework4j.reactive.ss3.joint.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import per.itachi.framework4j.reactive.ss3.app.service.TraceService;

/**
 * DeferredResult can be used to return async result, supported by servlet 3.x.
 * */
@Slf4j
@RestController
@RequestMapping("/callable/deferred")
public class AsyncDeferredController {

    @Autowired
    private TraceService traceService;

    @GetMapping
    public DeferredResult<String> testDeferredResult() {
        log.info("Executing DeferredResult. ");
        // will return timeout result if executing time is too long.
        // It seems that deferredResult won't interrupt the logic.
        DeferredResult<String> deferredResult = new DeferredResult<>(
                1000L,
                () -> "TIMEOUTRESULT");
        traceService.retrieveTracesCompletable()
                .whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));
        log.info("Servlet thread released. ");
        return deferredResult;
    }
}
