package per.itachi.framework4j.reactive.webflux.joint.controller;

import java.util.Arrays;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequestMapping("flux")
public class FluxController {

    @GetMapping("just")
    public Flux<String> testJust() {
        return Flux.just(UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }

    @GetMapping("fromIterable")
    public Flux<String> testFromIterable() {
        return Flux.fromIterable(
                Arrays.asList(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
    }

    @GetMapping("range")
    public Flux<Integer> testRange() {
        return Flux.range(1, 5);
    }

    @GetMapping("create")
    public Flux<String> testCreate() {
        return Flux.create(sink -> {
            sink.onRequest(n -> log.info("sink.onRequest"))
                    .onCancel(() -> log.info("sink.onCancel"))
                    .onDispose(() -> log.info("sink.onDispose"));
            log.info("The sink is {}, contextView={}. ", sink, sink.contextView());
            sink.complete();
        });
    }

    @GetMapping("map")
    public Flux<String> testMap() {
        return Flux.range(1, 5)
                .map(item -> {
                    log.info("map");
                    return String.valueOf(item);
                });
    }

    /**
     * @param isError To simulate whether to callback throwable.
     * */
    @GetMapping("subscribe")
    public Flux<Integer> testSubscribe(@RequestParam boolean isError) {
        Flux<Integer> flux = Flux.range(1, 5)
                .map(item -> isError ? item/0 : item * 3);
        flux.subscribe(
                item -> log.info("Subscribing, item={}. ", item),
                throwable -> log.error("throwable. ", throwable)); // no return value.
        return flux;
    }

    /**
     * Returns error instead of normal value via HTTP.
     * */
    @GetMapping("do-on-error")
    public Flux<Integer> testDoOnError() {
        Flux<Integer> flux = Flux.range(1, 5)
                .map(item -> item + 1)
                .map(item -> item/0)
                // mutant: Class, Predicator<? extends Throwable>
                .doOnError(throwable -> log.error("doOnError throwable. ", throwable)) // will output
                ;
        flux.subscribe(
                item -> log.info("Subscribing, item={}. ", item),
                throwable -> log.error("subscribe throwable. ", throwable)); // will output
        return flux;
    }

    /**
     * Returns normal value via HTTP.
     * */
    @GetMapping("on-error-return")
    public Flux<Integer> testOnErrorReturn() {
        Flux<Integer> flux = Flux.range(1, 5)
                .map(item -> item + 1)
                .map(item -> item/0)
                // mutant: Class, Predicator<? extends Throwable>
                .doOnError(throwable -> log.error("doOnError throwable. ", throwable)) // will output
                .onErrorReturn(UUID.randomUUID().hashCode())
                ;
        flux.subscribe(
                item -> log.info("Subscribing, item={}. ", item), // will output
                throwable -> log.error("subscribe throwable. ", throwable)); // won't output
        return flux;
    }

    /**
     *
     * */
    @GetMapping("on-error-resume")
    public Flux<Integer> testOnErrorResume() {
        Flux<Integer> flux = Flux.range(1, 5)
                .map(item -> item + 1)
                .map(item -> item/0)
                .onErrorResume(throwable -> Flux.empty())
                ;
        flux.subscribe(
                item -> log.info("Subscribing, item={}. ", item), // will output
                throwable -> log.error("subscribe throwable. ", throwable)); // won't output
        return flux;
    }
}
