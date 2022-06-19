package per.itachi.framework4j.reactive.webflux.app.service.impl;

import org.springframework.stereotype.Service;
import per.itachi.framework4j.reactive.webflux.app.service.UserService;
import per.itachi.framework4j.reactive.webflux.domain.User;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUserByNbr(String userNbr) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }
}
