package per.itachi.framework4j.reactive.rxjava2.infra.common.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ExecutorService commonExecutorService() {
        return Executors.newFixedThreadPool(5);
    }
}
