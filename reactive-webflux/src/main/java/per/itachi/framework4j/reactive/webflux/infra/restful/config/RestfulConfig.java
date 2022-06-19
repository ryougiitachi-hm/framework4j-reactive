package per.itachi.framework4j.reactive.webflux.infra.restful.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestfulConfig {

    @Bean
    @ConfigurationProperties("infra.restful.user-svc")
    public RestfulProperties userSvcProperties() {
        return new RestfulProperties();
    }

    @Bean
    public WebClient userSvcWebClient(RestfulProperties userSvcProperties) {
        return WebClient.builder()
                .baseUrl(userSvcProperties.getBaseUrl())
                .build();
    }

    @Bean
    @ConfigurationProperties("infra.restful.customer-svc")
    public RestfulProperties customerSvcProperties() {
        return new RestfulProperties();
    }

    @Bean
    public WebClient customerSvcWebClient(RestfulProperties customerSvcProperties) {
        return WebClient.builder()
                .baseUrl(customerSvcProperties.getBaseUrl())
                .build();
    }
}
