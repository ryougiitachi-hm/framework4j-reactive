package per.itachi.framework4j.reactive.webflux.joint.controller;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.itachi.framework4j.reactive.webflux.app.service.UserService;
import reactor.core.publisher.Mono;

/**
 * Referred to https://segmentfault.com/q/1010000042925803.
 * */
@Slf4j
@RestController
@RequestMapping("mono")
public class MonoController {

    @Autowired
    private UserService userService;

    /**
     * Create Mono<T>. Edge.
     * */
    @GetMapping("just")
    public Mono<String> testJust() {
        return Mono.just(UUID.randomUUID().toString());
    }

    /**
     * Create Mono<T>. Edge.
     * */
    @GetMapping("just-without-return")
    public void testJustWithoutReturn() {
        Mono<Long> mono =Mono.just(userService.getDelayedTime());
        mono.subscribe(item -> log.info("just-without-return, item={}", item)); // t0
        mono.subscribe(item -> log.info("just-without-return, item={}", item)); // t0
    }

    /**
     * Create Mono<T>. Lazy.
     * */
    @GetMapping("defer")
    public Mono<Long> testDefer() {
        return Mono.defer(() -> Mono.just(System.currentTimeMillis()));
    }

    @GetMapping("defer-without-return")
    public void testDeferWithoutReturn() {
        Mono<Long> mono = Mono.defer(() -> Mono.just(userService.getDelayedTime()));
        mono.subscribe(item -> log.info("just-without-return, item={}", item)); // t1
        mono.subscribe(item -> log.info("just-without-return, item={}", item)); // t2
    }
}
