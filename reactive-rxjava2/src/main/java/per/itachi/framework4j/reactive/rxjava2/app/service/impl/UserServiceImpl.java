package per.itachi.framework4j.reactive.rxjava2.app.service.impl;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import per.itachi.framework4j.reactive.rxjava2.app.service.UserService;
import per.itachi.framework4j.reactive.rxjava2.infra.restful.usersvc.UserPort;
import per.itachi.framework4j.reactive.rxjava2.infra.restful.usersvc.dto.UserDto;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserPort userPort;

    @Override
    public void showUsers() {
        CompletableFuture<UserDto> futureByName = userPort.getUserByNameAsync(UUID.randomUUID().toString());
        CompletableFuture.allOf(futureByName);
        UserDto userByName = futureByName.join();
        log.info("userByName={}. ", userByName);
    }

    @Override
    public UserDto getUserByNbr(String userNbr) {
        return userPort.getUserByNbr(userNbr);
    }
}
