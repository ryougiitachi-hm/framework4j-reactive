package per.itachi.framework4j.reactive.webflux.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import per.itachi.framework4j.reactive.webflux.app.service.UserService;
import per.itachi.framework4j.reactive.webflux.domain.User;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUserByNbr(String userNbr) {
        return null;
    }

    @Override
    public void addUser(User user) {
    }

    @Override
    public long getDelayedTime() {
        try {
            Thread.sleep(3500L);
            log.info("getDelayedTime completed");
            return System.currentTimeMillis();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
