package per.itachi.framework4j.reactive.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * No need to add "-" to thread prefix.
 * */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class SchedulerTest {

    private static final int DEFAULT_NUM_OF_THREAD = 10;

    private static final int DEFAULT_NUM_QUEUED_TASKS = 10;

    /**
     * Schedulers.newBoundedElastic can be regarded as Executors.cached
     * */
    @Test
    public void testBoundedElastic() {
        Scheduler scheduler = Schedulers.newBoundedElastic(DEFAULT_NUM_OF_THREAD, DEFAULT_NUM_QUEUED_TASKS, "new-elastic");
        Flux.range(5, 11)
                .subscribeOn(scheduler)
                .subscribe(item -> {log.info("item={}", item);});
//        log.info("The main testing thread finished. ");
    }

    /**
     * Schedulers.newBoundedElastic can be regarded as Executors.fixed.
     * */
    @Test
    public void testNewParallel() {
//        log.info("initialized");
        Scheduler scheduler = Schedulers.newParallel("new-parallel", DEFAULT_NUM_OF_THREAD);
        Flux.range(5, 11)
                .subscribeOn(scheduler)
                .subscribe(item -> {log.info("item={}", item);});
//        log.info("The main testing thread finished. ");
    }
}
