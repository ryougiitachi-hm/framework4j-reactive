package per.itachi.framework4j.reactive.webflux.joint.handlerfunction;

import java.util.List;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

public interface RouteHandlerFunction extends HandlerFunction<ServerResponse> {

    List<HttpMethod> supportsHttpMethods();

    String routeURL();
}