package per.itachi.framework4j.reactive.webflux.joint.handlerfunction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import per.itachi.framework4j.reactive.webflux.joint.exception.BusinessException;

@Component
public class RouteHandlerFunctionContext {

    @Autowired
    private List<RouteHandlerFunction> routeHandlerFunctionList;
    
    private Map<RouteWrapper, RouteHandlerFunction> routeHandlerFunctionMap;
    
    @PostConstruct
    public void init() {
        Map<RouteWrapper, RouteHandlerFunction> routeHandlerFunctionMap = new HashMap<>();
        for (RouteHandlerFunction handlerFunction : routeHandlerFunctionList) {
            for (HttpMethod httpMethod : handlerFunction.supportsHttpMethods()) {
                RouteWrapper routeWrapper  = RouteWrapper.builder()
                        .httpMethod(httpMethod)
                        .routeURL(handlerFunction.routeURL())
                        .build();
                routeHandlerFunctionMap.put(routeWrapper, handlerFunction);
            }
        }
        this.routeHandlerFunctionMap = routeHandlerFunctionMap;
    }

    public RouteHandlerFunction routeHandlerFunction(HttpMethod httpMethod, String routeURL) {
        RouteWrapper routeWrapper  = RouteWrapper.builder()
                .httpMethod(httpMethod)
                .routeURL(routeURL)
                .build();
        RouteHandlerFunction handlerFunction = routeHandlerFunctionMap.get(routeWrapper);
        if (handlerFunction == null) {
            throw new BusinessException("Lacks of handlerFunction");
        }
        return handlerFunction;
    }
}
