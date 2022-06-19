package per.itachi.framework4j.reactive.webflux.joint.controller;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import per.itachi.framework4j.reactive.webflux.domain.User;
import per.itachi.framework4j.reactive.webflux.infra.restful.customersvc.CustomerPort;
import per.itachi.framework4j.reactive.webflux.infra.restful.customersvc.dto.CustomerDTO;
import per.itachi.framework4j.reactive.webflux.joint.constant.UrlConstant;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/controller")
public class UserController {

    @Autowired
    private CustomerPort customerPort;

    @PostMapping(UrlConstant.V1_USERS)
    public Mono<ServerResponse> addUser(ServerRequest request) {
        log.info("Received request, url={}, method={}. ", request.path(), request.method());
        return request.bodyToMono(User.class)
                .flatMap(param->{
                    log.info("flatMap");
                    CompletableFuture<CustomerDTO> futureCus = customerPort.getCustomerByIdCardAsync(UUID.randomUUID().toString());
                    CompletableFuture.allOf(futureCus);
                    User user = new User();
                    user.setUsername(UUID.randomUUID().toString());
                    user.setUserNbr(UUID.randomUUID().toString());
                    user.setBirthday(LocalDate.now());
                    return ServerResponse.accepted().build();
                });// a lot of problem
    }
}
