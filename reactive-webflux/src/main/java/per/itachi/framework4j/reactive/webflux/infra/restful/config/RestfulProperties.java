package per.itachi.framework4j.reactive.webflux.infra.restful.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestfulProperties {

    private String baseUrl;

    private String username;

    private String password;
}
