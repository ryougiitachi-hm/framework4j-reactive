package per.itachi.framework4j.reactive.webflux.joint.handlerfunction;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpMethod;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class RouteWrapper {

    private HttpMethod httpMethod;

    private String routeURL;
}
