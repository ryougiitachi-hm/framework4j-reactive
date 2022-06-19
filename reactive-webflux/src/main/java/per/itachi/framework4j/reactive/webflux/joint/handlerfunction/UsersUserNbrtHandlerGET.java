package per.itachi.framework4j.reactive.webflux.joint.handlerfunction;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import per.itachi.framework4j.reactive.webflux.domain.User;
import per.itachi.framework4j.reactive.webflux.infra.restful.customersvc.CustomerPort;
import per.itachi.framework4j.reactive.webflux.infra.restful.customersvc.dto.CustomerDTO;
import per.itachi.framework4j.reactive.webflux.joint.constant.UrlConstant;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class UsersUserNbrtHandlerGET implements RouteHandlerFunction{

    @Autowired
    private CustomerPort customerPort;

    @Override
    public List<HttpMethod> supportsHttpMethods() {
        return Collections.singletonList(HttpMethod.GET);
    }

    @Override
    public String routeURL() {
        return UrlConstant.V1_USERS_USERBNR;
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        log.info("Received request, request={}.", request);
        String userNbr = request.pathVariable("userNbr");
        return request.bodyToMono(Void.class)
                .flatMap(param->{
                    log.info("flatMap");
                    CompletableFuture<CustomerDTO> futureCus = customerPort.getCustomerByIdCardAsync(UUID.randomUUID().toString());
                    CompletableFuture.allOf(futureCus);
                    User user = new User();
                    user.setUsername(UUID.randomUUID().toString());
                    user.setUserNbr(UUID.randomUUID().toString());
                    user.setBirthday(LocalDate.now());
                    return ServerResponse.ok().body(user, User.class);
                });
    }
}