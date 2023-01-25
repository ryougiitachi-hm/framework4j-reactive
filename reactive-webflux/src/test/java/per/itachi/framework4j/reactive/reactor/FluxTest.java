package per.itachi.framework4j.reactive.reactor;

import java.util.concurrent.ThreadLocalRandom;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class FluxTest {

    @Test
    public void testMerge() {
        Mono<Integer> mono = Mono.defer(()-> Mono.just(ThreadLocalRandom.current().nextInt(0, 10)));
        Flux.merge(mono, mono, mono, mono, mono, mono, mono)
                .subscribe(item -> {log.info("item={}", item);});
    }
}
