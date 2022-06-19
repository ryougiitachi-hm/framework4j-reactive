package per.itachi.framework4j.reactive.webflux.joint.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import per.itachi.framework4j.reactive.webflux.joint.constant.UrlConstant;
import per.itachi.framework4j.reactive.webflux.joint.handlerfunction.RouteHandlerFunctionContext;

@Configuration
public class WebFluxConfig {

    @Autowired
    private RouteHandlerFunctionContext handlerFunctionContext;

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions.route()
                .GET("/handler" + UrlConstant.V1_USERS_USERBNR, handlerFunctionContext
                        .routeHandlerFunction(HttpMethod.GET, UrlConstant.V1_USERS_USERBNR))
                .POST("/handler" + UrlConstant.V1_USERS, handlerFunctionContext
                        .routeHandlerFunction(HttpMethod.POST, UrlConstant.V1_USERS))
                .build();
    }
}
