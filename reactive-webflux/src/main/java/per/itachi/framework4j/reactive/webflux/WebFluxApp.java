package per.itachi.framework4j.reactive.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class WebFluxApp {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxApp.class, args);
    }

}
